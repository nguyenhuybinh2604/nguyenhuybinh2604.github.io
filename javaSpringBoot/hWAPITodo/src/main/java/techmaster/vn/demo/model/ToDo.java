package techmaster.vn.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDo {
    private Integer id;

    @NotNull(message = "Tiêu đề công vieecj không được để trống")
    private String title;

    @NotNull(message = "Trạng thái công việc không được để trống")
    private Boolean status;

}
