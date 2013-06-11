package bank_classes;

public class MoneyPair {
		private int cents, units; 
		
		public MoneyPair(int value){
			cents = (value % 100);
			units = (value - cents);	
		}

		public MoneyPair(int units, int cents){
			this.cents = cents;
			this.units = units/100;	
		}

		public int get_cents(){
			return cents;
		}
		
		public int get_dollars(){
			return units;
		}
	}	


