package bank_classes;
import java.util.*;
import java.lang.StringBuilder;
import bankexceptions.InvalidTransaction;
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
		withdraw_list = new ArrayList<Withdrawal>(10);
		deposit_list = new ArrayList<Deposit>(10);
		transfer_list = new ArrayList<Transfer>(10);
	}
	private static Money sum_money(Money sum, List<? extends Transaction> transaction_list){
		for(Transaction each: transaction_list){	    	  
			sum = each.get_value().add(sum);
		}
		return sum;
	}
	public Money sum_transaction_values() {
	    Money sum = new Money(0);
	    sum = sum_money(sum, deposit_list);
	    sum = sum_money(sum, transfer_list);
	    sum = sum_money(sum, withdraw_list);  
		return sum;
	}
	
	public void store_transaction(Transaction t) throws InvalidTransaction {
		if(t instanceof Deposit){
			deposit_list.add((Deposit) t);
		} else if(t instanceof Withdrawal) {
			withdraw_list.add((Withdrawal) t);
		} else if(t instanceof Transfer) {
			transfer_list.add((Transfer) t);
		} else{
			throw new InvalidTransaction("History class offers no support this type undefined.");
		}
	}
	


	public History get_transactions(Date from, Date to) throws InvalidTransaction {
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
	
	public String toString(){
		StringBuilder extract = new StringBuilder("\nRecord of deposits:\n");
		for(Deposit deposit : deposit_list){
			extract.append(deposit.toString());
		}
		extract.append("\nRecords of Withdraws:\n");
		for(Withdrawal withdraw : withdraw_list){
			extract.append(withdraw.toString());
		}
		extract.append("\nRecords of Transfers:\n");
		for(Transfer transfer : transfer_list){
			extract.append(transfer.toString());
		}
		return extract.toString();
	}
}
