document.addEventListener('DOMContentLoaded', function () {
	document.getElementById("addTaskForm").addEventListener("submit", function(event){
		var date = document.getElementById('date').value.trim();
		var task = document.getElementById('task').value.trim();
		
		if(date == "" || task == ""){
			alert("情報を全て入力してください");
			event.preventDefault();
		}
	});
});