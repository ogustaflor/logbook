package ogustaflor.com.github.logbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String s) {
        super(s);
    }

}
