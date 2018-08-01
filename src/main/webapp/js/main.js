
document.addEventListener("DOMContentLoaded", function() {

	loadPromotions();
	initCategories();
	

	let categoryBox = document.querySelector(".section_event_tab");
	categoryBox.addEventListener("click", function(evt) {
		console.log("dadsf");
	});
});

function initCategories() {

	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		let parent = document.querySelector(".event_tab_lst");
		let count = 0;
		const templete = document.querySelector("#categories");
	
		data.items.forEach(function(cate) {
			let templeteHtml = templete.innerHTML;
			templeteHtml = templeteHtml.replace("{id}", cate.id).replace("{name}",
					cate.name);
			parent.innerHTML += templeteHtml;
			count += cate.count;
		});
		let countTemplete = document.querySelector("#productsCount").innerHTML;
		countTemplete = countTemplete.replace("{count}", count);
		let parentCount = document.querySelector(".section_event_lst");
		parentCount.insertAdjacentHTML("afterbegin", countTemplete);

	});

	oReq.open("GET", "/naver/categories");
	oReq.send();
}

function loadPromotions() {
	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		let parent = document.querySelector(".visual_img");
		data.items.forEach(function(promotion) {
			let templete = document.querySelector("#promotionItem").innerHTML;
			templete = templete.replace("{productId}", promotion.productId)
					.replace("{productImageId}", promotion.productImageId);
			parent.innerHTML += templete;
		});
		promotionAnimator();
		
		
	});

	oReq.open("GET", "/naver/promotions");
	oReq.send();
}

//TODO 구조 다시 생각해보기
var promotionImgLocation = 0;
function promotionAnimator(){
	let promotionUl = document.querySelector(".visual_img");
	let length = promotionUl.childElementCount;
	promotionImgLocation -= 100;
	
	setTimeout(()=>{
		
		if(promotionImgLocation == 0){
			for(var i=0; i<length-3; i++){
				promotionUl.children[i].style.transition = "all 2s ease";
				promotionUl.children[i].style.left = promotionImgLocation+"%";
			}
			promotionUl.children[length-1].style.left = length*(-100)+"%";
			for(let i=length-3; i<length-1; i++){
				promotionUl.children[i].style.transition = "0s";
			}
		}else{
			if(promotionImgLocation == -100){
				promotionUl.children[length-1].style.transition = "0s";
			}
			for(var i=0; i<length; i++){
				promotionUl.children[i].style.left = promotionImgLocation+"%";
			}
		}
		if(promotionImgLocation < (length-4)*-100){
			for(let i=length-3; i<length; i++){
				promotionUl.children[i].style.transition = "all 2s ease";
			}		
		}
		if(promotionImgLocation < (length-3)*-100){
			for(let i=0; i<length-3; i++){
				promotionUl.children[i].style.left = "100%";
				promotionUl.children[i].style.transition = "0s";
			}
		}
		if(promotionImgLocation < (length-2)*-100){
			promotionImgLocation = 100;
		}
		
		promotionAnimator();
	}, 2000);
	
	
}

