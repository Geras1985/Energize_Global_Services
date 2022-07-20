package com.hotel.villa.util;

import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static final String regularExpression = "^[a-zA-Z][a-zA-Z0-9_]{6,19}$";

    static boolean validateCardExpiryDate(String expiryDate) {
        return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
    }

    /**
     * The method to validate the username
     */
    public static boolean isValidUsername(String username) {

        // Regex to check valid username.
        String regex = "^[A-Za-z][a-zA-Z0-9_]{5,29}$";

        Pattern p = Pattern.compile(regex);

        if (username == null) {
            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();
    }

    /**
     * Generated random string of integer like this: 1234 5678 9101 1121, if bin = 1234 and length=16.
     */
    public static String generate(String bin, int length) {
        Random random = new Random(System.currentTimeMillis());
        int randomNumberLength = length - (bin.length() + 1);

        StringBuilder builder = new StringBuilder(bin);
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }

        int checkDigit = getCheckDigit(builder.toString());
        builder.append(checkDigit);
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < builder.length(); i++) {
            newBuilder.append(builder.charAt(i));
            if ((i + 1) % 4 == 0) {
                newBuilder.append(' ');

            }
        }
        return newBuilder.toString();
    }

    // Returns true if given
// card number is valid
    public static boolean checkLuhn(String cardNo) {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 5; i--) {

            int d = cardNo.charAt(i) - '0';

            if (isSecond)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return nSum % 10 == 0;
    }

    /**
     * Calculated the date.
     */
    public static Date calculateDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();

        return date;
    }


//    /**
//     * Generated random string.
//     */
//    public static String randomString(Integer size) {
//        int leftLimit = 97; // letter 'a'
//        int rightLimit = 122; // letter 'z'
//        int targetStringLength = size;
//        Random random = new Random();
//
//        return random.ints(leftLimit, rightLimit + 1)
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//    }

    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
