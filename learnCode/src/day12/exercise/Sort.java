package day12.exercise;

public class Sort {
    public static void selectSort(Sortable[] s){
        for(int i=0;i<s.length-1;i++){
            for(int j=i+1;j<s.length;j++){
                if(s[i].compare(s[j])==1){
                    Sortable temp=s[i];
                    s[i]=s[j];
                    s[j]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Sortable[]sortables=new Sortable[5];
        sortables[0]=new Student(98);
        sortables[1]=new Student(96);
        sortables[2]=new Student(93);
        sortables[3]=new Rectangle(2.3,6);
        sortables[4]=new Rectangle(1,2);
        selectSort(sortables);
        for (Sortable sortable : sortables) {
            System.out.println(sortable.toString());
        }
    }
}
