courseList = [];

let params = {
    type: null,
    name: null,
    topic: null
}

const getAllCourse = () => {
    return axios.get("http://localhost:8080/api/v1/courses");
}

courseList = getAllCourse();
