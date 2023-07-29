const quizes = [
    {
        id: 1,
        question: "1 + 1 = ?",
        answers: [1, 2, 3, 4],
    },
    {
        id: 2,
        question: "2 + 2 = ?",
        answers: [2, 3, 4, 5],
    },
    {
        id: 3,
        question: "3 + 3 = ?",
        answers: [3, 4, 5, 6],
    },
];

const btn = document.getElementById('btn');
const container = document.querySelector('.quiz-container');

// ham tao quiz
function renderQuizes(listOfQuizes) {
    container.innerHTML = ``;
    let html = ``;
    for (let i = 0; i < listOfQuizes.length; i++) {
        html += `<div class="quiz-item">
            <h3>Câu ${listOfQuizes[i].id} : ${listOfQuizes[i].question}</h3>
            <div class="quiz-answer">`
        for (let j = 0; j < listOfQuizes[i].answers.length; j++) {
            html +=
                `<div class="quiz-answer-item">
                    <input type="radio" name="${listOfQuizes[i].id}">
                    <label>${listOfQuizes[i].answers[j]}</label>
                </div>`
        }
        html +=
            `</div>
        </div>`
    }
    container.innerHTML = html;
}

renderQuizes(quizes);

btn.onclick = function () {
    let children = container.childNodes;

    // duyet het cac cau hoi
    for (let i = 0; i < children.length; i++) {
        // chon 1 answer ngau nhien tu 1 -> answers.length cua cau hoi
        let pickedAnswer = getRandomAnswer(1, quizes[i].answers.length);
        // chon item theo so ngau nhien o tren
        let quizAnswerItem = children[i].querySelector(`.quiz-answer-item:nth-child(${pickedAnswer})`);
        // pick item do, khong can xoa trang cac lua chon vi item co dang option
        let input = quizAnswerItem.querySelector('input');
        input.checked = true;
    }
}

function getRandomAnswer(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

