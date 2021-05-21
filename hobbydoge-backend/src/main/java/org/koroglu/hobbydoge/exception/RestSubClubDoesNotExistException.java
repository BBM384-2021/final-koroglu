package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestSubClubDoesNotExistException extends BaseException{
    public RestSubClubDoesNotExistException() {super(BaseExceptionType.REST_SUB_CLUB_DOES_NOT_EXIST_EXCEPTION, null, null);
    }
    @Builder
    public RestSubClubDoesNotExistException(String message, List<String> errors) {
        super(BaseExceptionType.REST_SUB_CLUB_DOES_NOT_EXIST_EXCEPTION, message, errors);
    }
}
