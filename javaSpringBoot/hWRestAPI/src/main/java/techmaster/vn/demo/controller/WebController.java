package techmaster.vn.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import techmaster.vn.demo.model.BMI;

import java.util.*;

@CrossOrigin
@Controller
@RestController

public class WebController {
    private List<BMI> listBMI;

    public WebController() {
        System.out.println("Web controller created");
        listBMI = new ArrayList<>();
    }

    //    @GetMapping("/random-color")
    @RequestMapping(value = "/random-color", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRandomColor(@RequestParam("type") String type) {
        String color = generateRandomColor(type);
        return "{\"color\":\"" + color + "\"}";
    }

    private String generateRandomColor(String type) {
        String hexColor;
        // 3: Generate a random RGB color
        if (type.equalsIgnoreCase("3")) {
            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);
            return String.format("rgb(%d,%d,%d)", red, green, blue);

        }
        // 2: Generate a random HEX color
        else if (type.equalsIgnoreCase("2")) {

            // Generate random HEX color logic here
            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);
            return String.format("#%02x%02x%02x", red, green, blue);

        } else if (type.equalsIgnoreCase("1")) {
            String[] colorNames = {
                    "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet",
                    "Black", "White", "Gray", "Pink", "Purple", "Brown", "Cyan"
            };
            Random random = new Random();

            // Generate random color name index
            int index = random.nextInt(colorNames.length);

            // tranh loi tham chieu ra ngoai mang
            if (index == colorNames.length) index--;

            return colorNames[index];

        }
        // Handle unsupported types
        else {
            return "Type không hợp lệ";
        }
    }

    @RequestMapping(value = "/bmi", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> calcBMI(@RequestParam("height") double height, @RequestParam("weight") double weight) {
        try {
            if (height > 0 && weight > 0) {
                BMI bmi = new BMI();
                bmi.setWeight(weight);
                bmi.setHeight(height);
                String resultJson = "{\"BMI\":\"" + String.valueOf(weight / (height * height)) + "\"}";
                return ResponseEntity.ok(resultJson);
            }
            // test cach 1
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NumberFormatException e) {
            // test cach 2
            return ResponseEntity.badRequest().body("Bad Request: " + e.getMessage());
        }
    }


}
