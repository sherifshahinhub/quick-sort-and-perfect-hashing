package heapSort;

public class Max_heap {
	private static int arr[];
	private int l;
	private int r;
	private int largest = 0;
	private Node node;
	public Max_heap(int arr[]) {
		this.arr = arr;
		node = new Node(arr);
	}

	public void MAX_HEAPIFY(int index){
		l = node.leftChild(index);
		r = node.rightChild(index);
		if(l <= node.sizeOfHeap && arr[l - 1] > arr[index - 1]){
			largest = l;
		}else{
			largest = index;
		}
		if(r <= node.sizeOfHeap && arr[r - 1] > arr[largest - 1]){
			largest = r;
		}
		if(largest != index){
			swap(index - 1, largest - 1);
		} else{
			return;
		}
		MAX_HEAPIFY(largest);
		}
	/**
	 * 
	 */
	public void build_max_heap(){
		for (int i = node.sizeOfHeap / 2; i > 0; i--) {
			MAX_HEAPIFY(i);
		}
	}
	public void print(){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}

	public void sort() {
		for (int i = 0; i < node.sizeOfHeap; i++) {
			build_max_heap();
			swap(0, arr.length - 1 - i);
			node.sizeOfHeap--;
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
}
