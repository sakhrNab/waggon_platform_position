package de.db.waggons_platform_case_study.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WagonNotFoundException extends RuntimeException {

    public WagonNotFoundException(String message) {
        super(message);
    }

}
