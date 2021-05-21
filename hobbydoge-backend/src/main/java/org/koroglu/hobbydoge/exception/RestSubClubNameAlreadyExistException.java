package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestSubClubNameAlreadyExistException extends BaseException {
    public RestSubClubNameAlreadyExistException() {
        super(BaseExceptionType.REST_SUB_CLUB_NAME_ALREADY_EXIST_EXCEPTION, null, null);
    }

    @Builder
    public RestSubClubNameAlreadyExistException(String message, List<String> errors) {
        super(BaseExceptionType.REST_SUB_CLUB_NAME_ALREADY_EXIST_EXCEPTION, message, errors);
    }
}
