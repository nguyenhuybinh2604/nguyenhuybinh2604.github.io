package techmaster.vn.demo.exception;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
