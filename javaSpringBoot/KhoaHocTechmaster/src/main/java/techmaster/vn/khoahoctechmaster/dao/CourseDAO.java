package techmaster.vn.khoahoctechmaster.dao;

import techmaster.vn.khoahoctechmaster.model.Course;
import techmaster.vn.khoahoctechmaster.service.CourseService;

import java.util.List;

public interface CourseDAO {
    public List<Course> findAll();

//    void save(Course course);
//
//    void delete(Integer id);
//
//    List<Course> findByTitleContainsIgnoreCase(String title);

}
