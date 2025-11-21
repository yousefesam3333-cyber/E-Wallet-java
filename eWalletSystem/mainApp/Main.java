package eWalletSystem.mainApp;

import eWalletSystem.Service.ApplicationService;
import eWalletSystem.Service.impl.ApplicationServicesImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationService applicationServices = new ApplicationServicesImpl();
        applicationServices.StartEWalletSystem();

    }
}
