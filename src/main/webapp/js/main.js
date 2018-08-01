
document.addEventListener("DOMContentLoaded", function() {

	loadPromotions();
	loadCategories();

	let category = document.querySelector(".section_event_tab");
	category.addEventListener("clicked", function(evt) {

	});
});

function loadCategories() {

	let oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		let data = JSON.parse(this.responseText);
		let parent = document.querySelector(".event_tab_lst");
		let count = 0;
		data.items.forEach(function(cate) {
			let templete = document.querySelector("#categories").innerHTML;
			templete = templete.replace("{id}", cate.id).replace("{name}",
					cate.name);
			parent.innerHTML += templete;
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

	});

	oReq.open("GET", "/naver/promotions");
	oReq.send();

}
