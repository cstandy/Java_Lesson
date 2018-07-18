public class Duck {
	
	private boolean canfly = false;

	public Duck(boolean canfly){
		this.canfly = canfly;
	}

	public boolean getCanfly() {
		return canfly;
	}

	public void quack(){
		System.out.println("Quack!!");
	}

	public void quack(String sound){
		System.out.println(sound);
	}
	
	public String eat(String food){
		String message = "Thank you! The " + food + " is good!";
		return message;
	}

	public void swim(int distance){
		distance = distance - 1;
		System.out.println("The distance of my swimming is " + distance);
	}
}
