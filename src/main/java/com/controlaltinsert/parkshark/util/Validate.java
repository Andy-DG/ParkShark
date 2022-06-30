package com.controlaltinsert.parkshark.util;

import com.controlaltinsert.parkshark.support.address.domain.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Transient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static final String PHONE_VALIDATION =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Transient
    private static final Logger validateLogger = LoggerFactory.getLogger(Validate.class);


    //  Phone-number has to be of one of the following formats: 2055550125, 202 555 0125, (202) 555-0125, +111 (202) 555-0125,
    //      636 856 789, +111 636 856 789, 636 85 67 89, +111 636 85 67 89
    public static void validateToHaveAPhoneNumber(String phoneNumber, String mobilePhoneNumber) {
        if (phoneNumber == null && mobilePhoneNumber == null) {
            String errorMessage = "You need either a phone-number or a mobile phone-number";
            validateLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        } else {
            if (!isNullOrBlank(phoneNumber)) {
                validatePhoneNumberFormat(phoneNumber);
            } else {
                validatePhoneNumberFormat(mobilePhoneNumber);
            }
        }
    }

    public static String validatePhoneNumberFormat(String phoneNumber) {
        if (isNullOrBlank(phoneNumber)) {
            String errorMessage = "Phone-number cannot be empty";
            validateLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        Pattern pattern = Pattern.compile(PHONE_VALIDATION);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            String errorMessage = "Invalid phone-number format";
            validateLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return phoneNumber;
    }

    public static boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    public static String validateEmail(String email) {
        if (email == null) {
            String errorMessage = "Email cannot be empty";
            validateLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            String errorMessage = "Invalid email format";
            validateLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return email;
    }

    public static String validateString(String string, String errorSpecifics) {
        if (Validate.isNullOrBlank(string)) {
            String message = errorSpecifics + " - cannot be null or empty";
            validateLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return string;
    }

    public static Address validateAddress(Address address) {
        if (address == null) {
            String message = "Address cannot be null";
            validateLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return address;
    }
}
