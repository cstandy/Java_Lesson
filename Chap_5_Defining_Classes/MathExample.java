public class MathExample {
	public static void main(String[] args){
		int i = 7;
		int j = -9;
		double x = 72.3;
		double y = 0.34;
		System.out.println("i is " + i);
		System.out.println("j is " + j);
		System.out.println("x is " + x);
		System.out.println("y is " + y);
		System.out.println("|" + i + "| is " + Math.abs(i));
		System.out.println("|" + j + "| is " + Math.abs(j));
		System.out.println("|" + x + "| is " + Math.abs(x));
		System.out.println("|" + y + "| is " + Math.abs(y));
		System.out.println(x + " is approximately " + Math.round(x));
		System.out.println(y + " is approximately " + Math.round(y));
		System.out.println("The ceiling of " + i + " is " + Math.ceil(i));
		System.out.println("The ceiling of " + j + " is " + Math.ceil(j));
		System.out.println("The ceiling of " + x + " is " + Math.ceil(x));
		System.out.println("The ceiling of " + y + " is " + Math.ceil(y));
		System.out.println("min(" + i + "," + j + ") is " + Math.min(i,j));
		System.out.println("min(" + x + "," + y + ") is " + Math.min(x,y));
		System.out.println("min(" + i + "," + x + ") is " + Math.min(i,x));
		System.out.println("min(" + y + "," + j + ") is " + Math.min(y,j));
		System.out.println("max(" + i + "," + j + ") is " + Math.max(i,j));
		System.out.println("max(" + x + "," + y + ") is " + Math.max(x,y));
		System.out.println("max(" + i + "," + x + ") is " + Math.max(i,x));
		System.out.println("max(" + y + "," + j + ") is " + Math.max(y,j));
		System.out.println("Pi is " + Math.PI);
		System.out.println("e is " + Math.E);
		System.out.println("pow(2.0, 2.0) is " + Math.pow(2.0,2.0));
		System.out.println("pow(10.0, 3.5) is " + Math.pow(10.0,3.5));
		System.out.println("pow(8, -1) is " + Math.pow(8,-1));
		System.out.println("Here's one random number: " + Math.random());
		System.out.println("Here's another random number: " + Math.random());
	}}
