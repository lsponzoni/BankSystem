import java.io.InputStreamReader;
import java.util.Scanner;

import bank_classes.AtmUI;
import bank_classes.Bank;
import bank_classes.Branch;
import bank_classes.BranchUI;
import bank_classes.MenuOptions;
import bank_classes.UI;
import bankexceptions.NotFoundException;


public class SystemUI{
	private static final String CLEAR_SCREEN = "\000C";
	private static final String PROMPT = "\n=> ";
	private static final String SELECT_ONE_FROM_BELLOW_LIST = "Escolha uma opção abaixo";
	private static MenuOptions[] system_level_options = { MenuOptions.ATM,MenuOptions.EXIT_ALL,MenuOptions.BRANCH };

	Bank bank;
	UI current_interface;
	boolean use;

	public SystemUI(Bank b){
		bank = b;
		use = true;
	}

	public void display(String message){
		System.out.println(message);		
	}

	public  String get_string(String question){
		InputStreamReader cin = new InputStreamReader(System.in);
		Scanner in = new Scanner(cin);
		display(question);
		display(PROMPT);
		String input = in.nextLine();
		in.close();
		return input;
	}

	private void atm(){
		try {
			String code = get_string(bank.list_all_atms()+ "\n Código do caixa requisitado:");
			Branch branch = bank.get_atm(code);
			set_atm(branch);
			current_interface.unlogged_menu_loop();
		} catch (NotFoundException e){
			display(e.toString());
		}
	}
	private void branch(){
		try {	
			String code = get_string(bank.list_branches_with_data()+ "\n Código da agência requisitada:");
			Branch branch = bank.get_branch(code);
			set_branch(branch);
			current_interface.unlogged_menu_loop();
		} catch (NotFoundException e){
			display(e.toString());
		}	
	}
	
	private String execute(char optCode){
		if (MenuOptions.ATM.compare(optCode)){
			atm();
		} else if(MenuOptions.BRANCH.compare(optCode)){
			branch();
		} else if(MenuOptions.EXIT_ALL.compare(optCode)){
			exit();
		}
		return ""; 
	}
	
	private void exit(){
		use = false;
	}
	
	char get_next_operation(MenuOptions[] restricted_to_options){
		String input;
		char op_value;

		do {
			String menu = MenuOptions.menu(SELECT_ONE_FROM_BELLOW_LIST, restricted_to_options);
			input = get_string(menu);
			op_value = input.toUpperCase().charAt(0);
		}while(! MenuOptions.validOption(op_value,restricted_to_options));

		return op_value;
	}

	public void menu_loop() {
		while(use){
			user_interaction(system_level_options);
		}
	}

	void set_atm(Branch b){
		current_interface = new AtmUI(bank, b);		
	}
	void set_branch(Branch b){
		current_interface = new BranchUI(bank, b);
	}

	public void start() {
		menu_loop();
	}

	private void user_interaction(MenuOptions[] restrict_to_options){
		char operation_id = get_next_operation(restrict_to_options);
		String execution_result = execute(operation_id);
		display(execution_result);
	}



}
