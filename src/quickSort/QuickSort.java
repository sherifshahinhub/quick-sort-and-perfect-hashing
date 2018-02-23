package quickSort;

public class QuickSort {
	int array [];
	
	public static void main(String[] args) {
		QuickSort sorter = new QuickSort();
        int[] input = {3,1,4,2,5,8,9,5,7};
        sorter.algo(input);
        for(int i:input){
            System.out.print(i);

        }
	}
	public void algo(int array[]){
		this.array = array;
		sort(0, array.length - 1);
	}
	public void sort(int first ,int last){
		int i = first;
		int j = last;
		int pivote = array[first + ((last - first) / 2)];
		while(i <= j){
			while(array[i] < pivote){
				i++;
			}
			while(array[j] > pivote){
				j--;
			}
			if(i <= j){
				swap(i,j);
				i++;
				j--;
			}
		}
		if(j > first){
			sort(first , j);
		}
		if(i < last){
			sort(i, last);
		}
		
	}
	public void swap (int i, int j){
		int x = array[i];
		array[i] = array[j];
		array[j] = x;
	}

}
