package eWalletSystem.Service;

import eWalletSystem.model.Account;

public interface AccountService {
   boolean createAccount(Account account);
   boolean login(Account account);
   int deposit(Account account, double money);
   Account getAccountDetails(Account account);
   int WithdrawSuccess(Account account , double money);
   int transfer(Account account, String UsernameAccountTransfer , double money);
   int checkPassword(Account account , String password) ;
   int isPhoneNumberExist(String PhoneNumber);
}
