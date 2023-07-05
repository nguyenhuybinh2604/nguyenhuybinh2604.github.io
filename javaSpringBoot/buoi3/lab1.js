// Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
const h1 = document.getElementById("heading");
h1.style.color = "red";
h1.textContent = h1.textContent.toUpperCase();
console.log(h1);

// Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const paraList = document.getElementsByClassName("para");
Array.from(paraList).map(e => e.style.color = "blue");
Array.from(paraList).map(e => e.style.fontSize = "20px");
console.log(paraList);

// Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
const facebookEl = document.createElement('a');
facebookEl.href = "http://www.facebook.com";
facebookEl.innerText = "Facebook";

const p3 = document.querySelector(".para-3")
p3.insertAdjacentElement("afterend",facebookEl);
console.log(p3);

// Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document.getElementById("title");
title.innerText="";
title.innerText="Đây là thẻ tiêu đề"

// Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
const description = document.querySelector(".description");
description.className = "description " + "text-bold";
console.log(description);

// Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const btn = document.createElement("button");
btn.innerText = "Click me";
p3.replaceWith(btn);

// Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
const p2 = document.querySelector(".para-2");
// const p2Copy = p2.cloneNode(true);
p2.insertAdjacentElement("afterend",p2.cloneNode(true));

// Xóa thẻ có class=“para-1”
document.body.removeChild(document.querySelector(".para-1"));