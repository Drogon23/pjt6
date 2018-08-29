
function uiController(form) {
	this.form = form
	this.addTicketClickEvent();
	this.sendForm();
}

uiController.prototype = {
	addTicketClickEvent : function() {
		const minus = "btn_plus_minus spr_book2 ico_minus3";
		const plus = "btn_plus_minus spr_book2 ico_plus3";
		document.querySelector("div.ticket_body").addEventListener("click", function(evt) {
			if (evt.target.className === minus) {
				this.minusTicket(evt);
				this.form.allFormCheck();
			} else if (evt.target.className === plus) {
				this.plusTicket(evt);
				this.form.allFormCheck();
			}
		}.bind(this));
		this.toggleAgreement();
	},
	minusTicket : function(evt) {
		let parent = evt.target.parentElement;
		let ticketCount = parent.querySelector("input.count_control_input");
		ticketCount.value--;
		ticketCount.setAttribute("value",ticketCount.value);
		this.changePrice(parent, ticketCount, evt);
		if (ticketCount.value === "0") {
			this.disabledMinusBtn(evt,ticketCount);
		}
		document.querySelector("#totalCount").innerText--;
	},
	disabledMinusBtn : function(evt, ticketCount) {
		evt.target.className += " disabled";
		ticketCount.className += " disabled";
	},
	plusTicket : function(evt) {
		let parent = evt.target.parentElement;
		let ticketCount = parent.querySelector("input.count_control_input");
		let priceId = ticketCount.getAttribute("data-id");
		if (ticketCount.value === "0") {
			this.enableMinusBtn(parent, ticketCount);
		}
		ticketCount.value++;
		ticketCount.setAttribute("value",ticketCount.value);
		this.changePrice(parent, ticketCount, evt);
		document.querySelector("#totalCount").innerText++;
	},
	enableMinusBtn : function(parent, ticketCount) {
			let minusElement = parent.querySelector(".ico_minus3");
			minusElement.classList.toggle("disabled");
			ticketCount.classList.toggle("disabled");
	},
	changePrice : function(parent, ticketCount, evt) {
		let priceBox = parent.parentElement.querySelector("div.individual_price");
		let grandParent = parent.closest("div.qty");
		let price = grandParent.querySelector("span.price").innerText;
		price = parseInt(price.replace(/,/g, ""));
		priceBox.querySelector("span.total_price").innerText = (price*ticketCount.value).toLocaleString();
	},
	toggleAgreement : function() {
		document.querySelector("div.section_booking_agreement").addEventListener("click", function(evt) {
			if (evt.target.closest("a.btn_agreement") != null){
				if (evt.target.closest("div.agreement").classList.contains("open")) {
					evt.target.closest("div.agreement").classList.toggle("open");
				} else {
					evt.target.closest("div.agreement").classList.add("open");
				}
			}
		})
	},
	sendForm : function() {
		document.querySelector("div.bk_btn_wrap").addEventListener("click", function(evt) {
			if(evt.target.closest("div.bk_btn_wrap").classList.contains("disable") === false) {
				let form = document.querySelector("form.form_horizontal");
				let data = this.collectData(form);
			    ajaxUtil.sendAjaxAsPost("/api/reservationInfos",JSON.stringify(data))
			    .then(data =>{
			    	return JSON.parse(data);
			    })
				.catch(status =>{
					console.log(status);
				})
			    .then(jsonData => {
			    	if(jsonData.id != 0) {
			    		window.location.href = "/";
			    	}else {
			    		alert("정보를 제대로 입력해 주세요");
			    		window.location.href = window.location.href;
			    	}
			    });
			}
		}.bind(this))
	},
	collectData : function(form) {
		let formData = new FormData(form);
		let convertedJSON = {};

	    formData.forEach(function(value, key) { 
	        convertedJSON[key] = value.trim();
	    });
	    let priceElement = document.querySelectorAll("input.count_control_input");
	    let prices = [];
	    priceElement.forEach((element)=>{
	    	if(element.getAttribute("value") != "0") {
	    		prices.push({
		    		productPriceId : element.getAttribute("data-id"),
		    		count : element.getAttribute("value")
		    	});
	    	}
	    });
	    convertedJSON.prices = prices;
	    return convertedJSON;
	},
}

function formCheck(nameValid, phoneValid, emailValid, agreeValid){
	this.nameValid = nameValid;
	this.phoneValid = phoneValid;
	this.emailValid = emailValid;
	this.agreeValid = agreeValid;
	this.init();
}
formCheck.prototype = {
	init : function() {
		this.nameCheck();
		this.phoneCheck();
		this.emailCheck();
		this.agreementCheck();
	},
	nameCheck : function() {
		document.querySelector("#name").addEventListener("change", function(evt) {
			let parent = evt.target.parentElement;
			this.nameValid = (/^[가-힣]*$|[a-zA-Z]/).test(evt.target.value);
			if (this.nameValid === false) {
				parent.querySelector("div.warning_msg").style.visibility = "visible";
				setTimeout(()=>{
					parent.querySelector("div.warning_msg").style.visibility = "hidden";
				}, 1000);
			}
			this.allFormCheck();
		}.bind(this))
	},
	phoneCheck : function() {
		document.querySelector("#tel").addEventListener("change", function(evt) {
			let parent = evt.target.parentElement;
			this.phoneValid = (/^\d{3}-\d{3,4}-\d{4}$/).test(evt.target.value);
			if (this.phoneValid === false) {
				parent.querySelector("div.warning_msg").style.visibility = "visible";
				evt.target.setAttribute("data-valid", 0);
				setTimeout(()=>{
					parent.querySelector("div.warning_msg").style.visibility = "hidden";
				}, 1000);
			}
			this.allFormCheck();
		}.bind(this))
	},
	emailCheck : function() {
		document.querySelector("#email").addEventListener("change", function(evt) {
			let parent = evt.target.parentElement;
			this.emailValid = (/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i).test(evt.target.value);
			if (this.emailValid === false) {
				parent.querySelector("div.warning_msg").style.visibility = "visible";
				evt.target.setAttribute("data-valid", 0);
				setTimeout(()=>{
					parent.querySelector("div.warning_msg").style.visibility = "hidden";
				}, 1000);
			}
			this.allFormCheck();
		}.bind(this))
	},
	agreementCheck : function() {
		document.querySelector("label.label.chk_txt_label").addEventListener("click",function(evt){
			this.agreeValid = !this.agreeValid;		
			this.allFormCheck();
		}.bind(this))
	},
	allFormCheck : function() {
		const totalCount = parseInt(document.querySelector("#totalCount").innerText);
		if (this.nameValid && this.phoneValid && this.emailValid && this.agreeValid && totalCount) {
			document.querySelector("div.bk_btn_wrap").classList.remove("disable");
		} else {
			document.querySelector("div.bk_btn_wrap").className = "bk_btn_wrap disable";		
		}
	}
}


document.addEventListener("DOMContentLoaded", function() {
	let form = new formCheck(false, false, false, false);
	let ui = new uiController(form);
	
})