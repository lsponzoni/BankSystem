package bank_classes;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;

public abstract class UI {
	
	private static final String PROMPT = "\n=> ";
	protected boolean logged_in;
	protected User current_user;
	protected Branch access_branch;
	protected Bank facade;
	private Reader input; 
	
	private boolean systemOn;

	public UI(Bank bank, Branch branch) {
		// Bank and branch data addition
		this.access_branch = branch;
		this.facade = bank;
		this.input = new Reader();
		logged_in = false;
		systemOn = false;
	}
	
	private static boolean yes_or_no(String yes_or_no){
		return (yes_or_no.toLowerCase().startsWith("y"));
	}
	
	protected abstract String add_new_account_to_system();

	protected abstract String balance();
	
	protected abstract String call_login(String username, String password);

	public boolean confirm(String question){
		final String Y_OR_N_PREFFIX_WORD_REGEX = "[YyNn].*";
		final String CONFIRM_PREFFIX = "Do you really want to ";
		boolean valid_answer,answer;
		String string_answer = "y";		
		do{
			string_answer = get_string(CONFIRM_PREFFIX + question);
			valid_answer = string_answer.matches(Y_OR_N_PREFFIX_WORD_REGEX);
		}while(!valid_answer);
		answer = yes_or_no(string_answer);
		return answer;
	}

	protected abstract String deposit();
	
	private void disable_operations() {
		//display("\000C");
	}
	
	public void display(String message){
		System.out.println(message);		
	}
	
	public String execute(char optCode){
		// this method is actually a switch
		String message;
		if(MenuOptions.BALANCE.compare(optCode)){
			message = balance();
		} else if(MenuOptions.DEPOSIT.compare(optCode)){
			message = deposit();
		} else if(MenuOptions.EXIT.compare(optCode)){
			message = exit();
		} else if(MenuOptions.HISTORY.compare(optCode)){
			message = transaction_history();
		} else if(MenuOptions.LOGIN.compare(optCode)){
			message = login();
			logged_menu_loop();
		} else if(MenuOptions.LOGOUT.compare(optCode)){
			message = logout();
		} else if(MenuOptions.NEW_ACCOUNT.compare(optCode)){
			message = add_new_account_to_system();
		} else if(MenuOptions.TRANSFER.compare(optCode)){
			message = transfer();
		} else if(MenuOptions.WITHDRAW.compare(optCode)){
			message = withdraw();
		} else {
			message = "Opcao invalida.";
		}
		return message;
	}
	
	protected abstract User exist_at_system(String username,String branch) throws NotFoundException;

	private String exit() {
		systemOn = false;
		return "Exiting " + access_branch.get_code() + "\n" +
				access_branch.get_name();
	}
	
	private Date get_date_from_user(String details){
		Date date = null;
		String date_s;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		while (date == null){
			date_s = get_string(details);
			try{
				date = sdf.parse(date_s);
			}catch(ParseException e)
			{
				display("Entrada Invalida!\n");
				display("Formato yyyy-MM-dd \n");
				date = null;
			}
		}
		return date;
	}
	
	protected abstract MenuOptions[] get_logged_menu_options();

	protected Calendar[] get_month_from_user() {
		Calendar calendar;
		Calendar[] period = new Calendar[2];
		String month;		
		String ask_month = "Informe o mês a ser visualizado:  " +
				"(1-12; ex: Janeiro = 1) \n";	
		month = get_string(ask_month);
		calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Integer.parseInt(month), 1);
		period[0] = calendar;
		calendar.add(Calendar.MONTH, 1);
		period[1] = calendar;
		return period;
	}

	char get_next_operation(MenuOptions[] restricted_to_options){
		String enter;
		String menu;
		char op_value;
		
		do {
			menu = MenuOptions.menu("Escolha uma operacao abaixo", 
					restricted_to_options);
			enter = get_string(menu);
			op_value = enter.toUpperCase().charAt(0);
		}while(! MenuOptions.validOption(op_value,
					      	restricted_to_options));
		
		return op_value;
	}

	protected Calendar[] get_period_from_user() {
		Date date;
		Calendar[] period = new Calendar[2];
		String QUERY_DATE_FORMAT = "(aaaa-mm-dd)";
		String HEADER = "Informe o período a ser visualizado: " + QUERY_DATE_FORMAT; 
		String START_QUERY = "Data de início:" + "\n";
		String FINAL_QUERY = "Data final: \n";
		
		display(HEADER);
		date = get_date_from_user(START_QUERY);
		period[0].setTime(date);
		date = get_date_from_user(FINAL_QUERY);		
		period[1].setTime(date);
		return period;
		
	}
	
	public  String get_string(String question){
		display(question);
		return get_string();
	}

	public  String get_string(){
		display(PROMPT);
		return input.read();
	}

	private MenuOptions[] get_unlogged_menu_options(){
		MenuOptions[] restrictions = {
					MenuOptions.LOGIN,
					MenuOptions.EXIT
					};
		return restrictions;
	}

	public boolean isLoggedIn() {
		return logged_in;
	}

	public void logged_menu_loop(){
		while(isLoggedIn()){
			user_interaction(get_logged_menu_options());
		}
	}
	
	private String login(){
		String username, password;		
		username = get_string("Digite o nome de usuario: \n");
		password = get_string("Digite sua senha: \n");
		return call_login(username, password);
	}
	
	protected String login(String username, String branch, String password) {
		String msg;
		try{
			current_user = exist_at_system(username, branch);
			if(current_user.passwordMatch(password)) {
				logged_in = true;
				msg = "Login realizado.";
			} else {
				msg = "Senha incorreta!";
			}
		}catch(NotFoundException exception){
			msg = "Usuario nao encontrado!";
		}
		return msg;
	}
	
	protected String logout() {
		if(confirm("logout")){
			logged_in = false;
		}
		disable_operations();
		return "Operacao Finalizada";
	}
	
	protected void set_log_in()	{
		logged_in = true;
	}
	
	public void start(){
		String msg = "Bem vindo ao WAND Bank System. \n";
		display(msg);
		unlogged_menu_loop();
	}

	protected abstract String transaction_history();
	
	protected abstract String transfer();
	
	public void unlogged_menu_loop(){
		systemOn = true;
		
		while(systemOn ){
			user_interaction(get_unlogged_menu_options());
		}
		input.close(); 
	}

	private void user_interaction(MenuOptions[] restrict_to_options){
		char operation_id = get_next_operation(restrict_to_options);
		String execution_result = execute(operation_id);
		display(execution_result);
	}

	protected abstract String withdraw(); 	
}