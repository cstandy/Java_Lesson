public class Paint {
	
	public static void main (String[] args){
		
		System.out.println(Shape.color);

		Shape shape1 = new Rectangle();
		printArea(shape1);

		Shape shape2 = new Circle();
		printArea(shape2);
	}

	public static void printArea(Shape shape){
		System.out.println(shape.area());
	}
}
