package dkgh.websocketmessenger.controllers;

import dkgh.websocketmessenger.domains.Message;
import dkgh.websocketmessenger.domains.MessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @PostMapping("/add-user")
    @SendTo("/send")
    public Message addUser(@RequestBody String nickname) {
        System.out.println("nickname = " + nickname);
        return Message.builder()
                .messageType(MessageType.ENTER)
                .sender("관리자")
                .content(nickname + "입장 !!")
                .build();
    }

    @MessageMapping("/receive")
    @SendTo("/send")
    public Message sendChatting(Message message) {
        System.out.println("message = " + message);
        message.setMessageType(MessageType.CHAT);
        return message;
    }

}
