package bank_classes;



public class Deposit extends Transaction {
	private String cashParcelCode;

	public Deposit(String account, String gate, Money value, String cashParcelCode) {
		super(account, gate, value);
		this.cashParcelCode = cashParcelCode;
	}
	public static Deposit newInstance(String account_code,String branch_code, Money value, String parcel_code){
		return new Deposit(account_code, branch_code, value, parcel_code);
	}
	
	public static Deposit newInstance(Account account,Branch branch,Money initial_balance, String parcel_code){
		return newInstance(account.get_account_code(),	branch.get_code(), initial_balance, parcel_code);
	}

	public String toString(){
		return "Deposit: " + super.toString() + "\n " + "Parcel: " +cashParcelCode; 
	}
}
