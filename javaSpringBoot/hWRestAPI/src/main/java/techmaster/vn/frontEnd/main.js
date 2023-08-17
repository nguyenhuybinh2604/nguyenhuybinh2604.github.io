// type = 1: color name
// type = 2: hex
// type = 3: rgb
let color;
const getRandomColor = (type) => {
    fetch("http://localhost:8080/random-color?type=" + type)
        .then(rs => rs.json())
        .then(data => {
            color = data.color;
            console.log(data);
            console.log(color);
        })
        .catch(err => console.log(err))
}

function changeBackGround(color) {
    // Change background color to red
    document.body.style.backgroundColor = color;
}


const btnColorName = document.getElementById("btnColorName");
const btnHexCode = document.getElementById("btnHexCode");
const btnRGBCode = document.getElementById("btnRGBCode");

// Add a click event listener to the button
btnColorName.addEventListener("click", function () {
    // Code to execute when the button is clicked
    getRandomColor(1);
    changeBackGround(color);
});

// Add a click event listener to the button
btnHexCode.addEventListener("click", function () {
    // Code to execute when the button is clicked
    getRandomColor(2);
    changeBackGround(color);
});

// Add a click event listener to the button
btnRGBCode.addEventListener("click", function () {
    // Code to execute when the button is clicked
    getRandomColor(3);
    changeBackGround(color);
});

let bmi;

const getBMI = (height, weight) => {
    fetch("http://localhost:8080/bmi?height=" + height + "&weight=" + weight + "")
        .then(rs => rs.json())
        .then(data => {
            bmi = data;
            console.log(data);
            // console.log(color);
        })
        .catch(err => console.log(err))
}

heightInput = document.getElementById("heightInput");
weightInput = document.getElementById("weightInput");

const btnGetBMI = document.getElementById("btnGetBMI");

// Add a click event listener to the button
btnGetBMI.addEventListener("click", function () {
    // Code to execute when the button is clicked
    console.log(heightInput.value);
    console.log(weightInput.value);
    getBMI(heightInput.value, weightInput.value);
});

