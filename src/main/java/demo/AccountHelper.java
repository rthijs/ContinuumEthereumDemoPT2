package demo;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.http.HttpService;

@Component
public class AccountHelper {
    
    private static String WALLET_PASSWORD = "continuum";
    
    private Admin admin = Admin.build(new HttpService());
    
    public Boolean unlockAccount(String address) throws IOException {
        PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(address, WALLET_PASSWORD).send();
        if(personalUnlockAccount.getError() != null) {
            System.out.println(personalUnlockAccount.getError().getMessage());
        }
        //this does not return true or false but true or null
        return personalUnlockAccount.accountUnlocked() != null && personalUnlockAccount.accountUnlocked();
    }

}

