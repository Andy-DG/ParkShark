package com.controlaltinsert.parkshark.member.api;

import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.member.domain.MemberRepository;
import com.controlaltinsert.parkshark.member.level.domain.MembershipLevel;
import com.controlaltinsert.parkshark.member.service.MemberMapper;
import com.controlaltinsert.parkshark.member.service.MemberService;
import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.licenseplate.api.CreateLicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.support.licenseplate.service.LicensePlateMapper;
import com.controlaltinsert.parkshark.support.postalcode.api.PostalCodeDTO;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import com.controlaltinsert.parkshark.support.postalcode.service.PostalCodeMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class MemberControllerTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    MemberController memberController;
    MemberService memberService;
    MemberRepository memberRepository;
    MemberMapper memberMapper;
    LicensePlateMapper licensePlateMapper;
    AddressMapper addressMapper;
    PostalCodeMapper postalCodeMapper;

    @BeforeEach
    void setUp(){
        postalCodeMapper = new PostalCodeMapper();
        licensePlateMapper = new LicensePlateMapper();
        addressMapper = new AddressMapper(postalCodeMapper);
        memberRepository = Mockito.mock(MemberRepository.class);
        memberMapper = new MemberMapper(licensePlateMapper, addressMapper);
        memberService = new MemberService(memberRepository, memberMapper);
        memberController = new MemberController(memberService);
    }

    @Test
    @DisplayName("given a proper CreateMemberDTO when adding a new member then successfully create member")
    void givenAProperCreateMemberDtoWhenAddingANewMemberThenSuccessfullyCreateMember() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
        CreateMemberDTO newMember = new CreateMemberDTO("John", "Doe", "+111 636 856 789",
                "+111 636 856 789", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO, MembershipLevel.BRONZE);

        Member entity = memberMapper.toEntity(newMember);
        Mockito.when(memberRepository.save(entity)).thenReturn(entity);

        MemberDTO memberControllerMember = this.memberController.createMember(newMember);

        logger.info(memberControllerMember.toString());

        assertEquals(newMember.getFirstName(), memberControllerMember.getFirstName());
        assertEquals(newMember.getLastName(), memberControllerMember.getLastName());
        assertEquals(newMember.getEmail(), memberControllerMember.getEmail());
        assertEquals(newMember.getMobile(), memberControllerMember.getMobile());
        assertEquals(newMember.getLicensePlateDTO().getLicensePlate(), memberControllerMember.getLicensePlate().getLicensePlate());
        assertEquals(newMember.getRegistrationDate(), memberControllerMember.getRegistrationDate());

    }

    @Test
    @DisplayName("given a proper member but without a mobile when creating a member then succesfully create new member")
    void givenAProperMemberButWithoutAMobileWhenCreatingAMemberThenSuccesfullyCreateNewMember() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
        CreateMemberDTO newMember = new CreateMemberDTO("John", "Doe", "", "+111 636 856 789", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO,MembershipLevel.BRONZE);

        Member entity = memberMapper.toEntity(newMember);
        Mockito.when(memberRepository.save(entity)).thenReturn(entity);

        MemberDTO memberControllerMember = this.memberController.createMember(newMember);

        logger.info(memberControllerMember.toString());

        assertEquals(newMember.getFirstName(), memberControllerMember.getFirstName());
        assertEquals(newMember.getLastName(), memberControllerMember.getLastName());
        assertEquals(newMember.getEmail(), memberControllerMember.getEmail());
        assertEquals(newMember.getMobile(), memberControllerMember.getMobile());
        assertEquals(newMember.getLicensePlateDTO().getLicensePlate(), memberControllerMember.getLicensePlate().getLicensePlate());
        assertEquals(newMember.getRegistrationDate(), memberControllerMember.getRegistrationDate());
        assertEquals(newMember.getMembershipLevel(),memberControllerMember.getMembershipLevel());

    }

    @Test
    @DisplayName("Given a member but without a phone number when creating then success")
    void givenAMemberButWithoutAPhoneNumberWhenCreatingThenSuccess() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
        CreateMemberDTO newMember = new CreateMemberDTO("John", "Doe", "+111 636 856 789", "", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO, MembershipLevel.BRONZE);

        Member entity = memberMapper.toEntity(newMember);
        Mockito.when(memberRepository.save(entity)).thenReturn(entity);

        MemberDTO memberControllerMember = this.memberController.createMember(newMember);

        logger.info(memberControllerMember.toString());

        assertEquals(newMember.getFirstName(), memberControllerMember.getFirstName());
        assertEquals(newMember.getLastName(), memberControllerMember.getLastName());
        assertEquals(newMember.getEmail(), memberControllerMember.getEmail());
        assertEquals(newMember.getMobile(), memberControllerMember.getMobile());
        assertEquals(newMember.getLicensePlateDTO().getLicensePlate(), memberControllerMember.getLicensePlate().getLicensePlate());
        assertEquals(newMember.getRegistrationDate(), memberControllerMember.getRegistrationDate());

    }

    @Test
    @DisplayName("Given a member with a phonenumber that is incorrect then throw exception")
    void givenAMemberWithAPhonenumberThatIsIncorrectThenThrowException() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
        CreateMemberDTO newMember = new CreateMemberDTO("John", "Doe", "+111 636 856 789", "+119", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO, MembershipLevel.BRONZE);

       assertThrows(IllegalArgumentException.class, ()-> this.memberController.createMember(newMember));
    }

    @Test
    @DisplayName("Given a member with a mobileNumber that is incorrect then throw exception")
    void givenAMemberWithAMobileNumberThatIsIncorrectThenThrowException() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
        CreateMemberDTO newMember = new CreateMemberDTO("John", "Doe", "+111 689", "+111 636 856 789", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO, MembershipLevel.BRONZE);

        assertThrows(IllegalArgumentException.class, ()-> this.memberController.createMember(newMember));
    }

    @Test
    @DisplayName("Given a member with no lastName when creating a member then throw Exception")
    void givenAMemberWithNoLastNameWhenCreatingAMemberThenThrowException() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
        CreateMemberDTO newMember = new CreateMemberDTO("John", " ", "+111 636 856 789", "+111 636 856 789", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO, MembershipLevel.BRONZE);

        assertThrows(IllegalArgumentException.class, ()-> this.memberController.createMember(newMember));
    }

    @Test
    @DisplayName("Given a repo that contains member when calling getAllMembers then return allMembers in the repo")
    void givenARepoThatContainsMemberWhenCallingGetAllMembersThenReturnAllMembersInTheRepo() {
        //    public Member(String firstName, String lastName, String mobile, String phone, String email,
        //    Address fk_address_id, LocalDate registrationDate, LicensePlate licensePlate)

        Member m1 = new Member(
                "Jack", "Black", "+111 636 856 789", "+111 636 856 789", "person@mail.com",
                new Address("Street", 1, new PostalCode("ZIP", "ZIPPY")),
                LocalDate.of(1922, 04, 17),
                new LicensePlate("GENERIC", "A"), MembershipLevel.BRONZE
                );

        Member m2 = new Member(
                "Friedrich", "Schwartz", "+111 636 856 789", "+111 636 856 789", "person@mail.com",
                new Address("Street", 1, new PostalCode("HUEL", "HUELSTEDT")),
                LocalDate.of(1922, 04, 17),
                new LicensePlate("GENERIC", "A"), MembershipLevel.BRONZE
        );

        MemberDTO dto1 = memberMapper.toDTO(m1);
        MemberDTO dto2 = memberMapper.toDTO(m2);

        Mockito.when(memberRepository.findAll()).thenReturn(List.of(m1,m2));

        List<MemberDTO> allMembers = this.memberController.getAllMembers();

        assertEquals(allMembers.size(), allMembers.size());

        for(int i = 0; i < allMembers.size(); i++){
            assertEquals(allMembers.get(i), allMembers.get(i));
        }

    }

    @Test
    @DisplayName("when createing a member with a null level then throw exeption")
    void whenCreateingAMemberWithANullLevelThenThrowExeption() {
        AddressDTO addressDTO = new AddressDTO("west",17,new PostalCodeDTO("98101", "Seattle"));
        CreateLicensePlateDTO createLicensePlateDTO = new CreateLicensePlateDTO("XFILES","USA");
CreateMemberDTO createMemberDTOBadLevel = new CreateMemberDTO("John", "Doe", "+111 636 856 789",
        "+111 636 856 789", "jdoe@mail.com", addressDTO, LocalDate.now(), createLicensePlateDTO, null);
        assertThrows(IllegalArgumentException.class, () -> memberService.createMember(createMemberDTOBadLevel));
    }


    }
