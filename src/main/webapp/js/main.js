
document.addEventListener("DOMContentLoaded", function() {
	promotion.loadPromotions("/api/promotions");
	category.initCategories("/api/categories");
	product.getProducts("/api/products", main.getStartValue(), main.getCategoryId());
	product.addMoreButtonEvent();	
});


const category = {
	initCategories : function(url) {
		let parent = document.querySelector("ul.event_tab_lst");
		const template = document.querySelector("#categories").innerText;
		ajaxUtil.sendAjaxAsGet(url)
		.then(data =>{
			return JSON.parse(data);
		})
		.catch(status=>{
			console.log(status);
		})
		.then(jsonData=>{
			main.addCompiledTemplate(jsonData, parent, template);
			category.categoryTapEvent();
		});
	},
	categoryTapEvent : function() {
		let categoryBox = document.querySelector("div.section_event_tab");
		categoryBox.addEventListener("click", function(evt){
			category.changeCategory(evt);
		});
	},
	changeCategory : function(evt) {
		let category = evt.target.closest("li");
		if(category != null){
			document.querySelector("a.anchor.active").setAttribute("class", "anchor");
			category.firstElementChild.setAttribute("class", "anchor active");
			document.querySelector("#start").value = 0;
			let productsList = document.querySelectorAll("ul.lst_event_box");
			productsList.forEach(function(child){
				child.innerHTML="";
			})
			product.getProducts("/api/products", main.getStartValue(), main.getCategoryId());
		}
	}
}

const product = {
	getProducts : function(url) {
		ajaxUtil.sendAjaxAsGet(url+"?start="+main.getStartValue()+"&categoryId="+main.getCategoryId())
		.then(data =>{
			return JSON.parse(data);
		})
		.catch(status=>{
			console.log(status);
		})
		.then(jsonData=>{
			product.modifyProductsCount(jsonData);
			product.loadProducts(jsonData);
			product.changeStartValue(jsonData);
			product.checkPaging();
		});
	},
	modifyProductsCount : function(data) {
		let totalCount = data.totalCount;
		const template = document.querySelector("#products_count").innerText;
		let bindTemplate = Handlebars.compile(template);
		document.querySelector("#total_count").value = totalCount;
		let parentCount = document.querySelector("div.section_event_lst");
		parentCount.firstElementChild.remove();
		parentCount.insertAdjacentHTML("afterbegin", bindTemplate(data));
	},
	loadProducts : function(data) {
		const template = document.querySelector("#item_list").innerText;
		let parent = document.querySelectorAll("ul.lst_event_box");
		let index = 0;
		let bindTemplate = Handlebars.compile(template);
		data.products.forEach(function(product){
			parent[(index++)%2].insertAdjacentHTML("beforeend",bindTemplate(product));
		});
	},
	changeStartValue : function(data) {
		document.querySelector("#start").value = data.productsCount+parseInt(document.querySelector("#start").value);
	},
	checkPaging : function() {
		let moreButton = document.querySelector("#more_btn");
		let totalCount = parseInt(document.querySelector("#total_count").value);
		let start = main.getStartValue();
		if(totalCount<5 || start==totalCount){
			moreButton.style.display = "none";
		}else{
			moreButton.style.display = "block";
		}
	},
	addMoreButtonEvent : function() {
		let moreButton = document.querySelector("#more_btn");
		moreButton.addEventListener("click", function(evt){
			product.getProducts("/api/products", main.getStartValue(), main.getCategoryId());
		});
	}
}

const promotion = {
	loadPromotions : function(url) {
		let parent = document.querySelector("ul.visual_img");
		const template = document.querySelector("#promotion_item").innerText;
		ajaxUtil.sendAjaxAsGet(url)
		.then(data =>{
			return JSON.parse(data);
		})
		.catch(status=>{
			console.log(status);
		})
		.then(jsonData=>{
			main.addCompiledTemplate(jsonData, parent, template);
			promotion.promotionSetAnimator();
		});
	},
	promotionSetAnimator : function() {
		let promotionUl = document.querySelector("ul.visual_img");	
		let length = promotionUl.childElementCount;
		if(length>1){
			promotion.promotionAnimator(promotionUl);
		}
	},
	promotionAnimator : function(promotionUl) {
		setTimeout(()=>{	
			for(let i=0; i<2; i++){
				promotionUl.children[i].style.transition = "all 1s ease";
			}
			setTimeout(()=>{			
				for(let i=0; i<2; i++){
					promotionUl.children[i].style.transform = "translateX(-100%)";
				}
			}, 200);
			promotion.promotionAnimator(promotionUl);
			setTimeout(()=>{			
				// 애니메이션 효과 없애고 위치값 초기화
				for(let i=0; i<2; i++){
					promotionUl.children[i].style.transition = "0s";
					promotionUl.children[i].style.transform = "";
				}
				// 화면밖으로 나간 이미지 맨 뒤로 다시 붙임
				promotionUl.appendChild(promotionUl.children[0]);
			}, 1200);
		}, 1500);	
	},
}

const main = {
	addCompiledTemplate : function(data, parent, template) {
		let bindTemplate = Handlebars.compile(template);
		let resultHtml = data.items.reduce(function(prev,next){
			return prev + bindTemplate(next);
		},"");
		parent.innerHTML += resultHtml;	
	},
	getCategoryId : function() {
		let currentCategory = document.querySelector("a.anchor.active").closest("li");
		return currentCategory.getAttribute("data-category");
	},
	getStartValue : function() {
		return parseInt(document.querySelector("#start").value);
	}
}

