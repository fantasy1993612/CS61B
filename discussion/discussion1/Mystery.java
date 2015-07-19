package discussion1;

public class Mystery {
	/*find the index of the samllest number from the kth number*/
	public static int mystery(int[] arrayList , int k){
		int start = arrayList[k];
		int answer = k;
		int index = k + 1;
		while(index < arrayList.length){
			if(start > arrayList[index]){
				start = arrayList[index];
				answer = index;
			}
			index = index + 1;
		}
		return answer;
	}
	/*sort the arryList by desc*/
	public static void mystery2(int[] arrayList){
		int index = 0;
		while(index < arrayList.length){
			int targetIndex = mystery(arrayList,index);
			int temp =  arrayList[targetIndex];
		    arrayList[targetIndex] = arrayList[index];
		    arrayList[index] = temp;
		    index = index + 1;
		}
	}
	
	public static void main(String[] args){
		int[] arrayList = {3,0,1,6};
		int answer = mystery(arrayList,2);
		System.out.println("The index of the smallest number is:"+answer);
		mystery2(arrayList);
		for (int i = 0 ; i < arrayList.length ; i++){
			System.out.println(arrayList[i]);
		}
	}
}
