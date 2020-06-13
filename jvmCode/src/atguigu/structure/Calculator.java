package atguigu.structure;

public class Calculator {
    public static void main(String[] args) {
        String expression="10+3*9";//28
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operatorStack=new ArrayStack2(10);

        int index=0;
        int num1=0;
        int num2=0;
        int operator=0;
        int res=0;
        char ch=' ';
        String temp="";
        while(true){
            ch=expression.substring(index,index+1).charAt(0);
            if(operatorStack.checkOperator(ch)){
                if(operatorStack.isEmpty()){
                    operatorStack.push(ch);
                }else{
                   if(operatorStack.compareOperator(ch)<=operatorStack.compareOperator(operatorStack.peek())){
                       num1=numStack.pop();
                       num2=numStack.pop();
                       operator=operatorStack.pop();
                       res=operatorStack.count(num1,num2,operator);
                       numStack.push(res);
                       operatorStack.push(ch);
                   }else{
                       operatorStack.push(ch);
                   }
                }
            }else{
                //numStack.push(ch-48);
                temp+=ch;

                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(temp));
                }else if(operatorStack.checkOperator(expression.substring(index+1,index+2).charAt(0))){
                    numStack.push(Integer.parseInt(temp));
                    temp="";
                }
            }

            index++;
            if(index>=expression.length()){
                break;
            }
        }

        while(true){
            if(operatorStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            operator=operatorStack.pop();
            res= operatorStack.count(num1,num2,operator);
            numStack.push(res);

        }
        int result=numStack.pop();
        System.out.println(expression+"="+result);
    }
}
//定栈
class ArrayStack2{
    private int maxSize;
    private int top=-1;
    private int [] arr;
    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 判断是否满了
     * @return
     */
    public boolean isFell(){
        return top==maxSize-1;
    }

    public int peek(){
        return arr[top];
    }
    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        // 先判断是否满了
        if(isFell()){
            System.out.println("栈已满");
            return;
        }
        // 入栈
        top++;
        arr[top]=value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int result=arr[top];
        top--;
        return result;
    }

    /**
     * 遍历栈
     */
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for(int i=top;i>=0;i--) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 判断是否是运算符
     * @param ch
     * @return
     */
    public boolean checkOperator(char ch){
        return ch=='-'||ch=='+'||ch=='*'||ch=='/';
    }

    /**
     * 比较云算符的优先级
     * @param ch
     * @return
     */
    public int compareOperator(int ch){
        if(ch=='-'|| ch=='+'){
           return 0;
        }else if(ch=='*'|| ch=='/'){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * 运算
     * @param num1
     * @param num2
     * @param ch
     * @return
     */
    public int count(int num1,int num2,int ch){
        switch(ch){
            case '-':
                return num2-num1;
            case '+':
                return num1+num2;
            case '*':
                return num1*num2;
            case '/':
                return num2/num1;
            default:
                return 0;
        }

    }

}