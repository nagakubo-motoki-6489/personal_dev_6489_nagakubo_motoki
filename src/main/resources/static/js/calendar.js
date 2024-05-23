const WEEKDAYS = ["日", "月", "火", "水", "木", "金", "土"];
    
const calendar = document.querySelector(".calendar");
const monthHeader = document.querySelector("#month");
const weeks = document.querySelectorAll(".week");

displayCalendar();

function displayCalendar() {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();

    const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

    monthHeader.innerHTML = `${currentYear}/${(0 + currentMonth + 1).toString().padStart(2, '0')}`;//頭文字に0を入れられるように

    let dayCounter = 1;
    for (let i = 0; i < weeks.length; i++) {
        const week = weeks[i];
        week.innerHTML = "";

        if (i === 0) {
			//最初の週
            for (let j = 0; j < firstDayOfMonth; j++) {
                const day = document.createElement("div");
                day.classList.add("day", "disabled");
                week.appendChild(day);
            }
            for (let j = firstDayOfMonth; j < 7; j++) {
                const day = document.createElement("div");
                day.classList.add("day");
                day.textContent = dayCounter;
                week.appendChild(day);
                dayCounter++;
            }
        } else {
			//以降の週
            for (let j = 0; j < 7; j++) {
                if (dayCounter > daysInMonth) {
                    const day = document.createElement("div");
                    day.classList.add("day", "disabled");
                    week.appendChild(day);
                } else {
                    const day = document.createElement("div");
                    day.classList.add("day");
                    day.textContent = dayCounter;
                    week.appendChild(day);
                    dayCounter++;
                }
            }
        }
    }
}

function addSchedule() {
//	console.log(tasks);
//	console.log(tasks[0].closingDate);

//獲得した各タスクの数だけ処理を繰り返す
	for( let i = 0; i < tasks.length; i++){
		//thymeleafで各タスクを取得
//		console.log(tasks[i].closingDate);
		var deadlineDate = new Date(tasks[i].closingDate);//期限をDate型で新しく宣言
//		console.log(deadlineDate.getMonth() + 1)//月は0から始まるから+1しないといけないみたい
//		console.log(deadlineDate.getDate())
		
		//ヘッダーの文字列をDateで入手（Dayは適当に1で付けた）
		var dateStart = monthHeader.textContent + "/01";
//		console.log(dateStart);
		var currentMonth = new Date(dateStart);
//		console.log(currentMonth);
		
		//クラス名dayを全て取得
		var day = document.querySelectorAll(".day");
//		day.forEach(function(element) {
//    		console.log(element.textContent);
//			});
			
		//月が同じであれば
		if(deadlineDate.getMonth() == currentMonth.getMonth()){
			console.log("月が同じだから今から走るよ");
			day.forEach(function(element) {
				if(element.textContent == deadlineDate.getDate().toString()){
    				console.log(element);
    				element.classList.add("existEvent");
    				
    				var alert = (tasks[i].title + "\n" + "→" + tasks[i].memo);
 
    				element.addEventListener('click', function(){
						showInfo(alert);	
						}
						);//addEventListenerの処理がここまで
    				
					}
					
					
				});//forEach の処理がここまで
		}//if(deadlineDate.getMonth() == currentMonth.getMonth()) の処理がここまで
		
	}//for( let i = 0; i < tasks.length; i++) の処理がここまで
	
}//function addSchedule() の処理がここまで

function showInfo(title){
	window.alert(title);
}