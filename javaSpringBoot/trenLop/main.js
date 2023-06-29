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
console.log("Hello my name is " + name + ". Nam nay minh "+ (2023-year) + " tuoi.");

function getAge(year) {
    return 2023-year;
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
function sum(a,b) {
    return a+b;
}
// 2. Function expression:
const sum1 = function(a,b) { 
    return a+b;
}
// 3. Arrow function (ES6)
const sum2 = (a,b) => {
    return a + b;
}
// const sum2 = (a,b) => a + b;
console.log(sum(2.5,6));
console.log(sum1(2.5,6));
console.log(sum2(2.5,6));
console.log(sum1(2.5)); // number + undefined -> NaN
console.log(sum1()); // undefined + undefined -> NaN
// 4. Function expression:
const sum4 = function(a=20,b=30) { // => default parameters if given null inputs
    return a+b;
}

{
    let testVar = 2
    console.log(testVar);
}
// console.log(testVar);
//Bai 1
function bai1 (a) {
    let b = a;
    for (let i=1;i<10;i++) {
        a = a+b;
    }
    return a;
}
console.log(bai1("a"))

//Bai 2
function bai2 (a) {
    let b = "-"+a;
    for (let i=1;i<10;i++) {
        a = a+b;
    }
    return a;
}
console.log(bai2("a"))

//Bai 3
function bai3 (a,n) {
    let b = "-"+a;
    for (let i=1;i<n;i++) {
        a = a+b;
    }
    return a;
}
console.log(bai3("a",5))

//Bai 4
function bai4 (from,to) {
    let sum = 0;
    for (let i=from;i<to;i=i+5) {
        sum = sum + i;
    }
    return sum;
}
console.log(bai4(0,100))

//Bai 5
function bai5 (radius) {
    return 4/3*Math.PI*radius^3;
}
console.log(bai5(10))

//Bai 6
function bai6 (from,to) {
    let sum = 0;
    for (let i=from+1;i<to;i++) {
        sum = sum + i;
    }
    return sum;
}
console.log(bai6(3,8))

//Bai 7
function bai7 (n) {
    if (n < 4) return true;
let count = 0;
    for (let i=2;i<n;i++) {
        if (n%i==0) count++;
    }
if (count==0) return true;
else return false;
}
console.log(bai7(33))

//Bai 8
function bai8 (n) {
    let sum = 0;
        for (let i=1;i<n;i++) {
            if (bai7(i)) sum+=i;
        }
        return sum;
    }
console.log(bai8(33))
//Bai 9
function bai9 (n) {
    let sum = 0;
        for (let i=1;i<=n;i++) {
            if (n%i==0) sum+=i;
        }
        return sum;
    }
console.log(bai9(10))