const target = document.querySelectorAll('[id^="tooltipButton"]');
const popup = document.querySelectorAll('[id^="toolTip"]');
console.log(target);
console.log(popup);


 //ボタンにhoverした時
target.forEach(function(tag, index){
	tag.addEventListener("mouseover", function(){
		var num = index;
		console.log(popup[num])
		 popup[num].style.display = 'block';
	})
});

// ボタンから離れた時
target.forEach(function(tag, index){
	tag.addEventListener('mouseleave', function() {
		var num = index;
		console.log(popup[num])
  		popup[num].style.display = 'none';
	})
});