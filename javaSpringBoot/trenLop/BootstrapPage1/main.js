sectionContact = document.getElementById("contact");
sectionInteraction = document.getElementById("interaction");

function highlightListItem() {
    var listItems = document.querySelectorAll("#navbarNav ul li");

    for (var i = 0; i < listItems.length; i++) {
        listItems[i].addEventListener("click", function () {
            for (var j = 0; j < listItems.length; j++) {
                listItems[j].classList.remove("selected")
            }
            this.classList.add("selected");
            var index = Array.prototype.indexOf.call(listItems, this);
            switch (index) {
                case 0: {
                    console.log(10);
                    break;
                }
                case 1: {
                    console.log(20);
                    break;
                }
                case 2: {
                    console.log(30);
                    break;
                }
                case 3: {
                    console.log(40);
                    break;
                }
                case 4: {
                    console.log(50);
                    break;
                }
            }
        });

        listItems[i].addEventListener("mouseover", function () {
            if (!this.classList.contains("selected"))
                this.classList.add("highlighted");
        });

        listItems[i].addEventListener("mouseout", function () {
            this.classList.remove("highlighted");
        });
    }
}

highlightListItem();