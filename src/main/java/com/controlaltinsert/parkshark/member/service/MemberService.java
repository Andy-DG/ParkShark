package com.controlaltinsert.parkshark.member.service;

import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.member.domain.MemberRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberService {
    private final Logger memberServiceLogger = LoggerFactory.getLogger(MemberService.class);

    MemberRepository memberRepository;
    MemberMapper memberMapper;

    public MemberDTO createMember(CreateMemberDTO createMemberDTO) {
        Member member = memberMapper.toEntity(createMemberDTO);
        this.memberRepository.save(member);
        return this.memberMapper.toDTO(member);
    }

    public List<MemberDTO> getAllMembers() {
        return this.memberMapper.toDTO(this.memberRepository.findAll());
    }

    public MemberDTO getMemberById(int id) {
        Member member = memberRepository.findById(id).orElse(null);
        assertMemberExists(member);
        return memberMapper.toDTO(member);
    }

    private void assertMemberExists(Member member) {
        if (member == null) {
            String errorMessage = "Member not found!";
            memberServiceLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
