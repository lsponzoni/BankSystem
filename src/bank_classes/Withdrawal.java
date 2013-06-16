package bank_classes;

import java.util.Calendar;
import bankexceptions.InvalidTransaction;

public class Withdrawal extends Transaction {
	
	public Withdrawal(String this_acc_code, String location_access_code, Money value) throws InvalidTransaction{
		super(this_acc_code,location_access_code, value);
		if(value.is_negative()){
			throw new InvalidTransaction("Negative Withdraw");
		}
	}

	public Withdrawal(String this_acc_code, String location_access_code, Money value, Calendar date) throws InvalidTransaction{
		super(this_acc_code,location_access_code, date,  value);
		if(value.is_negative()){
			throw new InvalidTransaction("Negative Withdraw");
		}

	}

	public Withdrawal(Account acc, Branch location_access, Money value, Calendar date) throws InvalidTransaction{
		super(acc.get_account_code(),location_access.get_code(), date, value);
		if(value.is_negative()){
			throw new InvalidTransaction("Negative Withdraw");
		}

	}

	public Withdrawal(Account acc, Branch location_access, Money value) throws InvalidTransaction{
		super(acc.get_account_code(),location_access.get_code(), value);
		if(value.is_negative()){
			throw new InvalidTransaction("Negative Withdraw");
		}
	}

	
	public Money get_value() {
        return super.get_value().minus();
	}
	
	public String toString(){
		return "Withdrawal:" + super.toString() + "\n "; 
	}
}
