package techmaster.vn.khoahoctechmaster.service;

import org.springframework.stereotype.Service;
import techmaster.vn.khoahoctechmaster.db.CourseDB;
import techmaster.vn.khoahoctechmaster.model.Course;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> getAllCourses(String type, String name, String topic) {
        return CourseDB.courseList.stream()
                .filter(course -> (type == null || course.getType().equals(type)))
                .filter(course -> (name == null || course.getName().equals(name)))
                .filter(course -> (topic == null || course.getTopics().equals(topic)))
                .collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(Integer id) {
        return CourseDB.courseList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new RuntimeException("Course not found");
                });
    }



}
