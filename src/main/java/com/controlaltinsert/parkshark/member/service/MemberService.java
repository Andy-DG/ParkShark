package com.controlaltinsert.parkshark.member.service;

import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.member.domain.MemberRepository;
import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        return this.memberMapper.toDTO(this.memberRepository.findAll());
    }

    public MemberDTO getMemberById(int id) {
        Member member = memberRepository.findById(id).orElse(null);
        Validate.objectNotNull(member, "Member not found!");
        return memberMapper.toDTO(member);
    }

}
