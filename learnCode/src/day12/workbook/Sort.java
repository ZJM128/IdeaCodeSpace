package day12.workbook;

public class Sort {
    public static void selectSort(Sortable[]a){
        for(int i=0;i<a.length-1;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i].compare(a[j])==1){
                    Sortable temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }

    }

    public static void main(String[] args) {
        Sortable[]sortables=new Sortable[5];
        sortables[0]=new Student(69);
        sortables[3]=new Student(96);
        sortables[2]=new Student(89);
        sortables[1]=new Rectangle(2,6);
        sortables[4]=new Rectangle(6,1);
        selectSort(sortables);
        for (Sortable sortable : sortables) {
            System.out.println(sortable.toString());
        }
    }
}
