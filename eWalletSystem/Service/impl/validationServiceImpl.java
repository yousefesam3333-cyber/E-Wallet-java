package eWalletSystem.Service.impl;

import eWalletSystem.Service.validationService;
import eWalletSystem.model.Account;

public class validationServiceImpl implements validationService {
    @Override
    public boolean isValidUserName(String Name) {
        if(Name.length()>=3&&Character.isUpperCase(Name.charAt(0))) {
            return true;
        }
        return false;
    }



    @Override
    public boolean isValidPassword(String Password) {
        boolean isUpper = false;
        boolean isLower = false;
        boolean isDigit = false;
        boolean isSpecial = false;
        if(Password.length()>=6) {
            for(int i=0; i<Password.length(); i++) {
                if(Character.isUpperCase(Password.charAt(i))) {
                    isUpper = true;
                }
                if(Character.isLowerCase(Password.charAt(i))) {
                    isLower = true;
                }
                if(Character.isDigit(Password.charAt(i))) {
                    isDigit = true;
                }
                if(Password.charAt(i)=='#'||Password.charAt(i)=='$'||Password.charAt(i)=='@'||Password.charAt(i)=='&') {
                    isSpecial = true;
                }
                if(isUpper==true && isLower==true && isDigit==true && isSpecial==true) {
                    return true;

                }
            }
        }
        return false;
    }

    @Override
    public boolean isValidAge(int age) {
        if(age>=18&&age<=80) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidPhoneNumber(String PhoneNumber) {
        if(PhoneNumber.length()==12&&(PhoneNumber.startsWith("2010")||PhoneNumber.startsWith("2011")||PhoneNumber.startsWith("2012"))) {
            return true;
        }
        return false;
    }
}
