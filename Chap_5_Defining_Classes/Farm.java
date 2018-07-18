public class Farm {
	public static void main(String[] args) {
		Duck duck = new Duck(true);

		boolean canTheDuckFly = duck.getCanfly();
	
		if (canTheDuckFly == true) {
			System.out.println("The duck can fly.");
		}
	
		duck.quack();
		duck.quack("Ga Ga Ga");

		String food = "Hamburger";
		String message = duck.eat(food);
		System.out.println(message);

		int expectedDistance = 10;
		duck.swim(expectedDistance);
		System.out.println("The expected distance is " + expectedDistance);
	}
}
