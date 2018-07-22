public class ToyClass
{

	private String name;
	private int number;

	public ToyClass(String initialName, int initialNumber)
	{
		name = initialName;
		number = initialNumber;
	}

	public String toString( )
	{
		return (name + " " + number);
	}

	public void set(String newName, int newNumber)
	{
		name = newName;
		number = newNumber;
	}
}
