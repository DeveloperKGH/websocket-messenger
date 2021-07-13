package dkgh.websocketmessenger.domains;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Message {

    private MessageType type;

    private String content;

    private String sender;

}
