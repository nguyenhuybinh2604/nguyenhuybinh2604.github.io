const tasks = [
    {
        status: "checked",
        title: "Task 1 here"
    },
    {
        status: "unchecked",
        title: "Task 2 here"
    },
    {
        status: "unchecked",
        title: "Task 3 here"
    },
    {
        status: "checked",
        title: "Task 4 here"
    }
]

const taskList = document.querySelector("#taskUl");
// console.log(taskList);

const announcementHeader = document.querySelector("#announcement");

// function de check neu ul con trong hay da co task
const displayEmptyList = function (ulEl) {
    if (ulEl.childElementCount==0) {
        // neu con trong thi hien thi thong bao
        announcementHeader.innerHTML = "Danh sách công việc trống"
        // neu khong thi thong bao = ``
    } else announcementHeader.innerHTML = ``;
}

// load lan dau
displayEmptyList(taskList);


// render cac task xay san trong list o tren
const renderTaskList = list => {
    taskList.innerHTML = ``;
    let html = ``;
    list.forEach(e => {
        html += `
        <li style="list-style-type: circle;">
        <input type="checkbox" ${e.status === "checked" ? "checked" : ""}>
        <span ` + `${e.status === "checked" ? ` class = "activeSpan" ` : ""}` + `> ${e.title}</span> 
        <button class="editBtn">Edit</button>
        <button class="deleteBtn">Delete</button>
        </li>`
    });
    taskList.innerHTML = html;
    displayEmptyList(taskList);
}

renderTaskList(tasks);

const addBtn = document.querySelector("#btnAdd");
const inputEl = document.querySelector("#myInput");

// tao the li (task) moi khi click vao btnAdd
addBtn.onclick = function () {
    if (inputEl.value !== '') {
        let newList = document.createElement("li");
        newList.style = "list-style-type: circle;";
        newList.innerHTML = `
        <input type="checkbox">
        <span > ${inputEl.value}</span> 
        <button class="editBtn">Edit</button>
        <button class="deleteBtn">Delete</button>`;
        taskList.append(newList);
        displayEmptyList(taskList);
        // canh bao neu nhap vao ``
    } else alert("Tên công việc không được để trống");
}

// check cac thao tac voi task list
taskList.addEventListener('click', function (event) {
    // neu an vao check box (input) -> switch class cua span
    if (event.target.nodeName === 'INPUT') {
        let span = event.target.nextSibling.nextSibling;
        if (event.target.checked) {
            span.classList.add("activeSpan");
        } else {
            span.classList.remove("activeSpan");
        }
        // neu an vao nut edit -> promt nhap vao ten task moi, thong bao neu empty
    } else if (event.target.nodeName === 'BUTTON' && event.target.classList.contains("editBtn")) {
        let span = event.target.previousSibling.previousSibling;
        const inputValue = prompt('Nhập tiêu đề công việc mới:');
        if (inputValue == ``) alert("Tên công việc không được để trống");
        else {
            span.innerText = inputValue;
        };
        // neu an vao delete -> hien thi confirmation dialog -> xoa neu chon OK
    } else if (event.target.nodeName === 'BUTTON' && event.target.classList.contains("deleteBtn")) {
        const confirmed = confirm('Bạn chắc chắn muốn xóa?');
        if (confirmed) {
            const parent = event.target.parentNode;
            parent.remove();
        } else {
        }
        // console.log(taskList.childElementCount);
        if (taskList.childElementCount==0) displayEmptyList(taskList);
    }

});

