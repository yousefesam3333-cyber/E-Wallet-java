package eWalletSystem.Service.impl;

import eWalletSystem.Service.AccountService;
import eWalletSystem.Service.ApplicationService;
import eWalletSystem.Service.validationService;
import eWalletSystem.model.Account;
import eWalletSystem.Service.impl.AccountServiceImpl;

import java.util.Objects;
import java.util.Scanner;

public class ApplicationServicesImpl implements ApplicationService {

    private Scanner scanner = new Scanner(System.in);
    private AccountService accountService = new AccountServiceImpl();
    private validationService validationService = new validationServiceImpl();

    @Override
    public void StartEWalletSystem() {
        System.out.println("Welcome Sir :)");
        int counter = 5;
        while (counter>=0) {
            System.out.println("please choose :");
            System.out.println("1.signup      2.login");
            int choosee =0;
            try{
                Scanner sc = new Scanner(System.in);
                choosee = sc.nextInt();
            }
            catch(Exception e){

            }
            switch (choosee) {
                case 1:
                    signUp();
                    break;
                case 2:
                    logIn();
                    break;
                default:
                    counter--;
                    System.out.println("Invalid choice");
            }
        }

        if (counter==0) {
            System.out.println("Multi invalid choices please try again later");
        }
    }

    /**
     * This function for login
     * */
    private void logIn() {

        Account account = ValidateLoginAccount();
        if(Objects.isNull(account)) {
            System.out.println("Invalid login");
            return;
        }

        if (accountService.login(account)) {
            System.out.println("Login Successful");
            loginFeatures(account);
        }
        else {
            System.out.println("Login Failed");
        }
    }

    private void loginFeatures(Account account) {
        boolean ToLogout = false;
        int counter = 4;
        while (counter>=0) {
            System.out.println("please choose :");
            System.out.println("1.deposit     2.withdraw     3.transfer     4.show account details     5.change password     6.logout");
            int choosee =0;
            try{
                Scanner sc = new Scanner(System.in);
                choosee = sc.nextInt();
            }
            catch(Exception e){

            }
            switch (choosee) {
                case 1:
                    deposit(account);
                    break;
                case 2:
                    Withdraw(account);
                    break;
                case 3:
                    transefer(account);
                    break;
                case 4:
                    showAccountDetails(account);
                    break;
                case 5:
                    changePassword(account);
                    break;
                case 6:
                    System.out.println("Have a nice day");
                    ToLogout = true;
                    break;
                default:
                    counter--;
                    System.out.println("Invalid choice");

            }
            if(counter==0) {
                System.out.println("multi invalid choices please try again later");
            }
            if (ToLogout) {
                break;
            }

        }


    }

    private void changePassword(Account account) {
        System.out.println("Please enter your password");
        String password = scanner.next();
        if(account.getPassword().equals(password)) {
            System.out.println("Enter your new password");
            String newPassword = scanner.next();
            if(!validationService.isValidPassword(newPassword)) {
                System.out.println("Invalid password");
                System.out.println("Password length must be greater than or equal 6 and contain lowercase , uppercase, special letter and digits ");
            }
            else {
                System.out.println("confirm your new password");
                String confirmPassword = scanner.next();
                if(!(newPassword.equals(confirmPassword))) {
                    System.out.println("Passwords do not match");
                }
                else{
                    int checkToChangePassword = accountService.checkPassword(account , confirmPassword);
                    if(checkToChangePassword==1) {
                        System.out.println("Account not exist");
                        return;
                    }
                    System.out.println("Change password Completed successfully");

                }
            }
        }
        else {
            System.out.println("Passwords do not match");
        }
    }

    private void transefer(Account account) {
        System.out.println("Please enter the username you want to transfer to: ");
        String UsernameAccountTransfer = scanner.next();
        System.out.println("Please enter money you want to transfer");
        double money =0;
        int counter = 3;
        while (counter>=0) {
            try {
                Scanner sc = new Scanner(System.in);
                money = sc.nextDouble();
                break;
            }
            catch(Exception e){
                System.out.println("Invalid number , please try again");
                counter--;
            }

        }
        if(counter<0) {
            return;
        }
        int CheckToTransfer = accountService.transfer(account, UsernameAccountTransfer, money);
        if (CheckToTransfer == 1) {
            System.out.println("Account not exist");
            return;
        }
        else if (CheckToTransfer == 2) {
            System.out.println("User name is not exist");
            return;
        }
        else if (CheckToTransfer == 3) {
            System.out.println("The entered amount must be less than the current balance");
            return;
        }
        else if(CheckToTransfer == 4) {
            System.out.println("Please enter a non-negative money");
        } else if (CheckToTransfer == 5) {
            System.out.println("You entered your username , please try again ");
        } else if (CheckToTransfer == 6) {
            System.out.println("The transfer was completed successfully");
        }

    }

    private void Withdraw(Account account) {
        System.out.println("Please enter money you want to withdraw");
        double money= 0;
        int counter = 3;
        while (counter>=0) {
            try {
                Scanner sc = new Scanner(System.in);
                money = sc.nextDouble();
                break;
            }
            catch(Exception e){
                System.out.println("Invalid number , please try again");
                counter--;
            }

        }
        if(counter<0) {
            return;
        }
        int CheckToWithdraw = accountService.WithdrawSuccess(account,money);
        if(CheckToWithdraw==1) {
            System.out.println("Account not found");
            return;
        }
        else if(CheckToWithdraw==2) {
            System.out.println("The entered amount must be less than the current balance");
        }
        else if(CheckToWithdraw==3) {
            System.out.println("Please enter a non-negative money");
        }
        else if(CheckToWithdraw==4) {
            System.out.println("The withdraw was successful");
        }
    }

    private void showAccountDetails(Account account) {
        account = accountService.getAccountDetails(account);
        if(Objects.isNull(account)) {
            System.out.println("Account not found");
            return;
        }
        System.out.println("Your name is:      "+account.getUsername() );
        System.out.println("Your password is:  "+account.getPassword());
        System.out.println("Your balance is :  "+account.getBalance());
        System.out.println("Your age is :      "+account.getAge());
        System.out.println("Your phone number is: "+account.getPhoneNumber());
    }

    private void deposit(Account account) {
        System.out.println("Please enter money you want to deposit");
        double money = 0;
        int counter = 3;
        while (counter>=0) {
            try {
                Scanner sc = new Scanner(System.in);
                money = sc.nextDouble();
                break;
            }
            catch(Exception e){
                System.out.println("Invalid number , please try again");
                counter--;
            }

        }
        if(counter<0) {
            return;
        }
        int depositSuccess = accountService.deposit(account, money);
        if(depositSuccess==1) {
            System.out.println("Account not found");
            return;
        }
        else if(depositSuccess==2) {
            System.out.println("please enter a non-negative money ");
            return;
        }
        else if(depositSuccess==3) {
            System.out.println("Deposit completed successfully");
        }

    }

    /**
     * This function for signup
     * */

    private void signUp() {
        Account account = ValidateAccount();
        if(Objects.isNull(account)) {
            return;
        }
        if(accountService.createAccount(account))
            System.out.println("Account created Successfully");
        else
            System.out.println("Account is already exists");
    }
    private Account ValidateAccount() {

        System.out.println("please enter your name :");
        String name = scanner.next();
        if(!validationService.isValidUserName(name)) {
            System.out.println("Invalid user name");
            System.out.println("Username must be at least 3 characters long and start with uppercase letter ");
            return null;
        }

        System.out.println("please enter your password :");
        String password = scanner.next();
        if(!validationService.isValidPassword(password)) {
            System.out.println("Invalid password");
            System.out.println("Password length must be greater than or equal 6 and contain lowercase , uppercase, special letter and digits ");
            return null;

        }

        System.out.println("please enter your age :");
        int age =0;
        try{
            age = Integer.parseInt(scanner.next());
        }catch (Exception e) {
            System.out.println("you must enter a valid age(numbers)");
            return null;
        }
        if(!validationService.isValidAge(age)) {
            System.out.println("Invalid age");
            return null;
        }
        System.out.println("please enter your phone number :");
        String phoneNumber = scanner.next();
        if(!validationService.isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number");
            return null;
        }
        if(accountService.isPhoneNumberExist(phoneNumber)!=-1)
        {
            System.out.println("Phone number already exist");
            return null;
        }

        return new Account(name, password, age, phoneNumber);
    }
    private Account ValidateLoginAccount() {

        System.out.println("please enter your name :");
        String name = scanner.next();
        if(!validationService.isValidUserName(name)) {
            System.out.println("Invalid user name");
            System.out.println("Username must be at least 3 characters long and start with uppercase letter ");
            return null;
        }

        System.out.println("please enter your password :");
        String password = scanner.next();
        if(!validationService.isValidPassword(password)) {
            System.out.println("Invalid password");
            System.out.println("Password length must be greater than or equal 6 and contain lowercase , uppercase, special letter and digits ");
            return null;

        }

        return new Account(name, password);
    }
}
