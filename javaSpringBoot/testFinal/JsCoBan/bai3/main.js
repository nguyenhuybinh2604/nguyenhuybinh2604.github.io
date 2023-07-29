const getCountElement = list => {
    const ans = {};

    for (let i = 0; i < list.length; i++) {
        // neu da ton tai thi +1
        if (ans[list[i]]) {
            ans[list[i]]++;
            // neu chua co thi bat dau dem = 1
        } else {
            ans[list[i]] = 1;
        }
    }
    return ans;
}


console.log(getCountElement(["one", "two", "three", "one", "one", "three"]));