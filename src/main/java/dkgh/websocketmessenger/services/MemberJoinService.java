package dkgh.websocketmessenger.services;

import dkgh.websocketmessenger.domains.Member;
import dkgh.websocketmessenger.exceptions.BadRequestException;
import dkgh.websocketmessenger.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberJoinService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(Member member) {
        memberRepository.findByNickname(member.getNickname()).ifPresent(m -> {
            throw new BadRequestException("["+m.getNickname()+"]"+"는 중복되는 닉네임");
        });
        return memberRepository.save(member);
    }
    
}
