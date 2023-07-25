// Yêu cầu
// Render số box = số lượng màu ra ngoài màn hình bằng javascript (5 box)
// Box được tạo bởi thẻ div, có class=“box”, background tương ứng với từng mã màu
// Cập nhật số lượng total box trong thẻ <span> có class “points”
// Khi bấm vào box bất kỳ thì box đó biến mất, đồng thời số lượng total box giảm đi 1
// Khi click vào nút “more box” thì số lượng box tăng lên 5 (tương ứng với 5 màu trong mảng colors), 
// đồng thời số lượng total box cũng tăng lên 5
let colors = [
    '#3498db',
    '#9b59b6',
    '#e74c3c',
    '#2c3e50',
    '#d35400',
];

// so dem SL box
let numberOfBoxes = 0;

const boxFrame = document.querySelector(".boxes");
const pointElement = document.querySelector(".points");

// ham lay ma mau random
function randomIndex() {
    return Math.floor(Math.random() * 5);
}

// ham tao box theo ma mau
function createBox(colorIndex) {
    let newBox = document.createElement("div");
    newBox.classList.add("box");
    newBox.style.backgroundColor = colors[colorIndex];
    // ham remove box
    newBox.onclick = function () {
        newBox.remove();
        numberOfBoxes--;
        pointElement.innerText = " " + numberOfBoxes;
    }
    numberOfBoxes++;
    pointElement.innerText = " " + numberOfBoxes;
    boxFrame.appendChild(newBox);
}

// tao 05 box dau tien;lpp;
for (let i = 0; i < 5; i++) {
    createBox(i);
}

// tao ham cho btnMore
const btnMore = document.querySelector("#btn");

btnMore.onclick = function () {
    for (let i = 0; i < 5; i++) {
        // lan nay de ma mau random
        createBox(randomIndex());
    }
}
