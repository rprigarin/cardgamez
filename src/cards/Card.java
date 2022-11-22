package cards;

public class Card {

	private volatile int value;
	
	public Card(int value) {
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return "Card #" + value;
	}

}
