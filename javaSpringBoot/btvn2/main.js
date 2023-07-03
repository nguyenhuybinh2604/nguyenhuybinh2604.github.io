const grades = [
    { name: 'John', grade: 8, sex: 'M' },
    { name: 'Sarah', grade: 12, sex: 'F' },
    { name: 'Bob', grade: 16, sex: 'M' },
    { name: 'Johnny', grade: 2, sex: 'M' },
    { name: 'Ethan', grade: 4, sex: 'M' },
    { name: 'Paula', grade: 18, sex: 'F' },
    { name: 'Donald', grade: 5, sex: 'M' },
    { name: 'Jennifer', grade: 13, sex: 'F' },
    { name: 'Courtney', grade: 15, sex: 'F' },
    { name: 'Jane', grade: 9, sex: 'F' }
]

// Bai 1
// Viết function tính thứ hạng trung bình của cả lớp
function bai1_average_grade(grades) {
    // dung reduce tinh sum tat ca grade trong grades
    let sum = grades.reduce((accumulator, currentValue) => {
        return accumulator + currentValue.grade;
    }, 0);

    // chia cho do dai grades
    let avg = sum / grades.length;
    return avg;
}
console.log(bai1_average_grade(grades));

// Bai 2
// Viết function tính thứ hạng trung bình của nam trong lớp
function bai2_average_grade_M(grades) {
    // tao array moi bang cach filter array cu lay sex === 'M'
    let maleGrades = grades.filter((item) => {
        return item.sex === 'M';
    });

    // tinh binh quan nhu bai 1
    let sum = maleGrades.reduce((accumulator, currentValue) => {
        return accumulator + currentValue.grade;
    }, 0);

    let avg = sum / maleGrades.length;
    return avg;
}
console.log(bai2_average_grade_M(grades));

// Bai 3
// Viết function tính thứ hạng trung bình của Nữ trong lớp
function bai3_average_grade_F(grades) {
    // tao array moi bang cach filter array cu lay sex === 'F'
    let femaleGrades = grades.filter((item) => {
        return item.sex === 'F';
    });

    // tinh binh quan nhu bai 1
    let sum = femaleGrades.reduce((accumulator, currentValue) => {
        return accumulator + currentValue.grade;
    }, 0);

    let avg = sum / femaleGrades.length;
    return avg;
}
console.log(bai3_average_grade_F(grades));

// Bai 4
// Viết function tìm học viên Nam có thứ hạng cao nhất trong lớp
function bai4_max_grade_M(grades) {
    // tao array moi bang cach filter array cu lay sex === 'M'
    let maleGrades = grades.filter((item) => {
        return item.sex === 'M';
    });

    // sort tu cao xuong thap theo grade
    maleGrades.sort((a, b) => {
        return b.grade - a.grade;
    });

    // lay ban ghi dau tien
    return maleGrades[0];
}
console.log("Nam sinh co diem cao nhat la " + bai4_max_grade_M(grades).name + " voi " + bai4_max_grade_M(grades).grade + " diem.");

// Bai 5
// Viết function tìm học viên Nữ có thứ hạng cao nhất trong lớp
function bai5_max_grade_F(grades) {
    // tao array moi bang cach filter array cu lay sex === 'F'
    let femaleGrades = grades.filter((item) => {
        return item.sex === 'F';
    });

    // sort tu cao xuong thap theo grade
    femaleGrades.sort((a, b) => {
        return b.grade - a.grade;
    });

    // lay ban ghi dau tien
    return femaleGrades[0];
}
console.log("Nu sinh co diem cao nhat la " + bai5_max_grade_F(grades).name + " voi " + bai5_max_grade_F(grades).grade + " diem.");

// Bai 6
// Viết function tìm học viên Nam có thứ hạng thấp nhất trong lớp
// Tuong tu bai 4 nhung thay doi chieu sort
function bai6_min_grade_M(grades) {
    // tao array moi bang cach filter array cu lay sex === 'M'
    let maleGrades = grades.filter((item) => {
        return item.sex === 'M';
    });

    // sort tu cao xuong thap theo grade
    maleGrades.sort((a, b) => {
        return a.grade - b.grade;
    });

    // lay ban ghi dau tien
    return maleGrades[0];
}
console.log("Nam sinh co diem thap nhat la " + bai6_min_grade_M(grades).name + " voi " + bai6_min_grade_M(grades).grade + " diem.");

// Bai 7
// Viết function tìm học viên Nữ có thứ hạng thấp nhất trong lớp
// Tuong tu bai 5 nhung thay doi chieu sort
function bai7_min_grade_F(grades) {
    // tao array moi bang cach filter array cu lay sex === 'F'
    let femaleGrades = grades.filter((item) => {
        return item.sex === 'F';
    });

    // sort tu cao xuong thap theo grade
    femaleGrades.sort((a, b) => {
        return a.grade - b.grade;
    });

    // lay ban ghi dau tien
    return femaleGrades[0];
}
console.log("Nu sinh co diem cao nhat la " + bai7_min_grade_F(grades).name + " voi " + bai7_min_grade_F(grades).grade + " diem.");

// Bai 8
// Viết function thứ hạng cao nhất của cả lớp
// Tuong tu bai 4 nhung khong filter
function bai8_max_grade(grades) {
    // tao array moi bang cach slice array cu 
    let subGrades = grades.slice();

    // sort tu cao xuong thap theo grade
    subGrades.sort((a, b) => {
        return b.grade - a.grade;
    });

    // lay ban ghi dau tien
    return subGrades[0];
}
console.log("Hoc sinh co diem cao nhat la " + bai8_max_grade(grades).name + " voi " + bai8_max_grade(grades).grade + " diem.");

// Bai 9
// Viết function thứ hạng thấp nhất của cả lớp
// Tuong tu bai 8 nhung chieu nguoc lai
function bai9_min_grade(grades) {
    // tao array moi bang cach slice array cu 
    let subGrades = grades.slice();

    // sort tu cao xuong thap theo grade
    subGrades.sort((a, b) => {
        return a.grade - b.grade;
    });

    // lay ban ghi dau tien
    return subGrades[0];
}
console.log("Hoc sinh co diem thap nhat la " + bai9_min_grade(grades).name + " voi " + bai9_min_grade(grades).grade + " diem.");

// Bai 10
// Viết function lấy ra danh sách tất cả học viên Nữ trong lớp
// Tuong tu bai 7 nhung chi filter
function bai10_F(grades) {
    // tao array moi bang cach filter array cu lay sex === 'F'
    let femaleGrades = grades.filter((item) => {
        return item.sex === 'F';
    });

    // lay ca mang
    return femaleGrades;
}
console.log(bai10_F(grades));

// Bai 11
// Viết function sắp xếp tên học viên theo chiều tăng dần của bảng chữ cái
function bai11_sortByName_ascending(grades) {
    // tao array moi bang cach slice array cu 
    let subGrades = grades.slice();

    // sort tu cao xuong thap theo grade
    subGrades.sort((a, b) => {
        const nameA = a.name.toLowerCase();
        const nameB = b.name.toLowerCase();
        if (nameA < nameB) {
            return -1;
        }
        if (nameA > nameB) {
            return 1;
        }
        return 0;
    });

    // lay ca mang
    return subGrades;
}
console.log(bai11_sortByName_ascending(grades));


// Bai 12
// Viết function sắp xếp thứ hạng học viên theo chiều giảm dần
function bai12_sortByGrade_descending(grades) {
    // tao array moi bang cach slice array cu 
    let subGrades = grades.slice();

    // sort tu cao xuong thap theo grade
    subGrades.sort((a, b) => {
        return b.grade - a.grade;
    });

    // lay ca mang
    return subGrades;
}
console.log(bai12_sortByGrade_descending(grades));

// Bai 13
// Viết function lấy ra học viên Nữ có tên bắt đầu bằng “J”
function bai13_filterByName_hasJ(grades) {
    // tao array moi bang cach filter array cu
    let subGrades = grades.filter((item) => {
        return item.name.includes('J');
    });

    // lay ca mang
    return subGrades;
}
console.log(bai13_filterByName_hasJ(grades));

// Bai 14
// Viết function lấy ra top 5 người có thứ hạng cao nhất trong lớp
function bai14_top5(grades) {
    // tao array moi bang cach slice array cu 
    let subGrades = grades.slice();

    // sort tu cao xuong thap theo grade
    subGrades.sort((a, b) => {
        return b.grade - a.grade;
    });

    // chi lay 5 ban ghi dau tien
    subGrades = subGrades.slice(0, 5);
    
    // lay ca mang
    return subGrades;
}
console.log(bai14_top5(grades));