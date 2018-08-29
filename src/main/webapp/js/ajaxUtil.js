
let ajaxUtil = {
	sendAjaxAsGet : function(url) {
		return new Promise((resolve,reject)=>{
			const oReq = new XMLHttpRequest();
			oReq.open("GET", url);
			oReq.onload = () => resolve(oReq.responseText);
			oReq.onerror = () => reject(oReq.status);
			oReq.send();
		})		
	},
	sendAjaxAsPost : function(url,data) {
		return new Promise((resolve,reject)=>{
			const oReq = new XMLHttpRequest();
			oReq.open("POST", url);
			oReq.setRequestHeader("Content-type", "application/json");
			oReq.onload = () => resolve(oReq.responseText);
			oReq.onerror = () => reject(oReq.status);
			oReq.send(data);
		})		
	},
	sendAjaxAsDelete : function(url) {
		return new Promise((resolve,reject)=>{
			const oReq = new XMLHttpRequest();
			oReq.open("DELETE", url);
			oReq.onload = () => resolve(oReq.responseText);
			oReq.onerror = () => reject(oReq.status);
			oReq.send();
		})		
	},
	sendAjaxMultiPartForm : function(url, data) {
		return new Promise((resolve,reject)=>{
			const oReq = new XMLHttpRequest();
			oReq.open("POST", url);
			oReq.onload = () => resolve(oReq.responseText);
			oReq.onerror = () => reject(oReq.status);
			oReq.send(data);
		})		
	}
}