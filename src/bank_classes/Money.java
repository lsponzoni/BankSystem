package bank_classes;

public class Money {
	private int one_hundred_money;
	
	public Money(int x){
		one_hundred_money = x;  
	}

	public Money(double x){
		one_hundred_money =(int) x * 100;  
	}
	
	public String toString(){
		MoneyPair money= new MoneyPair(one_hundred_money);
		return money.get_dollars() + "," + money.get_cents();
	}

	public Money add(Money m) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Money minus(){
		return new Money(-one_hundred_money);
	}
}

