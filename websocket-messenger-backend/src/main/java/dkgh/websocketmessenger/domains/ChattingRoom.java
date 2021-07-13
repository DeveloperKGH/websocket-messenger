package dkgh.websocketmessenger.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ChattingRoom {

    private List<String> users;

    private Message message;

}
