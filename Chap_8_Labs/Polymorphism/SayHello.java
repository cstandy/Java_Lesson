public class SayHello {

	public String sayHello (String name){ 
		return "Hello! " + name;
	}

	public String sayHello (String name, String gender){
		if (gender.equals("boy")){
			return "Hello! Mr. " + name;
		} else if (gender.equals("girl")){
			return "Hello! Miss. " + name;
		} else {
			return "Hello! " + name;
		}
	}

	public static void main(String[] args){
		SayHello hello = new SayHello();

		System.out.println(hello.sayHello("S.J."));
		// decided at compile time

		System.out.println(hello.sayHello("S.J.", "boy"));
		// decided at compile time
	}
}
