package dkgh.websocketmessenger.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class BusinessException extends RuntimeException {

    protected Integer httpStatus;

    protected String errorMessage;

    protected String detailMessage;

}
