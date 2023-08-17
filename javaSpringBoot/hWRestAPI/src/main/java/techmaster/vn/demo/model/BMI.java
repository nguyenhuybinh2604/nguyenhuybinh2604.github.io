package techmaster.vn.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@CrossOrigin
public class BMI {
    double height;
    double weight;

    public BMI(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public BMI() {
    }

}
