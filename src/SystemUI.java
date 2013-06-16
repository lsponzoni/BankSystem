import bank_classes.Bank;


public class SystemUI{
	Bank bank;
	UI current_interface; 
	public SystemUI(Bank b){
		bank = b;
	}
	
	void run_branch(){
		current_interface = new BranchUI(b, string);
	}

	void run_atm(){
		current_interface = new BranchUI(b, string);		
	}
	
	
}
