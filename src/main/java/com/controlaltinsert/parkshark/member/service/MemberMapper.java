package com.controlaltinsert.parkshark.member.service;

import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.support.licenseplate.service.LicensePlateMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberMapper {
    LicensePlateMapper licensePlateMapper;
    AddressMapper addressMapper;

    public Member toEntity(CreateMemberDTO createMemberDTO) {
        LicensePlate licensePlate = licensePlateMapper.toEntity(createMemberDTO.getLicensePlateDTO());
        Address address = addressMapper.toEntity(createMemberDTO.getAddressDTO());

        return new Member(
                createMemberDTO.getFirstName(),
                createMemberDTO.getLastName(),
                createMemberDTO.getMobile(),
                createMemberDTO.getPhone(),
                createMemberDTO.getEmail(),
                address,
                createMemberDTO.getRegistrationDate(),
                licensePlate);
    }

    public MemberDTO toDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getMobile(),
                member.getPhone(),
                member.getEmail(),
                member.getAddress().getId(),
                member.getRegistrationDate(),
                member.getLicensePlate());
    }
}
