package dkgh.websocketmessenger.domains;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Message {

    private MessageType messageType;

    private String content;

    private String sender;

}
