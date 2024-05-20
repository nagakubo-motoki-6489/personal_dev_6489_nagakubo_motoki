"use strict";
//クリックされたら
{
  const hamburger = document.querySelector('.hamburger');
  hamburger.addEventListener('click', function() {
  });
}

//実際の処理
{
  const hamburger = document.querySelector('.hamburger');
  const nav = document.querySelector('.nav'); //navクラスの要素を取得

  hamburger.addEventListener('click', function() {
    hamburger.classList.toggle('open');
    nav.classList.toggle('open'); //openクラスを付け外しする
  });
}