package dkgh.websocketmessenger.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ErrorResponse {

    private Integer httpStatus;

    private String errorMessage;

    private String detailMessage;

    public ErrorResponse(Integer httpStatus, String errorMessage, String detailMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.detailMessage = detailMessage;
    }

}
