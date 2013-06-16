package bank_classes;

public enum MenuOptions {
	LOGIN(1,'L', "Login"), 
	LOGOUT(2,'O', "Logout"),
	EXIT(3,'D', "Desligar"),
	
	NEW_ACCOUNT(4,'N',"Nova conta"),
	
	TRANSFER(5,'T', "Transferir"),
	DEPOSIT(6,'D', "Depósito"),
	WITHDRAW(7,'W', "Retirada"),

	BRANCH(8,'B', "Menu Agência"),
	ATM(9,'C', "Caixa Eletrônico"),
	EXIT_ALL(10,'E',"Bank Shutdown");

	private final char id_letter;
	private final int  id_value;
	private final String  option_name;
	
	MenuOptions(int index,char option_char, String option_name){
		this.id_value = index;
		this.id_letter = option_char;
		this.option_name = option_name;
	}
	
	public int int_id(){
		return id_value;
	}

	public char char_id(){
		return id_letter; 
	}
	
	public String option_line(){
		return option_name;
	}

	public static String menu_entry_string(MenuOptions t){
		return t.char_id() + ":" + t.option_line() + "\n";
	}
	
	public static String menu(MenuOptions[] o){
		StringBuilder s = new StringBuilder("Select");
		for(MenuOptions t: o){
			s.append(menu_entry_string(t));
		}
		return s.toString();
	}
}
