
document.addEventListener("DOMContentLoaded", function() {

	loadPromotions();
	initCategories();
	let categoryBox = document.querySelector("div.section_event_tab");
	categoryBox.addEventListener("click", function(evt){
		changeCategory(evt);
	});
	
	getProducts(getStartValue(), getCategoryId());
	
	let moreButton = document.querySelector("div.more");
	moreButton.addEventListener("click", function(evt){
		if(evt.target.className ==="btn"){
			getProducts(getStartValue(), getCategoryId());
		}
	});
});


function initCategories() {

	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		let parent = document.querySelector("ul.event_tab_lst");
		const template = document.querySelector("#categories").innerText;
		addCompiledTemplate(data, parent, template);		
	});

	oReq.open("GET", "api/categories");
	oReq.send();
}


function loadPromotions() {
	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		let parent = document.querySelector("ul.visual_img");
		const template = document.querySelector("#promotion_item").innerText;
		addCompiledTemplate(data, parent, template);		
		promotionSetAnimator();
	});

	oReq.open("GET", "api/promotions");
	oReq.send();
}


function promotionSetAnimator(){
	let promotionUl = document.querySelector("ul.visual_img");	
	let length = promotionUl.childElementCount;
	if(length>1){
		promotionAnimator(promotionUl);
	}
}

function promotionAnimator(promotionUl){
	
	setTimeout(()=>{	
		
		for(let i=0; i<2; i++){
			promotionUl.children[i].style.transition = "all 1s ease";
		}
		setTimeout(()=>{			
			for(let i=0; i<2; i++){
				promotionUl.children[i].style.transform = "translateX(-100%)";
			}
		}, 200);
		
		promotionAnimator(promotionUl);
		
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
}

function changeCategory(evt){
	let category = evt.target.closest("li");
	if(category != null){
		document.querySelector("a.anchor.active").setAttribute("class", "anchor");
		category.firstElementChild.setAttribute("class", "anchor active");
		document.querySelector("#start").value = 0;
		let productsList = document.querySelectorAll("ul.lst_event_box");
		productsList.forEach(function(child){
			child.innerHTML="";
		})
		getProducts(getStartValue(), getCategoryId());
	}
	
}

function getProducts(start, categoryId){
	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		modifyProductsCount(data);
		loadProducts(data);
		changeStartValue(data);
		checkPaging();
	});

	oReq.open("GET", "api/products?start="+start+"&categoryId="+categoryId);
	oReq.send();
}

function modifyProductsCount(data){
	let totalCount = data.totalCount;
	const template = document.querySelector("#products_count").innerText;
	let bindTemplate = Handlebars.compile(template);
	document.querySelector("#total_count").value = totalCount;
	let parentCount = document.querySelector("div.section_event_lst");
	parentCount.firstElementChild.remove();
	parentCount.insertAdjacentHTML("afterbegin", bindTemplate(data));
}

function loadProducts(data){
	const template = document.querySelector("#item_list").innerText;
	let parent = document.querySelectorAll("ul.lst_event_box");
	let index = 0;
	let bindTemplate = Handlebars.compile(template);
	data.products.forEach(function(product){
		parent[(index++)%2].insertAdjacentHTML("beforeend",bindTemplate(product));
	});
	
}

function changeStartValue(data){
	document.querySelector("#start").value = data.productsCount+parseInt(document.querySelector("#start").value);
}
  
function checkPaging(){
	let moreButton = document.querySelector("button.btn");
	let totalCount = parseInt(document.querySelector("#total_count").value);
	let start = getStartValue();
	if(totalCount<5 || start==totalCount){
		moreButton.style.display = "none";
	}else{
		moreButton.style.display = "block";
	}
}


function addCompiledTemplate(data, parent, template){
	let bindTemplate = Handlebars.compile(template);
	let resultHtml = data.items.reduce(function(prev,next){
		return prev + bindTemplate(next);
	},"");
	parent.innerHTML += resultHtml;	
}

function getCategoryId(){
	let currentCategory = document.querySelector("a.anchor.active").closest("li");
	return currentCategory.getAttribute("data-category");
}

function getStartValue(){
	return parseInt(document.querySelector("#start").value);
}

