package heapSort;

public class Main {
	public static void main(String[] args) {
		int a[] = {5,3,2,8,6};
		Min_heap m = new Min_heap(a);
		m.build_min_heap();
		m.getMax();
		m.sort();
//		HeapSort h = new HeapSort(a);
//		h.sort();
		m.print();
	}

}
