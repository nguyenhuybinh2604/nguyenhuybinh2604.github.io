package techmaster.vn.khoahoctechmaster.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    Integer id; // Id khóa học
    String name; // Tên khóa học
    String description; // Mô tả khóa học
    String type; // Hình thức học (chỉ có 2 hình thức online/onlab)
    List<String> topics; // danh sách các topic của khóa học (Ví dụ : frontend, backend, database, devops, basic, mobile, ...)
    String thumbnail; // Ảnh khóa học
    Integer userId; // Id người tư vấn khóa học
}
