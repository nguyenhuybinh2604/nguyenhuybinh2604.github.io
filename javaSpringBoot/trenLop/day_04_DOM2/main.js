

function sayHello() {
    alert('Xin chào các bạn');
}

let btn1 = document.querySelector("#btn1");
let para = document.querySelector('p');

// Sử dụng DOM property
let btn2 = document.querySelector("#btn2");
console.log(btn2);
btn2.onclick = function() {
    console.log('Xin chào các bạn 2');
}

// Sử dụng addEventListener
let btn3 = document.querySelector("#btn3");
btn3.addEventListener('click', function() {
    para.classList.toggle('red');
})