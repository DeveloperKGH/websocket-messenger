package dkgh.websocketmessenger.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handle400Exception(HttpServletRequest request, BadRequestException exception) {
		log.error("request : {}", request);
		return new ErrorResponse(exception.getHttpStatus(), exception.getMessage(), exception.getDetailMessage());
	}

}