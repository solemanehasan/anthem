package work;
public class Checkout {
	double toothbrush, salsa, chips, milk, wine, scCombo, total;
	int salsaCount, chipsCount, tbCount;
	
	public Checkout(Scheme scheme) {
		//Grab the members from the scheme object passed in, and use 
		//them to initialize internal variables within this Checkout class
		total = 0;
		toothbrush = Scheme.ToothBrush;
		salsa = Scheme.Salsa;
		chips = Scheme.Chips;
		milk = Scheme.Milk;
		wine = Scheme.Wine;
		scCombo = Scheme.Chips_salsa;
		salsaCount = 0;
		chipsCount = 0;
		tbCount = 0;
	}
	
	public void scan(String input) {

		if(input.equals("1983")) { //Process Toothbrush
			
			if(tbCount >= 2) {
				//Then two toothbrushes have been bought
				//this one should be free of charge
				tbCount = 0; //reset the counter
			}
			else {
				//Issue regular price
				total += toothbrush;
				tbCount ++;
			}
		}
		else if (input.equals("4900")) { //Process Salsa	
			//Check Chips count
			if(chipsCount>0) {
				 //Discounted price for salsa because of bundle
				total += (scCombo - chips);
				chipsCount--;
			}
			else {
				//Regular price for salsa
				total += salsa;
				salsaCount++;
			}
		}
		else if (input.equals("6732")) { //Process Chips
			//Check Salsa count
			if(salsaCount>0) {
				//Discounted price for chips because of bundle
				total += (scCombo - salsa);
				salsaCount--;	
			}
			else {
				//Regular price for chips
				total += chips;
				chipsCount++;
			}
		}
		else if (input.equals("8873")) { //Process Milk
			total += milk;
		}
		else if (input.equals("0923") || input.equals("923")) { //Process Wine (tack on taxes)
			total += wine;
		}
		else {
			System.out.println("Invalid Item Number");
		}
		}
	
	public int getTotal() {
		int cents = (int) Math.round(100*total);
		return cents;	
	}



}
class Scheme{
	static double ToothBrush, Salsa, Chips, Milk, Wine, Chips_salsa;
	
	public Scheme(double toothbrush, double salsa, double chips, double milk, double wine) {
		System.out.println("Item #\tItem\t\t\tPrice");
		System.out.printf("1983\tToothbrush\t\t$%.2f  \n",toothbrush );
		System.out.printf("4900\tSalsa\t\t\t$%.2f  \n",salsa );
		System.out.printf("6732\tChips\t\t\t$%.2f  \n",chips );
		System.out.printf("8873\tCarton of Milk\t\t$%.2f  \n",milk );
		System.out.printf("0923\tWine\t\t\t$%.2f  \n",wine );
		ToothBrush = toothbrush;
		Salsa = salsa;
		Chips = chips;
		Milk = milk;
		Wine = wine + (wine * 0.0925);
		Chips_salsa = 4.99;
		
	}
}
