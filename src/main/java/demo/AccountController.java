package demo;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    Web3Service web3service;

    @RequestMapping("/accounts")
    public List<Account> listAccounts() throws InterruptedException, ExecutionException {
        return web3service.getAccounts();
    }

}
