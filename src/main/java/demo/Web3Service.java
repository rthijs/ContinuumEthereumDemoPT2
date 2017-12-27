package demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;

@Component
public class Web3Service {

    @Autowired
    private Web3j web3j;

    public List<Account> getAccounts() throws InterruptedException, ExecutionException {

        List<Account> accounts = new ArrayList<Account>();

        for (String accountAddress : web3j.ethAccounts().sendAsync().get().getAccounts()) {
            accounts.add(
                    new Account.AccountBuilder()
                        .address(accountAddress)
                        .balance(getBalance(accountAddress)).build());
        }
        return accounts;
    }

    private BigInteger getBalance(String address) throws InterruptedException, ExecutionException {
        return web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get().getBalance();
    }
    
}

