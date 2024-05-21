const WEEKDAYS = ["日", "月", "火", "水", "木", "金", "土"];
    
const calendar = document.querySelector(".calendar");
const monthHeader = document.querySelector("#month");
const weeks = document.querySelectorAll(".week");

function displayCalendar() {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();

    const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

    monthHeader.innerHTML = `${currentYear}年${currentMonth + 1}月`;

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

displayCalendar();