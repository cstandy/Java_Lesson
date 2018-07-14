public class Operator{
    public static void main( String[] args ){
        int value1 = 3;
        int value2 = 4;
        int result = 0;
        
        result = value1++ * value2--;
        System.out.println("Post increment/decrement: " + result);

        result = ++value1 * --value2;
        System.out.println("Pre  increment/decrement: " + result);
    }
}
