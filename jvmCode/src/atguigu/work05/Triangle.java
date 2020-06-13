package atguigu.work05;

public class Triangle {
    double a;
    double b;
    double c;

    boolean isRightTriangle(){
        return (a*a)+(b*b)==c*c;
    }
    boolean isLsoscelesTriangle(){
        if(a==b){
            return true;
        }else if(a==c){
            return true;
        }else if(b==c){
            return true;
        }
        return false;
    }
    boolean isEquilateralTriangle(){
        if(a==b && a==c){
            return true;
        }
        return false;
    }

    public double getArea(){
        double p= (a+b+c)/2;
        double area=p*(p-a)*(p-b)*(p-c);
        return Math.pow(area,2);
    }

    public double getLength(){
        return a+c+b;
    }

}
