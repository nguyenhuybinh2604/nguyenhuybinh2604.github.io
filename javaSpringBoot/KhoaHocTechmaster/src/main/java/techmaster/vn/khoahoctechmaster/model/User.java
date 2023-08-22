package techmaster.vn.khoahoctechmaster.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id; // Id của user
    String name; // Tên user
    String email;  // Email user
    String phone; // Số điện thoại
    String avatar; // Ảnh đại diện
}
