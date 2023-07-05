const heading = document.getElementById("heading");
console.log(heading);

const heading1 = document.querySelector("#heading");
console.log(heading1);

// ? truy cap p1, p2, p3
const p1 = document.querySelector("p");
const p2 = document.querySelector(".para");
const p3 = document.querySelector("p:nth-of-type(3)");

console.log(p3);

const p4 = document.querySelector(".para + .para");

console.log(p4);

const p5 = document.querySelector("div + ul > li:nth-of-type(3)");

console.log(p5);

const paraList = document.querySelectorAll("p");
console.log(paraList);

// thuoc tinh sua bang js la inline style - do uu tien gan cao nhat
// property name (ten thuoc tinh) viet theo camelCase (thay vi snake)
for (let i = 0; i < paraList.length; i++) {
    paraList[i].style.color = "blue";
    paraList[i].style.backgroundColor = "pink";
}

// convert nodeList sang array
Array.from(paraList).map(e => console.log(e));

// get/set content
const ul1 = document.querySelector(".box ul");
console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

heading.innerHTML = "Xin chào";
heading.innerHTML = "<span>Hello</span>";

const socialMedia = [
    {
        label: "Google",
        link: "http://www.google.com"
    },
    {
        label: "Facebook",
        link: "http://www.facebook.com"
    },
    {
        label: "Instagram",
        link: "http://www.instagram.com"
    }
]



const socialMediaEl = document.querySelector(".social-media");
const renderSocialMedia = list => {
    socialMediaEl.innerHTML = "";
    let html = "";
    for (let i = 0; i < socialMedia.length; i++) {
        html = html + `<a href= ${socialMedia[i].link} style="display: block">${socialMedia[i].label}</a> `
    }
    socialMediaEl.innerHTML = html;
}

renderSocialMedia(socialMedia);

const btn = document.createElement("button");
btn.innerText = "Click me";
console.log(btn);

document.body.prepend(btn);

const btnCopy = btn.cloneNode(true);
const scriptEl = document.querySelector("script");

document.body.appendChild(btnCopy);
document.body.insertBefore(btnCopy,scriptEl);

// Insert part 2
// code html
p3.insertAdjacentHTML("afterend", `<input type="text" placeholder="Enter name"/>`)
// object
p1.insertAdjacentElement("beforebegin", btn.cloneNode(true));

// Xóa
// Get parent truc tiep
document.body.removeChild(p1);
// Get parent gian tiep
p2.parentElement.removeChild(p2);

// Thay thế
let newElement = document.createElement('h1');
newElement.innerText = "Xin chào các bạn adsada";
p3.parentNode.replaceChild(newElement,p3);