package Search;

public class SeqSearch {
    public static void main(String[] args) {
        int []arr={2,3,5,4,9,8};
        int index=seqSearch(arr,3);
        System.out.println("索引为:"+index);
    }

    public static int seqSearch(int[]arr,int value){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
