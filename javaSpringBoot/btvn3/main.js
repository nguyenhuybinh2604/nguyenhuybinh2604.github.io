// Cau 1
const textTag = document.createElement("div");
textTag.id = "text";
textTag.style.color = "#777";
textTag.style.fontSize = "2rem";
textTag.innerHTML = "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.";
console.log(textTag);
document.body.prepend(textTag);

// Cau 2
const liList = document.querySelectorAll("#text + ul > li");
for (let i = 0; i < liList.length; i++) {
    liList[i].style.color = "blue";
}
console.log(liList);

// Cau 3
// 1 Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
// Tao moi 3 tag
const li8 = document.createElement("li");
const li9 = document.createElement("li");
const li10 = document.createElement("li");

// Ghi noi dung
li8.textContent = 'Item 8';
li9.textContent = 'Item 9';
li10.textContent = 'Item 10';

// Xac dinh ul #list
const ul = document.querySelector("#list");

// Them 3 tag moi vao cuoi ul
ul.appendChild(li8);
ul.appendChild(li9);
ul.appendChild(li10);
console.log(ul);

// 2
// Sua li1 thanh mau do
const li1 = document.querySelector("#list > li");
li1.style.color = "red";
console.log(li1);

// 3 Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
const li3 = document.querySelector("#list :nth-child(3)");
li3.style.backgroundColor = "green";
console.log(li3);

// 4 Remove thẻ <li> 4
const li4 = document.querySelector("#list :nth-child(4)");
ul.removeChild(li4);

// 5 Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ
const li4New = document.createElement("li");
li4New.textContent = "Replacement for last 4th list item.";
li3.insertAdjacentElement("afterend",li4New);