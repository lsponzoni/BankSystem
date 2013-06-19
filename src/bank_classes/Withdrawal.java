package bank_classes;


public class Withdrawal extends Transaction {
	
	public Withdrawal(String this_acc_code, String location_access_code, Money value){
		super(this_acc_code,location_access_code, value);
	}

	public static Withdrawal newInstance(Account account, Branch branch, Money value){
		return new Withdrawal(account.get_account_code(), branch.get_code(), value);
	}
	
	public Money get_value() {
        return super.get_value().minus();
	}
	
	public String toString(){
		return "Withdrawal:" + super.toString() + "\n "; 
	}
}
