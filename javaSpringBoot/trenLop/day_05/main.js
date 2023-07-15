const testimonialList =
    [{
        name: "Albert Einstein",
        quote: "Imagination is more important than knowledge.",
        image: "https://randomuser.me/api/portraits/men/41.jpg",
        color: "#1D5B79"
    },
    {
        name: "Maya Angelou",
        quote: "I've learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel.",
        image: "https://randomuser.me/api/portraits/women/50.jpg",
        color: "#468B97"
    },
    {
        name: "Steve Jobs",
        quote: "Innovation distinguishes between a leader and a follower.",
        image: "https://randomuser.me/api/portraits/women/22.jpg",
        color: "#EF6262"
    },
    {
        name: "Oprah Winfrey",
        quote: "The biggest adventure you can ever take is to live the life of your dreams.",
        image: "https://randomuser.me/api/portraits/women/76.jpg",
        color: "#F3AA60"
    },
    {
        name: "Nelson Mandela",
        quote: "It always seems impossible until it's done.",
        image: "https://randomuser.me/api/portraits/men/17.jpg",
        color: "#E6E2C3"

    }]

const testimonialContainer = document.querySelector(".testimonials-container");
const textElement = document.querySelector(".text");
const nameElement = document.querySelector(".name");

const authorContainer = document.querySelector(".authors-container");

let selectedAuthor = document.querySelector(".selected");

const updateInfo = function (i) {
    textElement.innerText = testimonialList[i].quote;
    nameElement.innerText = testimonialList[i].name;
    testimonialContainer.style.backgroundColor = testimonialList[i].color;
}

if (selectedAuthor) {
    const index = Array.prototype.indexOf.call(authorContainer.children, selectedAuthor);
    updateInfo(index);
}

const myAuthors = document.querySelectorAll(".author");

myAuthors.forEach(function (element) {
    element.addEventListener("click", function () {

        // console.log("myClass element is clicked");
        let index = Array.prototype.indexOf.call(authorContainer.children, element);
        if (selectedAuthor != element) {
            selectedAuthor.classList.remove("selected");

            element.classList.add("selected");

            selectedAuthor = element;

            updateInfo(index);
        }
    });
});
