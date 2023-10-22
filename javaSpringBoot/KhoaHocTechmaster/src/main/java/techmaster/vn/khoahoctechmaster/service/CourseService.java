package techmaster.vn.khoahoctechmaster.service;

import techmaster.vn.khoahoctechmaster.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAllCourses(String type, String name, String topic);

    public Course getCourseById(Integer id);
}
