public class ArrayTest3 {
	
	public static void main(String[] args){
	
		int[][] seat = new int[100][10];
	
		for(int i=0;i<seat.length;i++){
			for(int j=0;j<seat[i].length;j++){
				seat[i][j] = i*j;
			}
		}
	
		System.out.println(seat[5][3]);
	}
}
