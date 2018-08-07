 
document.addEventListener("DOMContentLoaded", function() {
	
	loadEtcImage();
	
 document.querySelector("a.bk_more._open").addEventListener("click", function(evt){
	 document.querySelector("div.store_details.close3").setAttribute("class", "store_details");
	 document.querySelector("a.bk_more._open").style.display = "none";
	 document.querySelector("a.bk_more._close").style.display = "block";
 });
 document.querySelector("a.bk_more._close").addEventListener("click", function(evt){
	 document.querySelector("div.store_details").setAttribute("class", "store_details close3");
	 document.querySelector("a.bk_more._close").style.display = "none";
	 document.querySelector("a.bk_more._open").style.display = "block";
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

  document.querySelector("div.prev").addEventListener("click", function(evt){  
	  let parent = document.querySelector("ul.visual_img.detail_swipe");
	  let length = parent.childElementCount;
	  if(length>1){
		  countDown(length);
		  imgMoveLeft(parent);
		  setTimeout(()=>{
			  initMoveLeft(parent);
			  parent.appendChild(parent.children[0]);
		  },100);		  
	  }
  });

  document.querySelector("div.nxt").addEventListener("click", function(evt){  
	  let parent = document.querySelector("ul.visual_img.detail_swipe");
	  let length = parent.childElementCount;
	  if(length>1){
		  countUp(length);
		  initMoveRight(parent);
		  parent.insertAdjacentElement("afterbegin", parent.children[1]);
		  setTimeout(()=>{
			  imgMoveRight(parent);			  
		  },100);		  
	  }
  });
  
  	function countUp(length){
  		let count = document.querySelector("span.num");
  		let imgIndex = parseInt(count.innerText);
  		imgIndex++;
  		if(imgIndex > length){
  			imgIndex = 1;
  		}
  		count.innerText = imgIndex;		  
	}
  
	function countDown(length){
		let count = document.querySelector("span.num");
		  let imgIndex = parseInt(count.innerText);
		  imgIndex--;
		  if(imgIndex == 0){
			  imgIndex = length;
		  }
		  count.innerText =  imgIndex;
	}

	function imgMoveLeft(parent){
		 for(let i=0; i<2; i++){
			  parent.children[i].style.transform = "translateX(-100%)";
			  parent.children[i].style.transition = "all 0.1s ease";
		  }	
	}

	function imgMoveRight(parent){
		 for(let i=0; i<2; i++){
			  parent.children[i].style.transform = "";
			  parent.children[i].style.transition = "all 0.1s ease";
		  }	
	}

	function initMoveLeft(parent){
		for(let i=0; i<2; i++){
			  parent.children[i].style.transform = "";
			  parent.children[i].style.transition = "0s";
		  }	
	}

	function initMoveRight(parent){
		for(let i=0; i<2; i++){
			  parent.children[i].style.transform = "translateX(-100%)";
			  parent.children[i].style.transition = "0s";
		  }	
	}
	
	function loadEtcImage(){
		let oReq = new XMLHttpRequest();

		oReq.addEventListener("load", function() {
			let data = JSON.parse(this.responseText);
			
		});

		oReq.open("GET", "productImages/");
		oReq.send();
	}
})
 

