package dkgh.websocketmessenger.controllers;

import dkgh.websocketmessenger.domains.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/send")
    @SendTo("/topic/public")
    public Message send(Message message) {
        return message;
    }

}
