package com.finance.layer5;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finance.layer2.OrderTable;
import com.finance.layer2.TransactionTable;
import com.finance.layer4.OrderService;
import com.finance.layer4.TransactionService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TransactionController {
	@Autowired
	TransactionService tranServ;
	@Autowired
	OrderService ordServ;
	
	 	@GetMapping(path="/getTran/{mytranid}") //Get Request in Postman http://localhost:8080/getDept/1
	   	@ResponseBody
	    public TransactionTable getTransactionbyid(@PathVariable("mytranid") Integer tranId) {
	        System.out.println("Transaction Controller....Understanding client and talking to service layer...");
	        TransactionTable tran = tranServ.findTransactionService(tranId);
	        return tran;        
	    }
	 
		@GetMapping(path="/getTrans")
		@ResponseBody
		public Set<TransactionTable> getAllDepartments() {
			System.out.println("Transaction Controller....Understanding client and talking to service layer...");
			Set<TransactionTable> tranList = tranServ.findAllTransactionsService();
			return tranList;
			
		}
		
		@GetMapping(path="/getTransByOrd/{myOrd}")
		@ResponseBody
		public Set<TransactionTable> getTranByOrderId(@PathVariable("myOrd") Integer ord) {
			System.out.println("Transaction Controller....Understanding client and talking to service layer...");
			Set<TransactionTable> tranList = tranServ.findTransactionsByOrderService(ord);
			return tranList;
			
		}
		@PostMapping(path="/addTran") 
        public void addTransaction(@RequestBody TransactionTable tran1) { 
            System.out.println("Transaction Controller....Understanding client and talking to service layer..."); 
            TransactionTable tranT=new TransactionTable();
            tranT.setMonthNo(tran1.getMonthNo()); 
            tranT.setDueAmt(tran1.getDueAmt());
            tranT.setInstallment(tran1.getInstallment());
            tranT.setOrderTable(tran1.getOrderTable());
            tranT.setTransDate(tran1.getTransDate());
            tranServ.addTransactionService(tranT);
            System.out.println("transaction added.....");
        
        
        }
		
		@GetMapping(path="/getTransByReg/{myOrd}")
		@ResponseBody
		public Set<TransactionTable> getTranByRegId(@PathVariable("myOrd") long ord) {
			System.out.println("Transaction Controller....Understanding client and talking to service layer...");
			Set<TransactionTable> tranList = tranServ.findTransactionsByRegIdService(ord);
			return tranList;
			
		}
		
		@GetMapping(path="/getTransByCard/{myCard}")
		@ResponseBody
		public Set<TransactionTable> getTranByCard(@PathVariable("myCard") long cardNo) {
			System.out.println("Transaction Controller....Understanding client and talking to service layer...");
			Set<TransactionTable> tranList = tranServ.findTransactionsByCardService(cardNo);
			return tranList;
			
		}
		
//		@DeleteMapping(path="/deleteDeptById/{mydno}")
//		   @ResponseBody
//		   public String deleteDepartment(@PathVariable("mydno")Integer dno) {
//		       String statusMsg = null;
//				try {
//					statusMsg = deptServ.removeDepartmentService(dno);
//				} catch (DepartmentNotFoundException e) {
//					// TODO Auto-generated catch block
//					return e.getMessage();
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//				}
//		      return statusMsg;
//		   }
		
	}