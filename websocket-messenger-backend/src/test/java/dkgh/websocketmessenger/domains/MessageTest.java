package dkgh.websocketmessenger.domains;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    public void message() {
        //given
        Message message = Message.builder()
                .messageType(MessageType.CHAT)
                .content("테스트")
                .sender("Beau")
                .build();

        //when
        MessageType messageType = message.getMessageType();
        String content = message.getContent();
        String sender = message.getSender();

        //then
        Assertions.assertThat(messageType).isEqualTo(MessageType.CHAT);
        Assertions.assertThat(content).isEqualTo("테스트");
        Assertions.assertThat(sender).isEqualTo("Beau");

    }

}