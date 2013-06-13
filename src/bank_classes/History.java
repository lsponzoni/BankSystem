package bank_classes;
import java.util.*;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : History.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//




public class History {
	List<Withdrawal> withdraw_list;
	List<Deposit> deposit_list;
	List<Transfer> transfer_list;

	public History(){
		withdraw_list = new ArrayList<Withdrawal>(50);
		deposit_list = new ArrayList<Deposit>(50);
		transfer_list = new ArrayList<Transfer>(50);
	}
	private static Money sum_money(Money sum, List<? extends Transaction> transaction_list){
		for(Transaction each: transaction_list){	    	  
			sum = each.get_value().add(sum);
		}
		return sum;
	}
	public Money sum_transaction_values() {
	    Money sum = new Money(0);
	    sum = sum_money(sum,deposit_list);
	    sum = sum_money(sum,transfer_list);
	    sum = sum_money(sum,withdraw_list);  
		return sum;
	}
	
	public void store_transaction(Deposit t) {
		deposit_list.add(t);
	}
	
	public void store_transaction(Withdrawal t) {
		withdraw_list.add(t);
	}
	
	public void store_transaction(Transfer t) {
		transfer_list.add(t);
	}
	
	public Transfer[] get_all_transfers() {
	       return (Transfer[]) transfer_list.toArray();
	}
	
	public Withdrawal[] get_all_withdrawals() {
	       return (Withdrawal[]) withdraw_list.toArray();
	}
	
	public Deposit[] get_all_deposits() {
		   return (Deposit[]) deposit_list.toArray();
	}
	
	public History get_transactions(Date from, Date to) {
		History only_inside_interval_transactions = new History();
		for(Withdrawal each: withdraw_list){
			if(each.in_time_period(from, to)){
	    		  only_inside_interval_transactions.store_transaction(each);  
	    	  }
	      }
	      for(Deposit each : deposit_list){
	    	  if(each.in_time_period(from, to)){
	    		  only_inside_interval_transactions.store_transaction(each);  
	    	  }
	      }
	      for(Transfer each : transfer_list){
	    	  if(each.in_time_period(from, to)){
	    		  only_inside_interval_transactions.store_transaction(each);  
	    	  }
	      }
		return only_inside_interval_transactions;
	}
}
