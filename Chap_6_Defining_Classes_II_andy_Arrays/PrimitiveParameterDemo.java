public class PrimitiveParameterDemo {

	public static void main(String[] args)
	{
		int speed = 50;
		System.out.println("argument value:" + speed);
		changer(speed);
		System.out.println("argument value:" + speed);
	}

	public static void changer(int speed)
	{
		speed = 100;
		System.out.println("parameter value:" + speed);
	}
}
