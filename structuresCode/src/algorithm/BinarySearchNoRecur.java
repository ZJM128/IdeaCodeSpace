package algorithm;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[]arr={1,2,3,9,10,23};
        System.out.println(binarySearch(arr, 23));
    }


    public static int binarySearch(int[]arr,int target){
        int left=0;
        int right=arr.length-1;
        int middle=0;
        while(left<=right){
            middle=(left+right)/2;
            if(arr[middle]==target){
                return middle;
            }else if(arr[middle]>target){
                right=middle-1;
            }else if(arr[middle]<target){
                left=middle+1;
            }
        }
        return -1;
    }
}
