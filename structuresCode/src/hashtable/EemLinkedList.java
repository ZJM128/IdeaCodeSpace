package hashtable;

public class EemLinkedList {
    Emp head;// 默认为空

    /**
     * 添加操作
     * @param emp
     */
    public void add(Emp emp){
        if(head==null){
            head=emp;
            return ;
        }

        Emp temp=head;
        while(true){
            if(temp.getNext()==null){
                break;
            }
            temp=temp.getNext();
        }

        temp.setNext(emp);
    }

    /**
     * 遍历雇员数组
     * @param no
     */
    public void list(int no){
        if(head==null){
            System.out.println("当前链表"+(no+1)+"为空");
            return;
        }
        Emp temp =head;
        while(true){
            System.out.println("当前链表"+(no+1)+":"+temp.toString());
            if(temp.getNext()==null){
                break;
            }
            temp=temp.getNext();
        }
    }

    /**
     * 查找一个雇员
     * @param id
     * @return
     */
    public Emp findEmp(int id){
        if(head==null){
            System.out.println("当前hashtable为空");
            return null;
        }
        Emp temp=head;
        while (true){
            if(temp==null){
                break;
            }
            if(temp.getId()==id){
                break;
            }
            temp=temp.getNext();
        }
        return temp;
    }
    public boolean delete(int id){
        if(head==null){
            System.out.println("当前的hashTable为空");
            return false;
        }
        Emp emp=head;
        while(true){
            if(emp.getNext()==null){
                break;
            }
            if(emp.getNext().getId()==id){
                break;
            }
            emp=emp.getNext();
        }
        if(emp.getNext()==null){
            System.out.println("hashTable没有该id");
            return false;
        }else{
            emp.setNext(emp.getNext().getNext());
            return true;
        }
    }
}
