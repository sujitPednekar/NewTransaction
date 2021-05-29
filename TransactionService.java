package com.finance.layer4;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.finance.layer2.TransactionTable;

@Service
public interface TransactionService {
		void addTransactionService(TransactionTable tRef);
		TransactionTable findTransactionService(long transno);  
		Set<TransactionTable> findAllTransactionsService();   
		void modifyTransactionService(TransactionTable tRef); 
		void removeTransactionService(long transno); 
		Set<TransactionTable> findTransactionsByOrderService(long ordId);
		Set<TransactionTable> findTransactionsByRegIdService(long ordId);
		Set<TransactionTable> findTransactionsByCardService(long cardNo);
	}

