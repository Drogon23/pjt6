document.addEventListener("DOMContentLoaded", function() {
	
	review.loadComments("/api/reservationUserComments/");
	review.addScrollEvent();
	
})
const review = {
	loadComments : function(url) {
		let productId = parseInt(document.querySelector("#productId").value);
		let start = parseInt(document.querySelector("#start").value);
		utilities.sendAjaxAsGet(url+productId+"?start="+start)
		.then(data =>{
			return JSON.parse(data);
		})
		.catch(status=>{
			console.log(status);
		})
		.then(jsonData=>{
			const compiledTemplate = review.compileCommentTemplate(jsonData);
			review.insertCompiledCommentTemplate(compiledTemplate);
			review.changeCommentData(jsonData);
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
		document.querySelector("#start").value = data.commentsInfo.length + parseInt(document.querySelector("#start").value);
		
	},
	addScrollEvent : function() {
		let reviewBox = document.querySelector("html");
		window.addEventListener("scroll", function(evt) {
			if (reviewBox.scrollTop == reviewBox.scrollHeight - reviewBox.clientHeight) {
				review.loadComments("/api/reservationUserComments/");
			}	
		});
	}
	
}

