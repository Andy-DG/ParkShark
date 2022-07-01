package com.controlaltinsert.parkshark.member.service;

import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.member.domain.MemberRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<MemberDTO> getAllMembers() {
        List<Member> members = this.memberRepository.findAll();
        return members.stream().map(member -> memberMapper.toDTO(member)).collect(Collectors.toUnmodifiableList());
    }
}
