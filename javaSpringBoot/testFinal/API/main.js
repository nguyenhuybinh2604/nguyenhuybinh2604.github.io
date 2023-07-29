const btnPosts = document.getElementById('btn-posts');
const btnPhotos = document.getElementById('btn-photos');
const btnAlbums = document.getElementById('btn-albums');
const buttons = document.querySelector('.button-container').querySelectorAll('button');
const ul = document.getElementById('ul-api');
const header = document.getElementById('header');

async function getPosts() {
    // Đổi nội dung header
    header.innerText = "Type: Posts";

    // Toggle off class của tất cả nút
    buttons.forEach(button => {
        button.className = '';
    });

    // Toggle on highlight class của nút được gọi
    btnPosts.classList.add("highlighted-button");

    // Gọi API để lấy danh sách
    let res = await axios.get("https://jsonplaceholder.typicode.com/posts")

    // Sau khi có data thì hiển thị kết quả trên giao diện
    renderItems(res.data)
}

async function getPhotos() {
    // Đổi nội dung header
    header.innerText = "Type: Photos";

    // Toggle off class của tất cả nút
    buttons.forEach(button => {
        button.className = '';
    });

    // Toggle on highlight class của nút được gọi
    btnPhotos.classList.add("highlighted-button");

    // Gọi API để lấy danh sách
    let res = await axios.get("https://jsonplaceholder.typicode.com/photos")

    // Sau khi có data thì hiển thị kết quả trên giao diện
    renderItems(res.data)
}

async function getAlbums() {
    // Đổi nội dung header
    header.innerText = "Type: Albums";

    // Toggle off class của tất cả nút
    buttons.forEach(button => {
        button.className = '';
    });

    // Toggle on highlight class của nút được gọi
    btnAlbums.classList.add("highlighted-button");

    // Gọi API để lấy danh sách
    let res = await axios.get("https://jsonplaceholder.typicode.com/albums")

    // Sau khi có data thì hiển thị kết quả trên giao diện
    renderItems(res.data)
}

function renderItems(items) {
    // xoa trang ul
    ul.innerHTML = '';
    for (let i = 0; i < items.length; i++) {
        const li = document.createElement("li");
        li.textContent = items[i].title;
        ul.appendChild(li);
    }
};

// Gọi lần đầu vào posts
getPosts();

// Các event để cho các lần nhấn nút

btnPosts.onclick = function () {
    getPosts();
}

btnPhotos.onclick = function () {
    getPhotos();
}

btnAlbums.onclick = function () {
    getAlbums();
}