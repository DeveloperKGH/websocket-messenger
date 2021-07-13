package dkgh.websocketmessenger.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BadRequestException extends BusinessException {

	public BadRequestException(String msg) {
		super(400, "BAD_REQUEST", msg);
	}

}