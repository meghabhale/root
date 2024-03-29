package assignmentPassword;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Password {
    public static  final int MINIMUM_DIGIT_IN_PASSWORD = 5;
    public static  final int MINIMUM_LOWER_CASE_IN_PASSWORD = 1;
    public static  final int MINIMUM_UPPER_CASE_IN_PASSWORD = 2;
    private String password;
    private int length;

    public Password(int length) {
        this.length = length;
        isStrong(generatePassword(this.length));
    }

    public Password() throws Exception{
        this.length = 8;
        generatePassword(length);
    }

    public String generatePassword(int length){
        String[]  strAlphaNumeric = {"0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz"};

        StringBuilder newPassword = new StringBuilder(length);

        for(int i=0;i < MINIMUM_DIGIT_IN_PASSWORD;i++){
            generateString(strAlphaNumeric[0], newPassword);
        }
        for(int i=0;i < MINIMUM_UPPER_CASE_IN_PASSWORD;i++){
            generateString(strAlphaNumeric[1], newPassword);
        }
        for(int i=0;i < MINIMUM_LOWER_CASE_IN_PASSWORD;i++){
            generateString(strAlphaNumeric[2], newPassword);
        }

        for(int i = 0, k=newPassword.length(); k < length; i++, k++){
            if(i==strAlphaNumeric.length) i=0;
            generateString(strAlphaNumeric[i],newPassword);
        }
        return  shuffleString(newPassword.toString());
    }

    public String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        this.password = letters.stream().collect(Collectors.joining());
        return password;

    }


    public String generateString(String str, StringBuilder newPassword){
        int index = (int)(str.length() * Math.random());
        newPassword.append(str.charAt(index));
        return  str;
    }

    public boolean isStrong(String password){
        if(password==null || password.length() < 8)     return false;

        int totalNumChar=0;int totalLowerChars=0, totalUpperCase = 0;

        for(int i=0;i<password.length();i++) {
            if (Pattern.matches("[0-9]", password.substring(i, (i + 1)))) {
                totalNumChar++;
            } else if (Pattern.matches("[a-z]", password.substring(i, (i + 1)))) {
                totalLowerChars++;
            } else if (Pattern.matches("[A-Z]", password.substring(i, (i + 1)))) {
                totalUpperCase++;
            }
        }

        if(totalNumChar > MINIMUM_DIGIT_IN_PASSWORD
                && totalLowerChars > MINIMUM_LOWER_CASE_IN_PASSWORD
                && totalUpperCase > MINIMUM_UPPER_CASE_IN_PASSWORD) return  true;
        return false;
    }

    public String getPassword() {
        return password;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
