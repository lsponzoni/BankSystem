package bank_classes;

public enum MenuOptions {
	LOGIN('L', "Login"), 
	LOGOUT('O', "Logout"),
	EXIT('D', "Desligar"),
	
	NEW_ACCOUNT('N',"Nova conta"),

	BALANCE('S', "Saldo"),
	HISTORY('H', "Extrato"),
	TRANSFER('T', "Transferir"),
	DEPOSIT('D', "Depósito"),
	WITHDRAW('W', "Retirada"),

	BRANCH('A', "Menu Agência"),
	ATM('C', "Caixa Eletrônico"),
	EXIT_ALL('E',"Bank Shutdown");

	private final char c;
	private final String  option_name;
	
	MenuOptions(char option_char, String option_name){
		this.c = option_char;
		this.option_name = option_name;
	}
	
	public final char char_id(){
		return c; 
	}

	public final String option_line(){
		return option_name;
	}

	public final boolean compare(char c){
		return this.c == c;
	}
	
	public static String menu_entry_string(MenuOptions t){
		return t.char_id() + "> " + t.option_line() + "\n";
	}
	
	public static boolean validOption(String op, MenuOptions[] o){
		char option_value = op.toLowerCase().charAt(0);
		for(MenuOptions t: o){
			if(option_value == t.char_id()){
				return true;
			}
		}
		return false;
	}

	public static boolean validOption(char option_value, MenuOptions[] restrict){
		for(MenuOptions t: restrict){
			if(option_value == t.char_id()){
				return true;
			}
		}
		return false;
	}

	public static String menu(String header,MenuOptions[] restrict){
		StringBuilder s = new StringBuilder(header);
		for(MenuOptions t: restrict){
			s.append(menu_entry_string(t));
		}	
		return s.toString();
	}
}
