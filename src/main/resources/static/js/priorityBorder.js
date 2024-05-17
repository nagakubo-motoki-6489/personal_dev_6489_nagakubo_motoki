// 優先度セルの取得
var priorityCells = document.getElementsByClassName('priority');

console.log(priorityCells);
for (var i = 0; i < priorityCells.length; i++) {
    if (priorityCells[i].textContent =="高") {
        priorityCells[i].classList.add("priorityHigh");
    }
    if (priorityCells[i].textContent == "中") {
        priorityCells[i].classList.add("priorityMiddle");
    }
    if (priorityCells[i].textContent == "低") {
        priorityCells[i].classList.add("priorityLow");
    }
}