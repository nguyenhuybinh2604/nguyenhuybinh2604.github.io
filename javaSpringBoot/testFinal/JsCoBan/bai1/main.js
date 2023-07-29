function getStringHasMaxLength(stringArray) {

    if (stringArray.length === 0) {
        return;
    } else {
        // sort tu dai nhat den ngan nhat
        stringArray.sort((a, b) => b.length - a.length);
        let maxLength = stringArray[0].length;
        let ans = [];
        let i = 0;
        while (i < stringArray.length && stringArray[i].length == maxLength) {
            ans.push(stringArray[i++]);

        }
        return ans;
    }
}

console.log(getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']));
