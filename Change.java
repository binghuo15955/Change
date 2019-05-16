import java.util.Scanner;

public class Change {
	public static void quickSort(int array[], int left, int right) {//sort by coinvalues
		if (left + 1 > right) {
			return;
		}
		int i = left;
		int j = right - 1;
		int pivot = array[right];
		while (i <= j) {
			while (i <= j && array[i] <= pivot) {
				i++;
			}
			while (i <= j && array[j] >= pivot) {
				j--;
			}
			if (i != j) {
				if (i > j) {
					int temp = array[i];
					array[i] = array[right];
					array[right] = temp;
				} else {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		quickSort(array, left, i - 1);
		quickSort(array, i, right);
	}

	public static int charge(int[] coinsValues, int n) {
		int[][] c = new int[coinsValues.length + 1][n + 1];
		
		for (int i = 0; i <= coinsValues.length; i++)
			c[i][0] = 0;
		for (int i = 0; i <= n; i++)
			c[0][i] = Integer.MAX_VALUE;
		
		for (int j_money = 1; j_money <= n; j_money++) {
			
			for (int i_coinKinds = 1; i_coinKinds <= coinsValues.length; i_coinKinds++) {
				if (j_money < coinsValues[i_coinKinds - 1])//如果要换的钱小于面额，向上一层找要换的钱的对应值，直接赋给原空|如果找不开，就找上一层
				{
					c[i_coinKinds][j_money] = c[i_coinKinds - 1][j_money];
					continue;
				}
				//以下是面额小于要换的钱数量
				//下面可以理解为在判断兑换的money是否为coin面值的倍数,因为从前往后，前面的倍数都记录着
				if (c[i_coinKinds - 1][j_money] < (c[i_coinKinds][j_money - coinsValues[i_coinKinds - 1]] + 1))//如果要换的钱币数量大于上一层的，就用上一层比较少的
					c[i_coinKinds][j_money] = c[i_coinKinds - 1][j_money];
				else//如果换的钱币数量小于上一层，那么用（要换的钱-面额）就是剩下该面值无法找开的钱应该需要的数量
					c[i_coinKinds][j_money] = c[i_coinKinds][j_money - coinsValues[i_coinKinds - 1]] + 1;
			}
		}
		return c[coinsValues.length][n];
	}

	public static void Change1() {
		Scanner in = new Scanner(System.in);
		int K = in.nextInt();
		int[] result = new int[K];
		for (int i = 0; i < K; i++) {
			int N = in.nextInt();// categories
			int S = in.nextInt();// total money which need to change
			if (N == S && N == 0) {// stop
				break;
			}
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = in.nextInt();
			}
			quickSort(arr, 0, arr.length - 1);
			result[i]=charge(arr, S);
		}
		for(int i=0;i<K;i++) {
			System.out.println(result[i]);
		}
	}


public static void main(String[] args) {
		Change1();
	}

}
