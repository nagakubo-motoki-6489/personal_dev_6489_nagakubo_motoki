// 期限日セルの取得
var deadlineCells = document.getElementsByClassName('deadline');

// 期限日に近い場合は色変えとクラス追加（縁取り用）
for (var i = 0; i < deadlineCells.length; i++) {
    if (isDeadlineNear(deadlineCells[i])) {
        deadlineCells[i].style.backgroundColor = '#FFD700';
        deadlineCells[i].classList.add("warning");
    }
    if (isDeadlineOver(deadlineCells[i])) {
        deadlineCells[i].style.backgroundColor = 'red';
        deadlineCells[i].classList.add("warning");
    }
}

// 期限が近いかどうかをチェック
function isDeadlineNear(deadline) {
    var twoDaysLater = new Date();
    twoDaysLater.setDate(twoDaysLater.getDate() + 2);


    var deadlineDate = new Date(deadline.textContent);

    // 確認用コンソール（正しく処理されているか）
    console.log(deadline.textContent);
    console.log(deadlineDate);
    console.log(twoDaysLater);
    console.log(deadlineDate <= twoDaysLater);

    return deadlineDate <= twoDaysLater;
}
function isDeadlineOver(deadline) {
    var twoDaysLater = new Date();
    twoDaysLater.setDate(twoDaysLater.getDate());

    var deadlineDate = new Date(deadline.textContent);

    return deadlineDate < twoDaysLater;
}
