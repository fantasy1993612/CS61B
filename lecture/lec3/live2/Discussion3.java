
public class Discussion3{


	 static int[] insert(int[] x, int val, int position) {
	 	int len = x.length + 1;
	 	int[] y = new int[len]; 
	 	for (int j = 0 ; j < len-1 ; j++){
	 		y[j] = x[j];
	 	}

		for(int i = x.length-1  ; i >= position ; i--){
			y[i+1] = y[i]; 
		}
		y[position] = val;

		return y;
	}

	public static void main(String[] args) {
		int[] x = {5,9,14,15};
		x = insert(x,6,2);
		for (int i : x){
			System.out.println(i+" ");
		}

	}
}	