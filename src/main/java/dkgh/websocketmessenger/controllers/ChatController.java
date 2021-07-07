package dkgh.websocketmessenger.controllers;

import dkgh.websocketmessenger.domains.ChattingRoom;
import dkgh.websocketmessenger.domains.Member;
import dkgh.websocketmessenger.domains.Message;
import dkgh.websocketmessenger.services.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final MemberService memberService;

    @PostMapping("/add-user")
    public void addUser(@RequestBody Member member) {
        log.debug("member : {}", member);
        member = memberService.join(member);
        log.debug("saved-member : {}", member);
    }

    @GetMapping("/users")
    public List<String> getUsers() {
        return memberService.findAllNickNames();
    }

    @MessageMapping("/enter")
    @SendTo("/send")
    public ChattingRoom enter(ChattingRoom chattingRoom) {
        List<String> users = memberService.findAllNickNames();
        String sender = chattingRoom.getMessage().getSender();
        log.debug("original-users : {}", users);
        log.debug("sender : {}", sender);


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
