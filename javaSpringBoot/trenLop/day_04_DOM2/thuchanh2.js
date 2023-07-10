const para = document.querySelector("#text");

// HTML attribute
const btn1 = document.querySelector("#btn-1");

// Define an array of quotes
const quotes = [
    "The greatest glory in living lies not in never falling, but in rising every time we fall. -Nelson Mandela",
    "The way to get started is to quit talking and begin doing. -Walt Disney",
    "Your time is limited, don't waste it living someone else's life. -Steve Jobs",
    "If life were predictable it would cease to be life, and be without flavor. -Eleanor Roosevelt",
    "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough. -Oprah Winfrey",
    "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success. -James Cameron"
];

function toggleContent() {
    // Generate a random index within the range of the quotes array length
    let randomIndex = Math.floor(Math.random() * quotes.length);

    // Get a random quote using the random index
    let randomQuote = quotes[randomIndex];

    // Output the random quote to the para
    para.innerText = randomQuote;
}

// DOM property
const btn2 = document.querySelector("#btn-2");

function generateRandomHexColor() {
    // Generate a random color code using the hexadecimal notation
    const colorCode = Math.floor(Math.random() * 16777215).toString(16);

    // If the generated color code is less than 6 digits, pad it with zeros
    const paddedColorCode = colorCode.padStart(6, '0');

    // Return the padded color code with the '#' prefix
    return '#' + paddedColorCode;
}

btn2.onclick = function () {
    para.style.color = generateRandomHexColor();
}

// addEventListener
const btn3 = document.querySelector("#btn-3");

function generateRandomRgbColor() {
    // Generate random values for red, green, and blue components between 0 and 255
    const red = Math.floor(Math.random() * 256);
    const green = Math.floor(Math.random() * 256);
    const blue = Math.floor(Math.random() * 256);

    // Return the RGB color code as "rgb(red, green, blue)"
    return `rgb(${red}, ${green}, ${blue})`;
}
btn3.addEventListener('click', function () {
    para.style.backgroundColor = generateRandomRgbColor();
})