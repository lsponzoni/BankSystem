package bank_classes;

public class Money {
	private int one_hundred_ammount;
	
	public Money(int x){
		one_hundred_ammount = x;  
	}

	public boolean is_negative(){
		return one_hundred_ammount < 0;
	}
	public Money(double x){
		one_hundred_ammount =(int) (x * 100);  
	}

	public Money(String initial_balance) {
		MoneyPair parsed = new MoneyPair(initial_balance);
		int hundreds = (parsed.get_dollars() * 100);
		int cents = parsed.get_cents();
		one_hundred_ammount = hundreds + cents; 
	}

	public String toString(){
		MoneyPair money= new MoneyPair(one_hundred_ammount);
		return money.toString();
	}

	public Money add(Money m) {
		int sum = this.one_hundred_ammount + m.one_hundred_ammount;
		return new Money(sum);
	}
	
	public Money minus(){
		return new Money(-one_hundred_ammount);
	}
}

