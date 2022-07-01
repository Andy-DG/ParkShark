package com.controlaltinsert.parkshark.member.domain;

import com.controlaltinsert.parkshark.member.level.domain.MembershipLevel;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

import static com.controlaltinsert.parkshark.util.Validate.*;

@Entity
@Table(name = "member", schema = "parkshark")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    public static final MembershipLevel DEFAULT_MEMBERSHIP_LEVEL = MembershipLevel.BRONZE;
    static Logger memberLogger = LoggerFactory.getLogger(Member.class);

    @Id
    @SequenceGenerator(name = "member_id_seq", sequenceName = "member_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "member_id_seq")
    int id;

    @Column(name="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "mobile_phone_number")
    String mobile;

    @Column(name = "phone_number")
    String phone;

    @Column(name = "email")
    String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    Address address;

    @Column(name = "registration_date")
    LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_license_plate_id")
    LicensePlate licensePlate;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "fk_membership_level_id")
    MembershipLevel fkMembershipLevelId = DEFAULT_MEMBERSHIP_LEVEL;

    public Member(String firstName, String lastName, String mobile, String phone, String email, Address fk_address_id, LocalDate registrationDate, LicensePlate licensePlate, MembershipLevel membershipLevel) {
        this.firstName = validateString(firstName, "Firstname");
        this.lastName = validateString(lastName, "Lastname");
        validateToHaveAPhoneNumber(phone, mobile);
        this.mobile = mobile;
        this.phone = phone;
        this.email = validateEmail(email);
        this.address = validateAddress(fk_address_id);
        this.registrationDate = registrationDate;
        this.licensePlate = validateLicensePlate(licensePlate);
        this.fkMembershipLevelId = validateMembershipLevel(membershipLevel);
    }

    private LicensePlate validateLicensePlate(LicensePlate licensePlate) {
        if (licensePlate == null) {
            String errorMessage = "License plate can't be null";
            memberLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return licensePlate;
    }

    MembershipLevel validateMembershipLevel(MembershipLevel membershipLevel){
        if (membershipLevel == null){
            String errorMessage = "Must specify a membership level!";
            memberLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return membershipLevel;
    }
}

