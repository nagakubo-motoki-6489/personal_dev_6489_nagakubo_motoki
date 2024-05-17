// トグルボタンの要素を取得
var toggleButton = document.getElementById('toggle');

// ボタンの状態が変更されたときに実行
toggleButton.addEventListener('change', function() {
    // ボタンの状態を取得
    var toggleCheck = toggleButton.checked;

    // 取得した状態に応じて処理を行う
    if(toggleCheck) {
        console.log('yes');
        //Bodyのフォントを全て変える
        document.body.setAttribute("theme","dot");
        //ボタンのフォントを全て変える
        var buttons = document.querySelectorAll("button");
		buttons.forEach(function(button) {
    		button.style.fontFamily = "'DotGothic16', sans-serif";
		});
    } else {
        console.log('no');
        //Bodyのフォントを全て変える
        document.body.setAttribute("theme","")
        //ボタンのフォントを全て変える
        var buttons = document.querySelectorAll("button");
		buttons.forEach(function(button) {
    		button.style.fontFamily = "'Yusei Magic', sans-serif";
		});
	}});