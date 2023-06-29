//Bai 1
function bai1_calculate_factorial(n) {
    if (n < 1) {
        console.log("So nhap vao khong hop le")
        return;
    }
    let result = 1;
    for (let i = 1; i <= n; i++) {
        result = result * i;
    }
    return result;
}
console.log(bai1_calculate_factorial(5))

//Bai 2
function bai2_reverse_string(str) {
    let result = str.split("").reverse().join("");
    return result;
}
console.log(bai2_reverse_string("This string is reversed"));

//Bai 3
function bai3_translate(country_code) {
    let result;
    switch (country_code) {
        case "VN":
            result = "Xin chào";
            break;
        case "EN":
            result = "Hello";
            break;
        case "RU":
            result = "Привет";
            break;
        case "CN":
            result = "你好";
            break;
        case "FR":
            result = "Bonjour";
            break;
        case "ES":
            result = "Hola";
            break;
        default:
            result = "Invalid code";
    }
    return result;
}
console.log(bai3_translate("CN"));

//Bai 4
function bai4_substring(str) {
    if (str.length <= 15) {
        console.log("Chuoi nhap vao khong hop le")
        return;
    }
    let result = str.substring(0,10) + "...";
    return result;
}
console.log(bai4_substring("This string is long enough"));