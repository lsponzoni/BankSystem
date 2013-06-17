import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bank_classes.Account;
import bank_classes.Bank;
import bank_classes.Branch;
import bank_classes.Clerk;
import bank_classes.Client;
import bank_classes.Money;
import bank_classes.User;
import bankexceptions.DuplicateException;
import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;


public class SystemTest {


	public static Bank populate() throws InvalidTransaction {
		Bank b = new Bank();
		Branch atm1 = new Branch("ATM2009","Caixa Eletrônico 9 Rua Ceará");	
		Branch atm2 = new Branch("ATM3010","Caixa Eletrônico 10 Rua Anchieta");
		Branch atm3 = new Branch("ATM5012","Caixa Eletrônico 12 Shopping Iguatemi");

		Calendar born_at = new GregorianCalendar();
		born_at.set(Calendar.YEAR, 1980);
		born_at.set(Calendar.MONTH, 11); // 11 = december
		born_at.set(Calendar.DAY_OF_MONTH, 24); // christmas eve
		Money dolar = new Money(10000);
		
		Account mario_acc= new Account("193022","FIL2001",dolar);
		Account julia_acc= new Account("194022","FIL3001",dolar);
		Account fefe_acc= new Account("195022","FIL3001",dolar);

		Client mario =  new Client("Mario","Brother",born_at,mario_acc,"It's me Mario","114929993");
		Client julia =  new Client("Julia","Ana",born_at,julia_acc,"I am not mario","114929803");
		Client fefe =  new Client("FE","FE",born_at,fefe_acc,"I am not mario also","1110821529");

		List<User> clients_br1 = new ArrayList<User>();

		clients_br1.add(mario);

		List<User> clients_br2 = new ArrayList<User>();

		clients_br2.add(julia);
		clients_br2.add(fefe);

		List<User> clerks_br1 = new ArrayList<User>();
		Clerk mario2 = new Clerk("Mario","Mario",born_at,"4022","It's me Mario");
		clerks_br1.add(mario2);

		List<User> clerks_br2 = new ArrayList<User>();
		Clerk mario3 = new Clerk("Super","Mario",born_at,"3022","So many marios.");
		clerks_br2.add(mario3);

		Branch br1 = new Branch("FIL2001","Filial Rua Ceará",clients_br1, clerks_br1);
		Branch br2 = new Branch("FIL3001","Filial Rua Anchieta",clients_br2, clerks_br2);


		b.add_atm(atm1);
		b.add_atm(atm2);
		b.add_atm(atm3);
		b.add_branch(br1);
		b.add_branch(br2);

		return b;
	}


	public static void main(String[] args) {
		Bank bank = new Bank();
		try {
		bank = populate();
		}
		catch(Exception e){
			System.out.println("Error at load from data base.");
		}
		SystemUI sistema_bancario = new SystemUI(bank);
		sistema_bancario.start();
	}

}

