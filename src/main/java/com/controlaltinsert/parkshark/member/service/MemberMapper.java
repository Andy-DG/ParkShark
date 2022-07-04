package com.controlaltinsert.parkshark.member.service;

import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.support.licenseplate.service.LicensePlateMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberMapper {
    LicensePlateMapper licensePlateMapper;
    AddressMapper addressMapper;

    public Member toEntity(CreateMemberDTO createMemberDTO) {
        return new Member(
                createMemberDTO.getFirstName(),
                createMemberDTO.getLastName(),
                createMemberDTO.getMobile(),
                createMemberDTO.getPhone(),
                createMemberDTO.getEmail(),
                addressMapper.toEntity(createMemberDTO.getCreateAddressDTO()),
                createMemberDTO.getRegistrationDate(),
                licensePlateMapper.toEntity(createMemberDTO.getCreateLicensePlateDTO()),
                createMemberDTO.getMembershipLevel());
    }

    public Member toEntity(MemberDTO memberDTO) {
        return new Member(
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getMobile(),
                memberDTO.getPhone(),
                memberDTO.getEmail(),
                addressMapper.toEntity(memberDTO.getAddressDTO()),
                memberDTO.getRegistrationDate(),
                licensePlateMapper.toEntity(memberDTO.getLicensePlateDTO()),
                memberDTO.getMembershipLevel());
    }

    public MemberDTO toDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getMobile(),
                member.getPhone(),
                member.getEmail(),
                addressMapper.toDTO(member.getAddress()),
                member.getRegistrationDate(),
                licensePlateMapper.toDTO(member.getLicensePlate()),
                member.getFkMembershipLevelId());
    }

    public List<MemberDTO> toDTO(List<Member> members) {
        return members.stream().map(this::toDTO).toList();
    }
}
