package techmaster.vn.khoahoctechmaster.service;

import techmaster.vn.khoahoctechmaster.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(String type, String name, String topic);

    Course getCourseById(Integer id);
}
