const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');
const subBreedHeader = document.getElementById('sub-breed-header');
const result = document.querySelector(".result");

// Vừa load trang phải gọi API để render danh sách breed
// API : https://dog.ceo/api/breeds/list/all

async function getBreedList() {
  // Gọi API để lấy danh sách giống loài
  let res = await axios.get("https://dog.ceo/api/breeds/list/all")
  // console.log(res.data.message);
  // Sau khi có data thì hiển thị kết quả trên giao diện
  renderBreed(res.data.message)
}

function renderBreed(breeds) {
  // Duyệt qua object breeds -> tạo thẻ option -> gắn vào DOM

  // Cách 1: Sử dụng for ... in
  // Cách 2 : Lấy ra danh sách keys của objec (Object.keys) => Duyệt mảng
  for (const breed in breeds) {
    if (breeds.hasOwnProperty(breed)) {
      const option = document.createElement("option");
      option.textContent = breed;
      option.value = breed;
      select.appendChild(option);
    }
  }
};

// getBreedList();

// Hien thi giong loai phu

const subBreedUl = document.getElementById('sub-breed');

let selectedBreed = select.value;

async function getSubBreedList() {

  await getBreedList();

  result.style.display = "none";

  selectedBreed = select.value;

  subBreedHeader.innerText = `Danh sách giống loài cụ thể cho ${selectedBreed}`;

  // Gọi API để lấy danh sách giống loài
  let res = await axios.get(`https://dog.ceo/api/breed/${selectedBreed}/list`)

  // Sau khi có data thì hiển thị kết quả trên giao diện
  renderSubBreed(res.data.message)
}

function renderSubBreed(subBreeds) {
  // xoa trang ul moi lan update
  while (subBreedUl.firstChild) {
    subBreedUl.removeChild(subBreedUl.firstChild);
  }

  for (const subBreed in subBreeds) {
    const li = document.createElement("li");
    li.innerText = subBreeds[subBreed];
    subBreedUl.appendChild(li);
  }

  if (subBreedUl.childElementCount === 0) {
    const li = document.createElement("li");
    li.innerText = "Không có sub breed.";
    subBreedUl.appendChild(li);
  };

}

getSubBreedList();

// get random image of a sub breed

function activeUl(ul) {
  // lay tat ca li trong ul
  const listItems = ul.getElementsByTagName("li");

  for (let i = 0; i < listItems.length; i++) {
    listItems[i].addEventListener("click", async function (event) {
      // tru truong hop khong co sub breed - hien thi 01 li 
      if (listItems[i].innerText != "Không có sub breed.") {
        let res = await axios.get(`https://dog.ceo/api/breed/${selectedBreed}/${listItems[i].innerText}/images/random`)
        if (res.data.message != null)
          // day link anh vao image
          image.src = res.data.message;
        // hien thi doi voi truong hop div result dang bi hide (khi load lan dau hoac khi moi get sub breed)
        if (result.style.display === "none") result.style.display = "";
      }
    });

    listItems[i].addEventListener("mouseover", function () {
      if (listItems[i].innerText != "Không có sub breed.")
      listItems[i].style.fontWeight = 'bold';
      listItems[i].style.cursor = 'pointer';
    });
    listItems[i].addEventListener("mouseout", function () {
      if (listItems[i].innerText != "Không có sub breed.")
      listItems[i].style.fontWeight = 'normal';
    });
  }

};

// goi ham khi click
btn.addEventListener("click", async function () {
  await getSubBreedList();
  // set click event cho cac li
  activeUl(subBreedUl);
});
