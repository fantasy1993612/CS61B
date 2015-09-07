import java.util.Arrays;

public class Plotting{


	public void FunctionVal(){
		int N = 1000;
		StdDraw.setXscale(0,N);
		StdDraw.setYscale(0,N*N);
		StdDraw.setPenRadius(.008);
		for(int i = 1 ; i <= N ;i++ ){
			StdDraw.point(i,i);
			StdDraw.point(i,i*i);
			StdDraw.point(i,i*Math.log(i));
		}
	}

	public void ArrayOfRandomVal(){
		int N = 100;
		double[] a = new double[N];
		StdDraw.setPenColor(StdDraw.RED); 
		for(int i = 0; i < N;i++){
			a[i] = StdRandom.random();
		}
		Arrays.sort(a);
		for(int i = 0;i < N;i++){
			double x = 1.0*i/N;
			double y = a[i]/2.0;
			double rw = 0.3/N;
			double rh = a[i]/2.0;
			StdDraw.filledRectangle(x,y,rw,rh);
		}
	}
	public static void main(String[] args){
		Plotting p = new Plotting();
		//p.FunctionVal();
		p.ArrayOfRandomVal();
	}
} 