package dkgh.websocketmessenger.controllers;

import dkgh.websocketmessenger.domains.ChattingRoom;
import dkgh.websocketmessenger.domains.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ChatController {

    @PostMapping("/add-user")
    public void addUser(@RequestBody String nickname) {
        log.debug("nickname : {}", nickname);
    }

    @MessageMapping("/enter")
    @SendTo("/send")
    public ChattingRoom enter(ChattingRoom chattingRoom) {
        List<String> users = chattingRoom.getUsers();
        String sender = chattingRoom.getMessage().getSender();
        log.debug("original-users : {}", users);
        log.debug("sender : {}", sender);

        users.add(sender);

        chattingRoom.setUsers(users);
        chattingRoom.getMessage().setContent(sender + "입장 !!!");

        return chattingRoom;
    }

    @MessageMapping("/receive")
    @SendTo("/send")
    public Message sendChatting(Message message) {
        log.debug("msg : {}", message);
        return message;
    }

}
