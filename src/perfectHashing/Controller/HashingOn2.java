package perfectHashing.Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import perfectHashing.Model.UniversalHashFunction;

public class HashingOn2 {
	static ArrayList<Long> hashTable[];
	public UniversalHashFunction universe;
    public int FailCount;
	public void hash(String arr[]) throws IOException {
		universe = new UniversalHashFunction();
		long M = arr.length * arr.length;
		hashTable = new ArrayList[(int) M];
		long max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (Long.parseLong(arr[i]) > max) {
				max = Long.parseLong(arr[i]);
			}
		}
		long u = (long) (Math.log(max) / Math.log(2)) + 1;
		long b = (long) (Math.log(M) / Math.log(2));

		while (true) {
			int i = 0;
			long index = 0;
			universe.pickHashFunction(b, u);
			for (i = 0; i < arr.length; i++) {
				index = universe.h(Integer.parseInt(arr[i]));

				if (hashTable[(int) index] != null && hashTable[(int) index].size() > 0) {
					for (int j = 0; j < hashTable[(int) index].size(); j++) {
					}
					System.out.println("fail");
					
					FailCount++;
					hashTable = new ArrayList[(int) M];

					break;
				}
				hashTable[(int) index] = new ArrayList<>();

				hashTable[(int) index].add(Long.parseLong(arr[i]));
			}

			if (i == arr.length) {
				File fout = new File("test.txt");
				FileOutputStream fos = new FileOutputStream(fout.getName());
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				for (int j = 0; j < hashTable.length; j++) {

					if (hashTable[j] != null) {
						bw.write(j + " ");
						for (int j2 = 0; j2 < hashTable[j].size(); j2++) {
							bw.write("===>");
							bw.write(hashTable[j].get(j2) + "   ");
						}
						bw.newLine();
					}
				}
				bw.flush();
				bw.close();
				break;
			}
		}
	}
	public boolean find(int key){
		int x=(int)universe.h(key);
		if (hashTable[x]==null){
			return false;
		}else if(hashTable[x].get(0)==key){
			return true;
		}else{
			return false;
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