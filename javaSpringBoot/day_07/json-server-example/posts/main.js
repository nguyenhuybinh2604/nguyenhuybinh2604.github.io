const API_URL = "http://localhost:3000/posts";

const postsApis = {
    getAllPosts() {
        return axios.get(API_URL)
    },
    createPost(newPost) {
        return axios.post(API_URL, newPost) // promise
    },
    updatePost(posts) { //posts = {id, title, author}
        const { id, ...updatedPost } = posts;
        return axios.put(`${API_URL}/${id}`, updatedPost);
    },
    deletePost(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

// Hiển thị danh sách post
// Gọi API -> ds posts
// ds -> Hiển thị UI

const getAllPosts = async () => {
    try {
        let rs = await postsApis.getAllPosts();
        console.log(rs);
        renderPost(rs.data);
    } catch (error) {
        console.log(error);
    }
}

getAllPosts();

const postUl = document.getElementById('postUl');

function renderPost(data) {
    data.forEach(element => {
        let newLi = document.createElement("li");
        newLi.textContent = element.title;
        postUl.appendChild(newLi);
    });
}

console.log(postUl);

// 2. Tạo posts

// 3. Xóa posts

// 4. Cập nhật title

// 5. Cập nhật author

