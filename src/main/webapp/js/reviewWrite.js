document.addEventListener("DOMContentLoaded", function() {
	let ui = new uiController();
	let form = new formController();
})
function uiController() {
	this.init();
}
uiController.prototype = {
	init : function() {
		this.textAreaEvent();
		this.chageStar();
	},
	textAreaEvent : function() {
		let textArea = document.querySelector("textarea.review_textarea");
		document.querySelector("a.review_write_info").addEventListener("click", (evt) => {
			evt.target.closest("a.review_write_info").style.visibility = "hidden";
			textArea.focus();
		});
		textArea.addEventListener("blur", (evt) => {
			if(textArea.value.length === 0) {
				document.querySelector("a.review_write_info").style.visibility = "visible";
			}
		})
	},
	chageStar : function() {
		 document.querySelector("div.rating").addEventListener("click", (evt) => {
			if( evt.target.closest("input") != null ) {
				let clickedValue = parseInt(evt.target.closest("input").value);
				let stars = document.querySelectorAll("input.rating_rdo");
				let countStar = document.querySelector("span.star_rank");
				countStar.classList.remove("gray_star");
				countStar.innerText = clickedValue;
				stars.forEach( star => {
					if( star.value <= clickedValue ) {
						star.classList.add("checked");
						star.checked = true;
					} else {
						star.classList.remove("checked");
						star.checked = false;
					}
				});
			}
		})
	}
}

function formController() {
	this.init();
}
formController.prototype = {
	init : function() {
		this.uploadImg();
		this.countText();
		this.deleteImg();
		this.sendForm();
	},
	uploadImg : function() {
		let img = document.querySelector("#reviewImageFileOpenInput");
		img.addEventListener("change", (evt) =>{
			if(evt.target.files.length > 0) {
				const image = evt.target.files[0];
				if(!this.validImageType(image)) { 
					alert("invalide image file type");
					return;
				}
				const elImage = document.querySelector("img.item_thumb");
				elImage.src = window.URL.createObjectURL(image);
				elImage.closest("li.item").style.display = "inline-block";
			}
		})
	},
	validImageType : function(image) {
		const result = ([ 'image/jpeg',
			  'image/png',
			  'image/jpg' ].indexOf(image.type) > -1);
		return result;
	},
	countText : function() {
		let textArea = document.querySelector("textarea.review_textarea");
		textArea.addEventListener("input", (evt) => {
			let countText = document.querySelector("#count_text");
			countText.innerText = textArea.value.length;
			if(textArea.value.length > 400) {
				textArea.value = textArea.value.substring(0,400);
				countText.innerText = 400;
			}
		})
	},
	deleteImg : function() {
		let deleteBtn = document.querySelector("#delete_img");
		deleteBtn.addEventListener("click", (evt) => {
			let img = document.querySelector("#reviewImageFileOpenInput");
			img.value = "";
			evt.target.closest("li.item").style.display = "none";
		})
	}, 
	sendForm : function() {
		 document.querySelector("div.box_bk_btn").addEventListener("click", function(evt) {
			 if(evt.target.closest("button.bk_btn") != null) {
				 const isValid = this.checkForm();
				 if(isValid) {
					 let data = this.makeFormData();
					 const productId = document.querySelector("#product_id").value;
					 const disId = document.querySelector("#display_info_id").value;
					 ajaxUtil.sendAjaxMultiPartForm("/api/reservationUserComments/"+productId, data)
					 .then(msg => {
						if(msg === "success"){
							window.location.href = "/review/"+disId;
						}
					 })
					 .catch(status => {
						 alert(status);
					 })
				 } else {
					 alert("별점이나 댓글내용을 채워주세요");
				 }
			 }
		 }.bind(this))
	},
	checkForm : function() {
		let textArea = document.querySelector("textarea.review_textarea");
		let countStar = document.querySelector("span.star_rank")
		if(textArea.value.length < 5) {
			return false;
		} else if(textArea.value.trim() === "") {
			return false;
		} else if(countStar.innerText === "0") {
			return false;
		} else {
			return true;
		}
	},
	makeFormData : function() {
		let imgTag = document.querySelector("#reviewImageFileOpenInput");
		let img = imgTag.files[0]; 
		let textArea = document.querySelector("textarea.review_textarea");
		const countStar = parseInt(document.querySelector("span.star_rank").innerText);
		const rsvId = parseInt(document.querySelector("#rsv_id").value);
		let formData = new FormData();
		if(img !== undefined){
			formData.append("img", img);
		}
		formData.append("rsvId", rsvId);
		formData.append("score", countStar);
		formData.append("comment", textArea.value);
		return formData;
	}
}