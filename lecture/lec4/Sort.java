package lec4;

public class Sort {
	/**由大到小排序*/
	public static void sort(String[] a){
		sort(a,0);
	}
	
	/**从指定位置开始排序*/
	private static void sort(String[] a,int start){
		if (start == a.length)
			return;
		int mindex = indexOfSmallest(a,start);//找最小下标
		swap(a,start,mindex);//把最小下标交换到最前面
		sort(a,start+1);//递归排序
	}
	
	/**返回从指定位置开始(start)字符串数组
	 * 的最小一个字符串的下标*/
	private static int indexOfSmallest(String[] a,int start){
		int minDex = start;
	    int i = start;
		while(i < a.length){
			int compareResult = a[i].compareTo(a[minDex]);
			if(compareResult < 0){
				minDex = i;
			}
			i = i + 1;
		}
		return minDex;
	}
	
	private static void swap(String a[],int start,int mindex){
		String temp = a[start];
		a[start] = a[mindex];
		a[mindex] = temp;
	}
	
	public static void print(String[] a){
		for (int i = 0 ; i < a.length ; ++i){
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args){
		String[] a = {"a","huge","tiny","scorpion"};
		sort(a);
		print(a);	
	}
}
