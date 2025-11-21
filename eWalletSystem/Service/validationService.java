package eWalletSystem.Service;

import eWalletSystem.model.Account;

public interface validationService {
    boolean isValidUserName(String Name);
    boolean isValidPassword(String Password);
    boolean isValidAge(int age);
    boolean isValidPhoneNumber(String PhoneNumber);

}
