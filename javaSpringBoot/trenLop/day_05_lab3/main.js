const taskList = [
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

const taskUl = document.querySelector("#taskUl");
// console.log(taskUl);

const announcementHeader = document.querySelector("#announcement");
// console.log(announcementHeader);

announcementHeader.innerHTML = ``;

const displayEmptyList = function (ulEl) {
    if (ulEl.innerHTML === ``) {
        announcementHeader.innerHTML = "Danh sách công việc trống"
    }
}

displayEmptyList(taskUl);

const renderTaskList = list => {
    taskUl.innerHTML = ``;
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
    taskUl.innerHTML = html;
    // console.log(html);
}

renderTaskList(taskList);

const addBtn = document.querySelector("#btnAdd");
const inputEl = document.querySelector("#myInput");
// console.log(inputEl);

addBtn.onclick = function () {
    if (inputEl.value !== '') {
        let ulHtml = taskUl.innerHTML;
        // console.log(ulHtml);
        ulHtml = ulHtml + `<li style="list-style-type: circle;">
        <input type="checkbox">
        <span > ${inputEl.value}</span> 
        <button class="editBtn">Edit</button>
        <button class="deleteBtn">Delete</button>
        </li>`;
        taskUl.innerHTML = ulHtml;
        checkboxes = document.querySelectorAll('input[type="checkbox"]');
        console.log(checkboxes);
    }
}

let checkboxes = document.querySelectorAll('input[type="checkbox"]');
console.log(checkboxes);

checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener("change", function (event) {
        let parentList = event.target.parentNode;
        let span = parentList.querySelector("span");
        // console.log(parentList);
        if (event.target.checked) {
            span.classList.add("activeSpan");
        } else {
            span.classList.remove("activeSpan");
            // console.log("Checkbox " + event.target.value + " is not checked");
        }
    });
});