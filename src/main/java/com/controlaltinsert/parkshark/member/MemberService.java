package com.controlaltinsert.parkshark.member;

import com.controlaltinsert.parkshark.member.api.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberService {
    MemberRepository memberRepository;
    MemberMapper memberMapper;

    public MemberDTO createMember(CreateMemberDTO createMemberDTO) {
        Member member = memberMapper.toEntity(createMemberDTO);
        this.memberRepository.save(member);
        return this.memberMapper.toDTO(member);
    }

}
