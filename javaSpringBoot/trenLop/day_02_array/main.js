console.log("Hello world");
//alert("Good ebening"); -> Chi dung neu can canh bao, ngoai ra han che dung

//Khai báo biến và không gán giá trị cho biến
// int age = 35
let age;
age = 35;
age = "35";
let variable = 1;

//Khai báo biến và gán giá trị cho biến
let email = "binhnh@mailserver.com"

const PI = 3.14;

// data type:
// Java: float, int, double, long, short, boolean, byte, char
// Javascipt: number, string, boolean, null, undefined, symbol (ES6)
const status = false;
// const name = null;
const userId = undefined;

// ES6: Template string
let name = "Binh";
let year = 1989;
console.log(`Hello my name is ${name}. Nam nay minh ${getAge(year)} tuoi.`);
console.log("Hello my name is " + name + ". Nam nay minh " + (2023 - year) + " tuoi.");

function getAge(year) {
    return 2023 - year;
}

console.log(1 + "0");
console.log(1 - "0");
console.log(2 * "2");
console.log(9 / "2");
console.log("2" * "3");
console.log("2" * "3a"); // NaN = Not a number

// Method vs Function
// Method duoc dinh nghia trong class, di lien voi doi tuong
// 1. Regular function:
function sum(a, b) {
    return a + b;
}
// 2. Function expression:
const sum1 = function (a, b) {
    return a + b;
}
// 3. Arrow function (ES6)
const sum2 = (a, b) => {
    return a + b;
}
// const sum2 = (a,b) => a + b;
console.log(sum(2.5, 6));
console.log(sum1(2.5, 6));
console.log(sum2(2.5, 6));
console.log(sum1(2.5)); // number + undefined -> NaN
console.log(sum1()); // undefined + undefined -> NaN
// 4. Function expression:
const sum4 = function (a = 20, b = 30) { // => default parameters if given null inputs
    return a + b;
}

{
    let testVar = 2
    console.log(testVar);
}
// console.log(testVar);
//Bai 1
function bai1(a) {
    let b = a;
    for (let i = 1; i < 10; i++) {
        a = a + b;
    }
    return a;
}
console.log(bai1("a"))

//Bai 2
function bai2(a) {
    let b = "-" + a;
    for (let i = 1; i < 10; i++) {
        a = a + b;
    }
    return a;
}
console.log(bai2("a"))

//Bai 3
function bai3(a, n) {
    let b = "-" + a;
    for (let i = 1; i < n; i++) {
        a = a + b;
    }
    return a;
}
console.log(bai3("a", 5))

//Bai 4
function bai4(from, to) {
    let sum = 0;
    for (let i = from; i < to; i = i + 5) {
        sum = sum + i;
    }
    return sum;
}
console.log(bai4(0, 100))

//Bai 5
function bai5(radius) {
    return 4 / 3 * Math.PI * radius ^ 3;
}
console.log(bai5(10))

//Bai 6
function bai6(from, to) {
    let sum = 0;
    for (let i = from + 1; i < to; i++) {
        sum = sum + i;
    }
    return sum;
}
console.log(bai6(3, 8))

//Bai 7
function bai7(n) {
    if (n < 4) return true;
    let count = 0;
    for (let i = 2; i < n; i++) {
        if (n % i == 0) count++;
    }
    if (count == 0) return true;
    else return false;
}
console.log(bai7(33))

//Bai 8
function bai8(n) {
    let sum = 0;
    for (let i = 1; i < n; i++) {
        if (bai7(i)) sum += i;
    }
    return sum;
}
console.log(bai8(33))
//Bai 9
function bai9(n) {
    let sum = 0;
    for (let i = 1; i <= n; i++) {
        if (n % i == 0) sum += i;
    }
    return sum;
}
console.log(bai9(10))

let array = [5, "Bình", false, 9.5];
array[10] = "a";
console.log(array);

// Khai bao empty array:
// int[] 3 => [0, 0, 0]
// Integer[] 3 => [null, null, null]

let arr1 = [1, 2, 3]
let arr2 = [1, 2, 3]
let arr3 = [3, 2, 1]
let str = "1,2,3"

let test = arr3 == arr3.sort();
let test2 = arr2 == arr3.reverse();
let test3 = arr2 == str; // so sanh ve gia tri; arr duoc convert sang string, sau do thuc hien so sanh
let test4 = arr1 === str; // === so sanh ve ca kieu du lieu va gia tri
console.log(test3);
console.log(test4)

const fruit = ["item1", "item2", "item3", "item4"]
const citrus = fruit.slice();
const citrus1 = fruit.slice(1);
console.log(citrus1)

// Thao tac voi array
// pop(), push(), shift(), unshift()
// slice(), splice()
// reverse(), sort() -> sort theo string, neu muon sort theo so phai truyen comparator
// indexOf, lastIndexOf, includes == contains

const number = [4, 2, 5, 6, 2, 7]
console.log(number.sort());
console.log(number.sort((a, b) => a - b));

number.forEach((e, i) => {
    console.log(e, number[i], i)
})

const modulo2 = (arr) => {
    let result = [];
    arr.forEach((e) => { result.push(e % 2) });
    return result;
}
console.log(modulo2([3, 2, 6, 7, 3]))

// Filter = for + if

function oddNumbers(arr) {
    return arr.filter((e) => e % 2 == 1);
}
console.log(oddNumbers(number));

// js map va filter
// find: return first found element; findIndex -> tra ve chi so
// some & every

//Bai 1 : max value
function getMaxValue(arr) {
    arr.sort((a, b) => a - b);
    return arr[arr.length - 1];
}
console.log(getMaxValue(number));

//Bai 2: min value
function getMinValue(arr) {
    arr.sort((a, b) => a - b);
    return arr[0];
}
console.log(getMinValue(number));

//Bai 3
function modulo(arr) {
    return arr.map((e) => e % 2);
}
console.log(modulo([4, 2, 5, 6, 2, 7]));

//Bai 4
function tenTimes(str) {
    let arr = [];
    arr[0] = str;
    for (let i = 1; i < 10; i++) {
        arr.push(str);
    }
    let result = arr.toString();
    result = result.replace(new RegExp(',', 'g'), '');
    return result;
}
console.log(tenTimes("Binh"));

//Bai 5
function tenTimesWithDash(str) {
    let arr = [];
    arr[0] = str;
    for (let i = 1; i < 10; i++) {
        arr.push("-" + str);
    }
    let result = arr.toString();
    result = result.replace(new RegExp(',', 'g'), '');
    return result;
}
console.log(tenTimesWithDash("Binh"));

// Bai 6
function isSymmetricString(string) {
    string = string.replace(new RegExp(' ', 'g'), '');
    string = string.toLowerCase();
    let middle = Math.floor(string.length / 2);
    let arr = string.split("");
    let firstPart = arr.slice(0, middle).join("");
    let secondBegin;
    if (arr.length % 2 != 0) {
        secondBegin = middle + 1;
    } else {
        secondBegin = middle;
    }
    let secondPart = arr.slice(secondBegin, arr.length).reverse().join("");
    if (firstPart == secondPart) {
        return true;
    } else {
        return false;
    }
}
console.log(isSymmetricString("Race car"));

// Bai 7
function getSmallestNumber(num) {
    let anotherNum = num;
    let arr = [];
    let i = 0;
    while (anotherNum > 0) {
        arr[i] = anotherNum % 10;
        anotherNum = Math.floor(anotherNum / 10);
        i++;
    }
    arr.sort((a, b) => a - b);
    if (arr[0] == 0) {
        console.log(arr);
        let nextNonZero = arr.find((e) => e > 0) + 1;
        console.log(nextNonZero); // tai sao o day chi return stt 1
        [arr[0], arr[nextNonZero]] = [arr[nextNonZero], arr[0]];
        return arr.join("");
    }
    else {
        return arr.join("");
    }
}
console.log(getSmallestNumber(192300045));

// OBJECT
let user = {
    name: "Nguyễn Văn A",
    age: 19,
    email: "nguyenvana@gmail.com",
    sayHello: function () {
        console.log("Hello");
    },
    eat(food) {
        console.log("Eat " + food);
    }
}

console.log(user.name);
console.log(user["name"]); // su dung khi duyet array of keys
// user.eat("Thit")

// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// Bai 1
function printEach(arr) {
    for (let i = 0; i < arr.length; i++) {
        console.log(arr[i]);
    }
}
printEach(products);

// Bai 3
const findByBrand = (arr, brand) => {
    return arr.filter(e => e.brand == brand);
}

console.log(findByBrand(products, "OPPO"))