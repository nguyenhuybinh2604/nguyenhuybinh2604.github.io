package techmaster.vn.khoahoctechmaster.db;

import techmaster.vn.khoahoctechmaster.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDB {
    public static List<Course> courseList = new ArrayList<>(
            List.of(
                    new Course(1
                            , "Data Structure - Algorithm - LeetCode"
                            , "Description 1"
                            , "Onlab"
                            , List.of("basic")
                            , "https://techmaster.vn/resources/image/thumbnail.jpg"
                            , 1),
                    new Course(2
                            , "Java căn bản"
                            , "Description 2"
                            , "Onlab"
                            , List.of("basic","devops")
                            , "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/ZuedW7J1"
                            , 2),
                    new Course(3
                            , "Học lập trình Java trực tuyến"
                            , "Description 3"
                            , "Online"
                            , List.of("basic")
                            , "https://media.techmaster.vn/api/fileman/Uploads/Java/banner-javabasic.png"
                            , 3),
                    new Course(4
                            , "Luyện thi FE"
                            , "Description 4"
                            , "Onlab"
                            , List.of("basic")
                            , "https://media.techmaster.vn/api/static/36/uhWHezPC"
                            , 4),
                    new Course(5
                            , "Spring Boot Web Backend"
                            , "Description 5"
                            , "Onlab"
                            , List.of("basic")
                            , "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg"
                            , 5),
                    new Course(6
                            , "Lập trình lego robot inventor"
                            , "Description 6"
                            , "Onlab"
                            , List.of("basic")
                            , "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/eQcu6FM-"
                            , 6),
                    new Course(7
                            , "Lộ trình Devops"
                            , "Description 7"
                            , "Onlab"
                            , List.of("basic")
                            , "https://devops.techmaster.vn/resources/image/banner_devops.png"
                            , 7),
                    new Course(8
                            , "Lập trình Nodejs"
                            , "Description 8"
                            , "Onlab"
                            , List.of("basic")
                            , "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/6PyUoB-T"
                            , 8)
            )
    );
}
