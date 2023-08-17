package techmaster.vn.demo.exception;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
