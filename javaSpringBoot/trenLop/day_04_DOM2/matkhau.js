const input = document.querySelector("body > input");
const btn = document.querySelector("body > button");
console.log(input);

btn.innerHTML = '';
const iconElement = document.createElement('i');
iconElement.classList.add('fa-solid');
iconElement.classList.add('fa-eye-slash');
btn.appendChild(iconElement);
btn.innerText = "";
console.log(btn);
console.log(iconElement);

btn.onclick = function () {
    let inputValue = input.value;
    if (iconElement.classList.contains("fa-eye-slash")) {
        
        // btn.innerText = "Show";
        input.setAttribute('type', 'password');
        inputValue = input.value.replace(/./g, '*');

        iconElement.classList.remove('fa-eye-slash');
        iconElement.classList.add('fa-eye');
    } else {
        // btn.innerText = "Hide";
        input.setAttribute('type', 'text');
        inputValue.replace(/\*/g, '');

        iconElement.classList.remove('fa-eye');
        iconElement.classList.add('fa-eye-slash');      
    }
};