users = [
    {
        name: "Bùi Công Sơn",
        age: 30,
        isStatus: true
    },
    {
        name: "Nguyễn Thu Hằng",
        age: 27,
        isStatus: false
    },
    {
        name: "Phạm Văn Dũng",
        age: 20,
        isStatus: false
    }
]
// render cac task xay san trong list o tren
const filterByAgeAndStatus = list => {
    let ans = list.filter(user => user.age > 25 && user.isStatus);
    return ans;
}

console.log(filterByAgeAndStatus(users));

const sortByAscendingAge = list => {
    // copy mang moi de khong anh huong data goc
    let ans = [...list];
    ans.sort((a, b) => a.age - b.age);
    return ans;
}

console.log(sortByAscendingAge(users));

