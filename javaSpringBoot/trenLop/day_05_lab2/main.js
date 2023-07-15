let student_warning = {
    students: [
        {
            name: "Lê Hoài Nam",
            email: "namlehoai@gmail.com",
            phone: "123456789",
            total_off: 2,
            sessions: 14,
            detail_info: [
                {
                    date: "01-09-2020",
                    note: "Xin nghỉ ốm",
                    teacher: "Bùi Hiên",
                },
                {
                    date: "05-09-2020",
                    note: "Buồn vì thất tình",
                    teacher: "Nguyễn Hàn Duy",
                },
            ],
        },
        {
            name: "Đỗ Đăng Nguyên",
            email: "nguyen@gmail.com",
            phone: "0123987654",
            total_off: 2,
            sessions: 14,
            detail_info: [
                {
                    date: "01-09-2020",
                    note: "Mưa to nên ngại đi học",
                    teacher: "Bùi Hiên",
                },
                {
                    date: "05-09-2020",
                    note: "Trượt lô, nên rút học phí để đánh",
                    teacher: "Nguyễn Hàn Duy",
                },
            ],
        },
        {
            name: "Nguyễn Xuân Ba",
            email: "3nx92nd@gmail.com",
            phone: "0344005987",
            total_off: 3,
            sessions: 14,
            detail_info: [
                {
                    date: "01-09-2020",
                    note: "Đang training không đi học được",
                    teacher: "Bùi Hiên",
                },
                {
                    date: "07-09-2020",
                    note: "Soạn giáo án đặc vụ 0175",
                    teacher: "Nguyễn Hàn Duy",
                },
                {
                    date: "10-09-2020",
                    note: "Ganks team còng lưng nên chưa đi học được",
                    teacher: "Nguyễn Hàn Duy",
                },
            ],
        },
    ],
    class: "Lập trình Game 2D Canvas",
    course: "Lập trình Game",
    sessions: 14,
    teachers: [
        {
            name: "Bùi Hiên",
            email: "hien@techmaster.vn",
            phone: "0123456789",
        },
        {
            name: "Nguyễn Hàn Duy",
            email: "duy@techmaster.vn",
            phone: "0987654321",
        },
    ],
};

// Dựa vào thông tin của object student_warning. Hãy hiển thị dự liệu tương tự như demo trong mã HTML
const table = document.querySelector("table");
// console.log(table);

const tableBody = document.querySelector("table > tbody");
// console.log(tableBody);

const renderStudentWarning = function (studentWarning) {
    let studentList = studentWarning.students;
    let trText = ``;
    for (i = 0; i < studentList.length; i++) {
        if (studentList[i].total_off > 0) {
            trText = trText + ` <tr>
            <td rowspan="${studentList[i].total_off}">${i + 1}</td>
            <td rowspan="${studentList[i].total_off}">${studentList[i].name}</td>
            <td rowspan="${studentList[i].total_off}">${studentList[i].email}</td>
            <td rowspan="${studentList[i].total_off}">${studentList[i].phone}</td>
            <td rowspan="${studentList[i].total_off}" class="text-center">${studentList[i].total_off}/${studentList[i].sessions}</td>
            <td class="text-center">${studentList[i].detail_info[0].date}</td>
            <td>${studentList[i].detail_info[0].note}</td>
            <td>${studentList[i].detail_info[0].teacher}</td>
        </tr>`
            for (j = 1; j < studentList[i].detail_info.length; j++) {
                trText = trText + `<tr>
            <td class="text-center">${studentList[i].detail_info[j].date}</td>
            <td>${studentList[i].detail_info[j].note}</td>
            <td>${studentList[i].detail_info[j].teacher}</td>
        </tr> `;
            }
        }
    }
    // console.log(trText);
    if (studentList.length > 0) {
        tableBody.innerHTML = ``;
        tableBody.innerHTML = trText;
    } else {
        tableBody.style.display = "none";
    }
}

renderStudentWarning(student_warning);

const classBox = document.querySelector(".class-inner");
// console.log(classBox);

const renderClassInfo = function (studentWarning) {
    let trText =
        `<p><span>Lớp học</span> : ${studentWarning.class}</p>
                    <p><span>Thuộc khóa học</span> : ${studentWarning.course}</p>
                    <p><span>Danh sách giảng viên</span> :</p>
                    <ul>
                        `
    for (i = 0; i < studentWarning.teachers.length; i++) {
        trText = trText +
            `<li>${studentWarning.teachers[i].name} ( ${studentWarning.teachers[i].email} - ${studentWarning.teachers[i].phone} )</li>`
    }
    trText = trText +
        `</ul>
                    <p></p>
                    <p><span>Tổng số buổi</span> : ${studentWarning.sessions}</p>`
    classBox.innerHTML = ``;
    classBox.innerHTML = trText;
}

renderClassInfo(student_warning);
