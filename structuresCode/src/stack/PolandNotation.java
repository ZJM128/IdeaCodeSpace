package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 */
public class PolandNotation {

    public static void main(String[] args) {

        String suffixExpression="11+2-(1*2)";
        List<String> list = strToList(suffixExpression);

        System.out.println("总数:"+polandNotationDeal(getPolandNotationNum(list)));

    }

    /**
     *逆波兰计算器
     * @param listStr
     * @return
     */
    public static int polandNotationDeal(List<String> listStr){
        Stack<String> stack=new Stack<>();
        for(String s:listStr){
            if(s.matches("\\d+")){// 如果是数的话就加入
                stack.push(s);
            }else{
                int num1=Integer.parseInt(stack.pop());
                int num2=Integer.parseInt(stack.pop());
                int res=0;
                switch(s){
                    case "-":
                        res=num2-num1;
                        break;
                    case "+":
                        res=num1+num2;
                        break;
                    case "*":
                        res=num1*num2;
                        break;
                    case "/":
                        res=num2/num1;
                        break;
                }
                stack.push(res+"");
            }
        }
        return Integer.parseInt(stack.pop());

    }

    /**
     * 得到中缀list
     * @param str
     * @return
     */
    public static List<String> strToList(String str){
        List<String>list=new ArrayList<>();
        int i=0;
        String joinStr;
        char c;
        do{
            if((c=str.charAt(i))<48 || (c=str.charAt(i))>57){// 证明不是数字直接加入list中
                list.add(c+"");
                i++;
            }else{
                joinStr="";
                while(i<str.length() && (c=str.charAt(i))>=48 && (c=str.charAt(i))<=57){
                    joinStr+=c;
                    i++;
                }
               list.add(joinStr);
            }
        }while(i<str.length());
        return list;
    }

    public static List<String> getPolandNotationNum(List<String>list){

        Stack<String> operatorStack=new Stack();// 用于存储符号
        List<String>listNum=new ArrayList<>();// 用于存储数据

        for(String item:list){
            if(item.matches("\\d+")){// 数字直接压入数字栈
                listNum.add(item);
            } else if("(".equals(item) || operatorStack.size()==0){
                operatorStack.push(item);
            }else if(")".equals(item)){
                while(!"(".equals(operatorStack.peek())){
                    listNum.add(operatorStack.pop());
                }
                operatorStack.pop();
            }else {
                // 当 item的优先级小于等于operatorStack栈顶运算符,讲operatorStack栈顶
                // 的运算符出栈并加入listNum,再次转到不符合条件
                while(operatorStack.size()!=0 && (Operator.compare(item)<=Operator.compare(operatorStack.peek()))){
                    listNum.add(operatorStack.pop());
                }
                operatorStack.push(item);
            }

        }

        while(operatorStack.size()!=0){
            listNum.add(operatorStack.pop());
        }
        return listNum;
    }
}

class Operator{
    private final static int ADD=1;
    private final static int  MIN=1;
    private final static int  MUL=2;
    private final static int  DIV=2;

    public static int compare(String operator){
        int result=0;
        switch(operator){
            case "-":
                result= MIN;
                break;
            case "+":
                result= ADD;
                break;
            case "*":
                result= MUL;
                break;
            case "/":
                result =DIV;
                break;
            default:
                System.out.println("不存在该运算符");

        }
        return result;
    }

}