package perfectHashing.Model;
import java.util.Random;

public class UniversalHashFunction {
	public  long[][] hash;
	public  long u;
	public  long b;
	public int index;
	public static void main(String[] args) {
//		while(true){
//		pickHashFunction(19,9 );
//		System.out.println(h(100));
//		}
		
	}

	// int b=(int) (Math.log(M)/Math.log(2));
	public  void pickHashFunction(long b, long u) {
		hash = new long[ (int) b][(int) u];
		this.b = b;
		this.u = u;
		for (int i = 0; i < hash.length; i++) {
			for (int j = 0; j < hash[i].length; j++) {
				Random r = new Random();
				hash[i][j]=r.nextInt(2);
			}
			
		}

	}

	public  long h(int x) {
		String s = format(Long.toBinaryString(x), u);
		long arr[][] = new long[(int) u][1];
		for (int i = 0; i < s.length(); i++) {
			arr[i][0] = s.charAt(i)-48;
		}
		int num = Multiply(hash, arr);
		return num;

	}

	public static String format(String s, long u) {
		String newStr = "";
		for (int i = 0; i < u - s.length(); i++) {
			newStr = newStr + "0";
		}
		newStr = newStr + s;
		return newStr;
	}

	public  int Multiply(long[][] hash, long[][] arr) {
		/*
		 * Create another 2d array to store the result using the original
		 * arrays' lengths on row and column respectively.
		 */
		long[][] result = new long[(int) b][1];

		/* Loop through each and get product, then sum up and store the value */
		for (int i = 0; i < hash.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				for (int k = 0; k < hash[0].length; k++) {
					result[i][j] += hash[i][k] * arr[k][j];
				}
			}

		}
		String n ="";
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j]=result[i][j]%2;
				n+=result[i][j]+"";
			}
		}
	int number= Integer.parseInt(n, 2);	
		return number;

	}
}