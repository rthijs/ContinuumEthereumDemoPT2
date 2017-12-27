package demo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;

@Component
public class TransactionHelper {

    @Autowired
    private Web3j web3j;
    
    @Autowired
    private AccountHelper accountHelper;

    public TransactionReceipt sendEtherTransaction(EtherTransactionRequest transactionRequest)
            throws IOException, InterruptedException, TransactionException, ExecutionException {

        if (accountHelper.unlockAccount(transactionRequest.getFromAddress())) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3j,transactionRequest.getFromAddress());
            Transfer transfer = new Transfer(web3j,transactionManager);
            return transfer.sendFunds(transactionRequest.getToAddress(), BigDecimal.valueOf(transactionRequest.getAmount().longValue()), Unit.WEI).sendAsync().get();
        }
        return null;
    }
}

