document.addEventListener("DOMContentLoaded", function() {
	let tab = new tabUiController();	
	let rsv = new reserveController();
})

function tabUiController() {
	this.init();
}
tabUiController.prototype = {
	init : function() {
		this.clickEvent();
	},
	clickEvent : function() {
		document.querySelector("ul.summary_board").addEventListener("click", function(evt) {
			if( evt.target.closest("a.link_summary_board") != null ) {
				let beforeTab = document.querySelector("a.link_summary_board.on");
				beforeTab.classList.remove("on");
				let tab = evt.target.closest("a.link_summary_board");
				tab.classList.add("on");
				this.showItems(tab);
			}
		}.bind(this))
	},
	showItems : function(tab) {
		let confirmedList = document.querySelector("li.card.confirmed");
		let usedList = document.querySelector("li.card.used");
		let canceledList = document.querySelector("li.card.used.cancel");
		
		let tabDetail = tab.querySelector("i.spr_book2");
		if ( tabDetail.classList.contains("ico_book2") ) {
			confirmedList.style.display = "inline-block";
			usedList.style.display = "inline-block";
			canceledList.style.display = "inline-block";
		} else if( tabDetail.classList.contains("ico_book_ss") ) {
			confirmedList.style.display = "inline-block";
			usedList.style.display = "none";
			canceledList.style.display = "none";
		} else if( tabDetail.classList.contains("ico_check") ) {
			confirmedList.style.display = "none";
			usedList.style.display = "inline-block";
			canceledList.style.display = "none";
		} else if( tabDetail.classList.contains("ico_back") ) {
			confirmedList.style.display = "none";
			usedList.style.display = "none";
			canceledList.style.display = "inline-block";
		}
	}
}

function reserveController() {
	this.init();
}

reserveController.prototype = {
	init : function() {
		let confirmedList = document.querySelector("li.card.confirmed");
		if( confirmedList.childElementCount > 1 ) {
			this.cancelReserve();
		}
		this.writeReview();
	},
	cancelReserve : function() {
		document.querySelector("li.card.confirmed").addEventListener("click", function(evt) {
			if( evt.target.closest("button.btn") != null ) {
				if( window.confirm("취소하시겠습니까?") ) {
					let parent = evt.target.closest("div.card_detail");
					const rsvId = parent.querySelector("em.booking_number").getAttribute("data-id");
					ajaxUtil.sendAjaxAsDelete("/api/reservationInfos/"+rsvId)
					.then(msg => {
						if( msg === "success" ) {
							this.moveCanceledCard(evt);
							let beforeCount = document.querySelector("#before_count");
							beforeCount.innerText = parseInt(beforeCount.innerText)-1;
							let canceledCount = document.querySelector("#canceled_count");
							canceledCount.innerText = parseInt(canceledCount.innerText)+1;
						}
					})
				} 
			}
		}.bind(this))
	},
	writeReview : function() {
		document.querySelector("li.card.used").addEventListener("click", function(evt) {
			if( evt.target.closest("button.btn") != null ) {
				let parent = evt.target.closest("div.card_detail");
				const displayId = parent.querySelector("h4.tit").getAttribute("data-dp");
				const rsvId = parent.querySelector("em.booking_number").getAttribute("data-id");
				window.location.href = "/reviewWrite/"+displayId+"?rsvId="+rsvId;
			}
		})
	},
	moveCanceledCard : function(evt) {
		let card = evt.target.closest("article.card_item");
		let canceledList = document.querySelector("li.card.used.cancel");
		canceledList.appendChild(card);
		let cancelBtn = card.querySelector("div.booking_cancel");
		cancelBtn.remove();
	}
}