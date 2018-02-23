package heapSort;

public class Node {
	int arr[];
	int sizeOfHeap;
	public Node(int arr[]) {
		this.arr = arr;
		sizeOfHeap = arr.length;
	}

	public int parent(int index) {
		return index / 2;
	}

	public int leftChild(int index) {
		return index * 2;
	}

	public int rightChild(int index) {
		return index * 2 + 1;
	}

	public int heapSize(int i) {
		if(i == 1){
			return sizeOfHeap--;
		}
		return arr.length;
	}

}
