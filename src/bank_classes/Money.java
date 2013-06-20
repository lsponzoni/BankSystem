package bank_classes;

import bankexceptions.InvalidTransaction;

public class Money {
	private int one_hundred_ammount;
	
	public Money(double x){
		one_hundred_ammount =(int) (x * 100);  
	}

	private Money(int x, int dummy){
		one_hundred_ammount = x;  
	}
	
	public static Money parseString(String initial_balance) throws InvalidTransaction {
		if (initial_balance.contains("-")){
			throw new InvalidTransaction("Negative values unaccepted");
		}
		MoneyPair parsed = new MoneyPair(initial_balance);
		int hundreds = (parsed.get_dollars() * 100);
		int cents = parsed.get_cents();
		return new Money(hundreds + cents, 0); 
	}

	public Money add(Money m) {
		int sum = this.one_hundred_ammount + m.one_hundred_ammount;
		return new Money(sum, 0);
	}

	public boolean is_negative(){
		return one_hundred_ammount < 0;
	}

	public Money minus(){
		return new Money(-one_hundred_ammount, 0);
	}
	
	public String toString(){
		MoneyPair money= new MoneyPair(one_hundred_ammount);
		return money.toString();
	}
}

