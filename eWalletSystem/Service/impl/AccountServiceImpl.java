package eWalletSystem.Service.impl;

import eWalletSystem.Service.AccountService;
import eWalletSystem.model.Account;
import eWalletSystem.model.EWallet;

import java.util.List;

public class AccountServiceImpl implements AccountService {
   private EWallet eWallet = new EWallet();

    @Override
    public int transfer(Account account, String UsernameAccountTransfer ,  double money) {
        int SenderaccountIndex = isAccountExist(account);
        int UserNameAccountIndex = isUserNameExist(UsernameAccountTransfer);
        if (SenderaccountIndex == -1) {
            return 1;
        }
        if(UserNameAccountIndex == -1) {
            return 2;
        }
        if(!(eWallet.getAccounts().get(SenderaccountIndex).getBalance()>=money)) {
            return 3;
        }
        if(money<0){
            return 4;
        }
        if(eWallet.getAccounts().get(SenderaccountIndex).getUsername().equals(UsernameAccountTransfer)) {
            return 5;
        }
        eWallet.getAccounts().get(SenderaccountIndex).setBalance(eWallet.getAccounts().get(SenderaccountIndex).getBalance()-money);
        eWallet.getAccounts().get(UserNameAccountIndex).setBalance(eWallet.getAccounts().get(UserNameAccountIndex).getBalance()+money);
        return 6;


    }

    @Override
    public int checkPassword(Account account , String Password) {
        int accountIndex = isAccountExist(account);
        if(accountIndex == -1) {
            return 1 ;
        }
        eWallet.getAccounts().get(accountIndex).setPassword(Password);
        return 2;
    }

    @Override
    public int WithdrawSuccess(Account account, double money) {
        int accountIndex = isAccountExist(account);
        if (accountIndex == -1) {
            return 1;
        }
        if(!(eWallet.getAccounts().get(accountIndex).getBalance()>money)) {

            return 2;
        }
        if(money<0){
            return 3;
        }
        eWallet.getAccounts().get(accountIndex).setBalance(eWallet.getAccounts().get(accountIndex).getBalance()-money);
        return 4;

    }

    @Override
    public Account getAccountDetails(Account account) {
        int accountIndex = isAccountExist(account);
        if (accountIndex == -1) {
            return null ;
        }
        return eWallet.getAccounts().get(accountIndex);
    }

    @Override
    public int deposit(Account account, double money) {
       int accountIndec = isAccountExist(account);
       if (accountIndec == -1) {
           return 1;
       }
       if(money<0) {
           return 2;
       }


           eWallet.getAccounts().get(accountIndec).setBalance(eWallet.getAccounts().get(accountIndec).getBalance() + money);
       return 3;
    }

    private int isAccountExist(Account account) {
        List<Account> accounts = eWallet.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if(account.getUsername().equals(accounts.get(i).getUsername())) {
                return i;
            }
        }
        return -1;
    }
    private int isUserNameExist(String username) {
        List<Account> accounts = eWallet.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }
     public int isPhoneNumberExist(String PhoneNumber) {
        List<Account> accounts = eWallet.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getPhoneNumber().equals(PhoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean createAccount(Account account) {
        if (isAccountExist(account)!=-1) return false;
        eWallet.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean login(Account account) {
        List<Account> accounts = eWallet.getAccounts();
        for (Account a : accounts) {
            if(a.getUsername().equals(account.getUsername()) && a.getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
