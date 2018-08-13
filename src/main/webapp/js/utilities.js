
let utilities = {
	sendAjaxAsGet : function(url){
		return new Promise((resolve,reject)=>{
			const oReq = new XMLHttpRequest();
			oReq.open("GET", url);
			oReq.onload = () => resolve(oReq.responseText);
			oReq.onerror = () => reject(oReq.status);
			oReq.send();
		})		
	}
}