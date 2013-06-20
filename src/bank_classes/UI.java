package bank_classes;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;

public abstract class UI {
	private static final String NOME_USUARIO_DEPOSITO = "Digite o nome do usuario a receber o deposito: \n";
	private static final String DIGITE_SUA_SENHA = "Digite sua senha: \n";
	private static final String DIGITE_O_NOME_DE_USUARIO = "Digite o nome de usuario: \n";
	private static final String DIGITE_A_QUANTIA_A_SER_TRANSFERIDA = "Digite a quantia a ser transferida: \n";
	private static final String DIGITE_O_CODIGO_DA_AGENCIA_DESTINO = "Digite o codigo da agencia destino: \n";
	private static final String DIGITE_O_CODIGO_DA_CONTA_DESTINO = "Digite o codigo da conta destino: \n";
	private static final String DIGITE_O_CLIENTE_DE_ENVIO = "Digite o cliente de envio: \n";
	protected static final String OPERACAO_FINALIZADA = "Operacao Finalizada";
	protected static final String INVALID_TRANSACTION = "Transacao invalida.";
	private static final String SELECT_ONE_FROM_BELLOW_LIST = "Escolha uma operacao abaixo";
	protected static final String USER_NOT_FOUND = "Usuario nao encontrado!";
	protected static final String WRONG_PASSWORD = "Senha incorreta!";
	protected static final String LOGIN_OK = "Login realizado.";
	protected static final String PROMPT = "\n=> ";
	private static final String CLEAR_SCREEN = "\000C";
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
	}

	public void display(String message){
		System.out.println(message);		
	}
	
	public  String get_string(String question){
		display(question);
		display(PROMPT);
		return input.read();
	}

	private static boolean yes_or_no(String yes_or_no){
		return (yes_or_no.toLowerCase().startsWith("y"));
	}

	public boolean confirm(String question){
		final String Y_OR_N_PREFFIX_WORD_REGEX = "[YyNn].*";
		final String CONFIRM_PREFFIX = "Do you really want to ";
		boolean valid_answer,answer;
		String input = "y";		
		do{
			input = get_string(CONFIRM_PREFFIX + question);
			valid_answer = input.matches(Y_OR_N_PREFFIX_WORD_REGEX);
		}while(!valid_answer);
		answer = yes_or_no(input);
		return answer;
	}

	protected String logout() {
		if(confirm("logout")){
			logged_in = false;
		}
		disable_operations();
		return OPERACAO_FINALIZADA;
	}

	protected abstract User exist_at_system(String username,String branch) throws NotFoundException;
	
	protected abstract String add_new_account_to_system();
	
	private String login(){
		String username, password;		
		username = get_string(DIGITE_O_NOME_DE_USUARIO);
		password = get_string(DIGITE_SUA_SENHA);
		return call_login(username, password);
	}
	
	protected abstract String call_login(String username, String password);
	
	protected String login(String username, String branch, String password) {
		String msg;
		try{
			current_user = exist_at_system(username, branch);
			if(current_user.passwordMatch(password)) {
				logged_in = true;
				msg = LOGIN_OK;
			} else {
				msg = WRONG_PASSWORD;
			}
		}catch(NotFoundException exception){
			msg = USER_NOT_FOUND;
		}
		return msg;
	}

	private void disable_operations() {
		display(CLEAR_SCREEN);
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

	public String deposit() {
		String ammount;
		String cashParcelId;
		String user;
		Client client;

		user = get_string(NOME_USUARIO_DEPOSITO);
		try{
			client = this.facade.get_client(user, this.access_branch.get_code());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}

		String value = "Digite a quantia a ser depositada: \n";
		ammount = get_string(value);
		String get_parcel = "Digite o codigo do envelope: \n";
		display(get_parcel);
		cashParcelId = get_string(PROMPT);
		try{
			this.facade.deposit(ammount, cashParcelId, access_branch, client.get_account());
		}
		catch(InvalidTransaction e){
			return INVALID_TRANSACTION;
		}
		return OPERACAO_FINALIZADA;
	}

	public String withdraw() {
		String ammount;
		String user;
		Client client;
		user = get_string("Digite o nome do usuario a fazer o saque: \n");
		try{
			client = this.facade.get_client(user, this.access_branch.get_code());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}
		ammount = get_string("Digite a quantia a ser retirada: \n");
		return this.facade.withdraw(ammount, access_branch, client.get_account());
	}

	public void unlogged_menu_loop(){
		systemOn = true;
		
		while(systemOn ){
			user_interaction(get_unlogged_menu_options());
		}
		input.close(); 
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

	private String exit() {
		systemOn = false;
		return "Exiting " + access_branch.toString();
	}

	protected abstract MenuOptions[] get_logged_menu_options();
	
	public void logged_menu_loop(){
		while(isLoggedIn()){
			user_interaction(get_logged_menu_options());
		}
	}
	
	private void user_interaction(MenuOptions[] restrict_to_options){
		char operation_id = get_next_operation(restrict_to_options);
		String execution_result = execute(operation_id);
		display(execution_result);
	}
	
	public String transfer() {
		String ammount;
		String user;
		Client client;
		String to_account_id;
		String to_branch_id;
		
		user = get_string(DIGITE_O_CLIENTE_DE_ENVIO);
		try{
			client = this.facade.get_client(user, this.access_branch.get_code());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}
		
		to_account_id = get_string(DIGITE_O_CODIGO_DA_CONTA_DESTINO);
		to_branch_id = get_string(DIGITE_O_CODIGO_DA_AGENCIA_DESTINO);
		ammount = get_string(DIGITE_A_QUANTIA_A_SER_TRANSFERIDA);
		try{
			this.facade.transfer(ammount, to_account_id, to_branch_id, access_branch, client.get_account());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}
		catch(InvalidTransaction e){
			return INVALID_TRANSACTION;
		}
		return OPERACAO_FINALIZADA;
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
	
	protected void set_log_in()	{
		logged_in = true;
	}

	private Calendar[] get_period_from_user() {
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

	private Calendar[] get_month_from_user() {
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

	private String transaction_history() {
		String user;
		Client client;
		History history;
		String opt;
		Calendar[] period = new Calendar[2];
		display("Digite o nome do usuario: \n");
		user = get_string(PROMPT);
		try{
			client = this.facade.get_client(user, this.access_branch.get_code());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}
		
		history = client.get_account().get_history();
		display(SELECT_ONE_FROM_BELLOW_LIST);
		
		do{
			display("1> Visualizar historico do mes anterior \n" +
				"2> Selecionar periodo a ser visualizado");
			opt = get_string("");
		} while(!opt.equals("1") && !opt.equals("2"));
		
		if(opt.equals("1")) {
			period = get_month_from_user();
		}
		if(opt.equals("2"))	{
			
			period = get_period_from_user();
		}
		
		try{
			return history.get_transactions(period[0], period[1]).toString();
		}
		catch(InvalidTransaction e)
		{
			return INVALID_TRANSACTION;
		}
	
	}

	public void start(){
		String msg = "Bem vindo ao WAND Bank System. \n";
		display(msg);
		unlogged_menu_loop();
	}

	public String balance() {
		String user;
		Client client;
		display("Digite o nome do usuario: \n");
		user = get_string(PROMPT);
		try{
			client = this.facade.get_client(user, this.access_branch.get_code());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}
		Date dateNow = new Date();
		return "Seu saldo $ " + client.get_account().get_balance().toString() +
				"\n Visto em " + dateNow.toString();
	}
}