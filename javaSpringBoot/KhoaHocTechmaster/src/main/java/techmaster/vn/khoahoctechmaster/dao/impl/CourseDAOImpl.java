package techmaster.vn.khoahoctechmaster.dao.impl;

import org.springframework.stereotype.Repository;
import techmaster.vn.khoahoctechmaster.dao.CourseDAO;
import techmaster.vn.khoahoctechmaster.db.CourseDB;
import techmaster.vn.khoahoctechmaster.model.Course;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {
    @Override
    public List<Course> findAll() {
        return CourseDB.courseList;
    }



}
