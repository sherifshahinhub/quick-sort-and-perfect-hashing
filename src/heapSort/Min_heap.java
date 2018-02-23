package heapSort;

public class Min_heap {
	private static int arr[];
	private int l;
	private int r;
	private int smallest = 0;
	private Node node;
	public Min_heap(int arr[]) {
		this.arr = arr;
		node = new Node(arr);
	}

	public void MIN_HEAPIFY(int index){
		l = node.leftChild(index);
		r = node.rightChild(index);
		if(l <= node.sizeOfHeap && arr[l - 1] < arr[index - 1]){
			smallest = l;
		}else{
			smallest = index;
		}
		if(r <= node.sizeOfHeap && arr[r - 1] < arr[smallest - 1]){
			smallest = r;
		}
		if(smallest != index){
			swap(index - 1, smallest - 1);
		} else{
			return;
		}
		MIN_HEAPIFY(smallest);
		}
	/**
	 * 
	 */
	public void build_min_heap(){
		for (int i = node.sizeOfHeap / 2; i > 0; i--) {
			MIN_HEAPIFY(i);
		}
	}
	
		
	
	
	public static void swap(int i, int j){
		int a = arr[i];
		arr[i] = arr[j];
		arr[j] = a;
	}
	public int getMax(){
		return arr[0];
	}
	
	
	public void sort() {
		for (int i = 0; i < node.sizeOfHeap; i++) {
			build_min_heap();
			swap(0, arr.length - 1 - i);
			node.sizeOfHeap--;
		}
	}
	public void print(){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
}
