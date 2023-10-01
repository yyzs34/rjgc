import java.util.Scanner;
 
public class maxs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		System.out.println("请输入数组长度");
        int N = sc.nextInt();
        int arr[] = new int[N];
		System.out.println("请输入数组元素，元素间用空格隔开");
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int max = maxSum(arr, 0, N - 1);
        System.out.println("最大子数组和为：" + max);
    }
 
    public static int maxSum(int arr[], int left, int right) {
        int sum = 0;
        if (left == right) {
            
            sum = arr[left] > 0 ? arr[left] : 0;
        } else {
            int center = (left + right) / 2;
            int leftsum = maxSum(arr, left, center);
            int rightsum = maxSum(arr, center + 1, right);
 
          
            int s1 = 0;
            int lefts = 0;

            for (int i = center; i >= left; i--) {
                lefts += arr[i];
         
                if (lefts > s1)
                    s1 = lefts;
            }
            int s2 = 0;
            int rights = 0;
  
            for (int i = center + 1; i <= right; i++) {
                rights += arr[i];
                if (rights > s2)
                    s2 = rights;
            }
    
            sum = s1 + s2;
            if (sum < leftsum)
                sum = leftsum;
            if (sum < rightsum)
                sum = rightsum;
        }
        return sum;
    }
 
}