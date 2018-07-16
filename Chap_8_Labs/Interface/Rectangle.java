public class Rectangle implements Shape {
	
	int x1 = 0;
	int y1 = 0;
	int x2 = 10;
	int y2 = 10;

	public double area(){
		return (x2 - x1) * (y2 - y1);
	}
}
