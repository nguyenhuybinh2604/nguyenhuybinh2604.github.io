const productList = [
    {
        id: 1,
        name: "Monogram Flower Tile Short Parka",
        price: 11000000,
        thumbnail: "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhlc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        rating: 4
    },
    {
        id: 2,
        name: "Monogram Flower Tile Short Parka",
        price: 11000000,
        thumbnail: "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhlc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        rating: 5
    },
    {
        id: 3,
        name: "Monogram Flower Tile Short Parka",
        price: 11000000,
        thumbnail: "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhlc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        rating: 3
    },
    {
        id: 4,
        name: "Monogram Flower Tile Short Parka",
        price: 11000000,
        thumbnail: "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhlc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        rating: 4
    },
    {
        id: 5,
        name: "Monogram Flower Tile Short Parka",
        price: 11000000,
        thumbnail: "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhlc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        rating: 2
    },
    {
        id: 6,
        name: "Monogram Flower Tile Short Parka",
        price: 11000000,
        thumbnail: "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhlc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        rating: 5
    }
]

document.body.style.display = "block";
const container = document.querySelector(".container");
console.log(container);

const header = document.createElement("h2");
header.innerText = "List of products";
header.style.alignContent = "center";
container.prepend(header);

const productRow = document.createElement("div");
productRow.classList.add("product-list", "row");

const renderProductList = list => {
    productRow.innerHTML = "";
    let html = "";
    for (let i = 0; i < productList.length; i++) {
        html = html + `<div class="col-md-3">
        <div class="course-item shadow-sm rounded mb-4">
            <div class="course-item-image">
                <img src=${productList[i].thumbnail}
                    alt="Marge Innastraightline" />
            </div>
            <div class="course-item-info p-3">
                <h2 class="fs-5 mb-4 text-dark">
                ${productList[i].name}
                </h2>
                <div
                    class="d-flex justify-content-between align-items-center fw-light text-black-50">
                    <p class="type">Louis Vuitton</p>
                    <p class="rating">
                        <span>${productList[i].rating}</span>
                        <span class="text-warning"><i class="fa-solid fa-star"></i></span>
                    </p>
                </div>
                <p class="price text-danger fs-5">
                ${productList[i].price.toLocaleString('en-US', {
                    style: 'decimal',
                    minimumFractionDigits: 0,
                    maximumFractionDigits: 0,
                  })} VND
                </p>
            </div>
        </div>
    </div>`
    }
    productRow.innerHTML = html;
}

renderProductList(productList);
header.insertAdjacentElement("afterend",productRow);

