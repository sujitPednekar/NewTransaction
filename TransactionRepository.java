package com.finance.layer3;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.finance.layer2.TransactionTable;

@Repository
public interface TransactionRepository {
	void addTransaction(TransactionTable tRef);   //C - add/create
	TransactionTable findTransaction(long transno);     //R - find/reading
	Set<TransactionTable> findAllTransactions();     //R - find all/reading all
	void modifyTransaction(TransactionTable tRef); //U - modify/update
	void removeTransaction(long transno); //D - remove/delete
	Set<TransactionTable> findTransactionsByOrder(long ordId);
    Set<TransactionTable> findTransactionsByRegId(long regId);
    Set<TransactionTable> findTransactionsByCard(long cardNo);
}
