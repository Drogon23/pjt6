
document.addEventListener("DOMContentLoaded", function() {
	detail.loadEtcImage("/productImages/");
	detail.loadComments("/api/reservationUserComments/");
	detail.addClickEvent();
}) 

const detail = {		
	addClickEvent : function() {
		$("a.bk_more._open").click(()=>{
			$("div.store_details.close3").toggleClass("close3");
			$("a.bk_more._open").css("display", "none");
			$("a.bk_more._close").css("display", "block");
		});
		
		$("a.bk_more._close").click(()=>{
			$("div.store_details").toggleClass("close3");
			$("a.bk_more._close").css("display", "none");
			$("a.bk_more._open").css("display", "block");
		});
		document.querySelector("li.item._path").addEventListener("click", function(evt){
			 document.querySelector("div.detail_area_wrap").setAttribute("class", "detail_area_wrap hide");
			 document.querySelector("div.detail_location.hide").setAttribute("class", "detail_location");
			 document.querySelector("li.item._detail").children[0].setAttribute("class", "anchor");
			 document.querySelector("li.item._path").children[0].setAttribute("class", "anchor active");
		 });
		  document.querySelector("li.item._detail").addEventListener("click", function(evt){
			 document.querySelector("div.detail_location").setAttribute("class", "detail_location hide");
			 document.querySelector("div.detail_area_wrap.hide").setAttribute("class", "detail_area_wrap");
			 document.querySelector("li.item._detail").children[0].setAttribute("class", "anchor active");
			 document.querySelector("li.item._path").children[0].setAttribute("class", "anchor");	
		 });
	},
	loadComments : function(url) {
		let productId = parseInt(document.querySelector("#productId").value);
		utilities.sendAjaxAsGet(url+productId)
		.then(data =>{
			return JSON.parse(data);
		})
		.catch(status=>{
			console.log(status);
		})
		.then(jsonData=>{
			if(jsonData.commentsCount>3){
				jsonData.commentsInfo.splice(3,3);
			}
			const compiledTemplate = detail.compileCommentTemplate(jsonData);
			detail.insertCompiledCommentTemplate(compiledTemplate);
			detail.changeCommentData(jsonData);
		})	
	},	
	compileCommentTemplate : function(data) {
		const commentTemplate = document.querySelector("#comment").innerText;
		let bindTemplate = Handlebars.compile(commentTemplate);
		
		Handlebars.registerHelper("hideEmail", function(reservationEmail) {
			  return reservationEmail.substring(0,4)+"****";
			});
		Handlebars.registerHelper("dateFormat", function(reservationDate) {
			let date = new Date(reservationDate);
			date = date.toLocaleDateString().replace(/\s/g, "");
			  return date;
			});
		return bindTemplate(data);
	},
	insertCompiledCommentTemplate : function(template) {
		document.querySelector("ul.list_short_review").innerHTML += template;
	},
	changeCommentData : function(data) {
		document.querySelector("#avgScore").innerText = data.avgScore;
		document.querySelector("#graph_value").style.width = data.avgScore/5*100+"%";
		document.querySelector("#commentCounts").innerText = data.commentsCount+"건";
	},
	loadEtcImage : function(url) {
		let displayInfoId = parseInt(document.querySelector("#displayInfoId").value);
		utilities.sendAjaxAsGet(url+displayInfoId+"/etc")
		.then(data =>{
			return JSON.parse(data);
		})
		.catch(status =>{
			console.log(status);
		})		
		.then(jsonData =>{
			if(jsonData.productEtcImageList.length>0){
				detail.insertEtcImage(jsonData);
				detail.changeImgDisplay();
				detail.imgMoveEvent();
			}
		});		
	},
	insertEtcImage : function(data) {
		const template = document.querySelector("#etc_image").innerText;
		let bindTemplate = Handlebars.compile(template);
		document.querySelector("ul.visual_img.detail_swipe").innerHTML+= bindTemplate(data.productEtcImageList[0]);
	},
	changeImgDisplay : function() {
		document.querySelector("#total_img_count").innerText = 2;
		document.querySelector("div.prev").style.display = "block";
		document.querySelector("div.nxt").style.display = "block";
	},
	imgMoveEvent : function() {
		let parent = document.querySelector("ul.visual_img.detail_swipe");
		let length = parent.childElementCount;
		document.querySelector("div.nxt").addEventListener("click", function(evt){  
			detail.imgMoveLeft(parent);
			setTimeout(function() {
				detail.initImgMoveLeft(parent);
				parent.appendChild(parent.children[0]);
				},100);
			detail.countUp(length);
		});
		document.querySelector("div.prev").addEventListener("click", function(evt){  
			detail.initImgMoveRight(parent);
			parent.insertAdjacentElement("afterbegin", parent.children[1]);
			setTimeout(function() {
				detail.imgMoveRight(parent);			  
				},100);		
			detail.countDown(length);
		});
	},
	countUp : function(length) {
		let count = document.querySelector("span.num");
  		let imgIndex = parseInt(count.innerText);
  		imgIndex++;
  		if(imgIndex > length){
  			imgIndex = 1;
  		}
  		count.innerText = imgIndex;		
	},
	countDown : function(length) {
		let count = document.querySelector("span.num");
		let imgIndex = parseInt(count.innerText);
		imgIndex--;
		if(imgIndex == 0){
			imgIndex = length;
		}
		count.innerText =  imgIndex;
	},
	imgMoveLeft : function(parent) {
		for(let i=0; i<2; i++){
			parent.children[i].style.transform = "translateX(-100%)";
			parent.children[i].style.transition = "all 0.1s ease";
		}
	},
  	imgMoveRight : function(parent) {
  		for(let i=0; i<2; i++){
  			parent.children[i].style.transform = "";
  			parent.children[i].style.transition = "all 0.1s ease";
		}	
	},
	initImgMoveLeft : function(parent) {
		for(let i=0; i<2; i++){
			parent.children[i].style.transform = "";
			parent.children[i].style.transition = "0s";
		}	
	},
	initImgMoveRight : function(parent) {
		for(let i=0; i<2; i++){
			parent.children[i].style.transform = "translateX(-100%)";
			parent.children[i].style.transition = "0s";
		}	
	}
}

