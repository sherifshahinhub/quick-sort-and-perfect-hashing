package perfectHashing.Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import perfectHashing.Model.UniversalHashFunction;

public class HashingOn {
	public ArrayList<Integer> hashTable[];
	UniversalHashFunction universe;
	HashingOn2 HashFunctions[];
	int n;
	public double counter = 0;
	public int fails;
	public void hashPhaseOne(String arr[]) throws IOException {
		 universe = new UniversalHashFunction();
		n = arr.length;
		hashTable = new ArrayList[n];
		long max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (Long.parseLong(arr[i]) > max) {
				max = Long.parseLong(arr[i]);
			}
		}
		int u = (int) ((Math.log(max) / Math.log(2)) + 1);
		int b = (int) (Math.log(n) / Math.log(2));

		while (true) {
			int i = 0;
			long index = 0;
			universe.pickHashFunction(b, u);
			for (i = 0; i < arr.length; i++) {
				index = universe.h(Integer.parseInt(arr[i]));
				if (hashTable[(int) index] == null) {
					hashTable[(int) index] = new ArrayList<>();
				}
				hashTable[(int) index].add(Integer.parseInt(arr[i]));
			}

			if (i == arr.length) {
				break;
			}
		}

	}

	public void hashPhaseTwo(ArrayList<Integer>[] arr) throws IOException {
		counter=0;
		fails=0;
		HashFunctions= new HashingOn2[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				 HashFunctions[i]=null;
				continue;

			}
			String[] newArr = new String[arr[i].size()];
			for (int j = 0; j < arr[i].size(); j++) {
				newArr[j] = arr[i].get(j) + "";
			}
			HashingOn2 h = new HashingOn2();
			if (newArr.length == 1) {
			 HashFunctions[i]=null;
				counter++;
				continue;

			}
			h.hash(newArr);
			counter += newArr.length*newArr.length;
			HashFunctions[i] = h;
		}
		for (int i = 0; i < HashFunctions.length; i++) {
			if(HashFunctions[i]!=null){
				 fails += HashFunctions[i].FailCount;
			}
		}
	}
	public boolean find(int key){
		int keyOfkey= (int) universe.h(key);
		if (hashTable[keyOfkey]==null){
			return false;
		}else if(hashTable[keyOfkey].size()==1){
			return true;
		}else{
			return HashFunctions[keyOfkey].find(key);
			
		}
		
	}

	public String[] read(File f) throws Exception {
		String line = "";
		String s = "";
		try (BufferedReader reader1 = Files.newBufferedReader(f.toPath())) {

			while ((line = reader1.readLine()) != null) {
				try {
					s = s + line;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException x) {
			x.printStackTrace();
		}
		return s.split(",");

	}

}