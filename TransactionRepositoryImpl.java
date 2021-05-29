package com.finance.layer3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finance.layer2.ProductTable;
import com.finance.layer2.TransactionTable;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository  {
	
	@PersistenceContext
	 EntityManager entityManager;
	
	@Transactional
	public void addTransaction(TransactionTable tRef) {
		entityManager.persist(tRef);
	}

	@Transactional
	public TransactionTable findTransaction(long transno) {
		System.out.println("Department repo....NO scope of bussiness logic here...");
		return entityManager.find(TransactionTable.class,transno);
	}

	@Transactional
	public Set<TransactionTable> findAllTransactions() {
		// TODO Auto-generated method stub
		 List<TransactionTable> list = new ArrayList<TransactionTable>();
	        TypedQuery<TransactionTable> query = entityManager.createNamedQuery("TransactionTable.findAll", TransactionTable.class);
	        list = query.getResultList();
	        Set<TransactionTable> TransactionSet = new HashSet<TransactionTable>(list);
	        return TransactionSet;
	
	}

	@Transactional
	public void modifyTransaction(TransactionTable tRef) {
		entityManager.merge(tRef);
		
	}

	@Transactional
	public void removeTransaction(long transno) {
		TransactionTable transT = entityManager.find(TransactionTable.class,transno);
		entityManager.remove(transT);
		
	}

	@Transactional
	public Set<TransactionTable> findTransactionsByOrder(long ordId){
		Set<TransactionTable> tranSet;//=new HashSet();
		Query query = entityManager.createQuery("from TransactionTable e where ord_id =:myord",TransactionTable.class).setParameter("myord", ordId);
		tranSet = new HashSet(query.getResultList());
		return tranSet;
	}
	
	@Transactional
	public Set<TransactionTable> findTransactionsByRegId(long regId){
		Set<TransactionTable> tranSet;//=new HashSet();
		Query query = entityManager.createQuery("from TransactionTable e where ord_id in(select ordId from OrderTable where card_no=(select cardNo from CardTable\r\n"
				+ "				   where approval_no=(select approvalNo from ApprovalTable where reg_id=:myord)))",TransactionTable.class).setParameter("myord", regId);
		tranSet = new HashSet(query.getResultList());
		return tranSet;
		
	
	}
	

	@Transactional
	public Set<TransactionTable> findTransactionsByCard(long cardNo){
		Set<TransactionTable> tranSet;//=new HashSet();
		Query query = entityManager.createQuery("from TransactionTable e where ord_id in(select ordId from OrderTable where card_no=:myord)",TransactionTable.class).setParameter("myord", cardNo);
		tranSet = new HashSet(query.getResultList());
		return tranSet;
		
	
	}

}
