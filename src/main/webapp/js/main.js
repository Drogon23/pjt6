
document.addEventListener("DOMContentLoaded", function() {

	loadPromotions();
	initCategories();
	let categoryBox = document.querySelector(".section_event_tab");
	categoryBox.addEventListener("click", function(evt){
		changeCategory(evt);
	});
	getProducts(getStartValue(), getCategoryId());
	
	let moreButton = document.querySelector(".more");
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
		let parent = document.querySelector(".event_tab_lst");
		const templete = document.querySelector("#categories");
	
		data.items.forEach(function(cate) {
			let templeteHtml = templete.innerHTML;
			templeteHtml = templeteHtml.replace("{id}", cate.id).replace("{name}",cate.name);
			parent.innerHTML += templeteHtml;
		});
	});

	oReq.open("GET", "/naver/categories");
	oReq.send();
}


function loadPromotions() {
	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		let parent = document.querySelector(".visual_img");
		const templete = document.querySelector("#promotion_item");
		
		data.items.forEach(function(promotion) {
			let templeteHtml = templete.innerHTML;
			templeteHtml = templeteHtml.replace("{productId}", promotion.productId)
					.replace("{productImageId}", promotion.productImageId);
			parent.innerHTML += templeteHtml;
		});
		promotionSetAnimator();
	});

	oReq.open("GET", "/naver/promotions");
	oReq.send();
}


function promotionSetAnimator(){
	let promotionUl = document.querySelector(".visual_img");	
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
			//화면밖으로 나간 이미지 맨 뒤로 다시 붙임
			promotionUl.appendChild(promotionUl.children[0]);
			//애니메이션 효과 없애고 위치값 초기화
			for(let i=0; i<2; i++){
				promotionUl.children[i].style.transition = "0s";
				promotionUl.children[i].style.transform = "";
			}
		}, 1000);
	}, 1500);	
}

function changeCategory(evt){
	let category = evt.target.closest("li");
	if(category != null){
		document.querySelector(".anchor.active").setAttribute("class", "anchor");
		category.firstElementChild.setAttribute("class", "anchor active");
		document.querySelector("#start").value = 0;
		let productsList = document.querySelectorAll(".lst_event_box");
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

	oReq.open("GET", "/naver/products?start="+start+"&categoryId="+categoryId);
	oReq.send();
}

function modifyProductsCount(data){
	let totalCount = data.totalCount;
	let countTemplete = document.querySelector("#products_count").innerHTML;
	countTemplete = countTemplete.replace("{count}", totalCount);
	document.querySelector("#total_count").value = totalCount;
	let parentCount = document.querySelector(".section_event_lst");
	parentCount.firstElementChild.remove();
	parentCount.insertAdjacentHTML("afterbegin", countTemplete);
}

function loadProducts(data){
	const templete = document.querySelector("#item_list");
	let parent = document.querySelectorAll(".lst_event_box");
	let index = 0;
	data.products.forEach(function(product){
		let templeteHtml = templete.innerHTML;
		templeteHtml = templeteHtml.replace(/{displayInfoId}/gi, product.displayInfoId)
									.replace(/{id}/gi, product.id).replace(/{description}/gi, product.description)
									.replace(/{placeName}/gi, product.placeName).replace(/{content}/gi,product.content);
		parent[(index++)%2].insertAdjacentHTML("beforeend",templeteHtml);
	});
	
}

function changeStartValue(data){
	document.querySelector("#start").value = data.productsCount+parseInt(document.querySelector("#start").value);
}

function checkPaging(){
	let moreButton = document.querySelector(".btn");
	if(moreButton == null){
		createMoreButton();
	}
	let totalCount = parseInt(document.querySelector("#total_count").value);
	let start = getStartValue();
	if(totalCount<5 || start==totalCount){
		moreButton.remove();
	}
}

function createMoreButton(){
	let btn = "<button class=\"btn\"><span>더보기</span></button>";
	 document.querySelector(".more").insertAdjacentHTML("afterbegin", btn);
}

function getCategoryId(){
	let currentCategory = document.querySelector(".anchor.active").closest("li");
	return currentCategory.getAttribute("data-category");
}

function getStartValue(){
	return parseInt(document.querySelector("#start").value);
}

