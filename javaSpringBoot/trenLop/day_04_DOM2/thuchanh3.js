// Tao the div
let newCircle = document.createElement("div");
// Add class circle
newCircle.classList.add("circle");
// Gan vi tri top-left

// Tao the div
let newSquare = document.createElement("div");
// Add class circle
newSquare.classList.add("square");
// Gan vi tri top-left

document.addEventListener("click", function (event) {

    // let element = document.querySelector('.circle');
    // if (element) {
    //     element.remove();
    // };

    // Tao the div
    // let newCircle = document.createElement("div");
    // Add class circle
    // newCircle.classList.add("circle");
    // Gan vi tri top-left

    let top = event.offsetY;
    let left = event.offsetX;

    newCircle.style.top = (top - 50) + "px";
    newCircle.style.left = (left - 50) + "px";
    // Cho the div xuat hien trong body
    document.body.appendChild(newCircle);
});

function generateRandomHexColor() {
    // Generate a random color code using the hexadecimal notation
    const colorCode = Math.floor(Math.random() * 16777215).toString(16);

    // If the generated color code is less than 6 digits, pad it with zeros
    const paddedColorCode = colorCode.padStart(6, '0');

    // Return the padded color code with the '#' prefix
    return '#' + paddedColorCode;
}

document.addEventListener("keypress", function (event) {

    let left = Math.floor(Math.random() * window.innerWidth);
    let top = Math.floor(Math.random() * window.innerHeight);

    if (event.key == "Enter") {
        newCircle.style.display = "";
        newSquare.style.display = "none";
        newCircle.style.top = (top - 50) + "px";
        newCircle.style.left = (left - 50) + "px";
        // Cho the div xuat hien trong body
        document.body.appendChild(newCircle);
        console.log(newCircle.style.left);
    }
    else if (event.code == "Space") {
        console.log(event);
        newCircle.style.display = "none";
        newSquare.style.display = "";
        newSquare.style.top = top + "px";
        newSquare.style.left = left + "px";
        // Cho the div xuat hien trong body
        document.body.appendChild(newSquare);
    }
    else {
        this.body.style.backgroundColor = generateRandomHexColor();
    };
});

document.addEventListener('keydown', function(event) {
  if (event.key === 'ArrowLeft') {
    console.log('Left arrow key pressed');
    newCircle.style.display = "";
    newSquare.style.display = "none";
    newCircle.style.left = (parseInt(newCircle.style.left, 10) - 20) + "px";
  }
  else if (event.key === 'ArrowRight') {
    console.log('Left arrow key pressed');
    newCircle.style.display = "";
    newSquare.style.display = "none";
    newCircle.style.left = (parseInt(newCircle.style.left, 10) + 20) + "px";
  }
  else if (event.key === 'ArrowUp') {
    console.log('Left arrow key pressed');
    newCircle.style.display = "";
    newSquare.style.display = "none";
    newCircle.style.top = (parseInt(newCircle.style.top, 10) - 20) + "px";
  }
  else if (event.key === 'ArrowDown') {
    console.log('Left arrow key pressed');
    newCircle.style.display = "";
    newSquare.style.display = "none";
    newCircle.style.top = (parseInt(newCircle.style.top, 10) + 20) + "px";
  }
});

