package day13.review;

public enum Payment implements Payable{
    ALlPAY{
        public void pay(){
            System.out.println("扫码支付");
        }
    },
    WECHAT{
        public void pay(){
            System.out.println("扫码支付");
        }
    },
    CREDIT_CARD{
        public void pay(){
            System.out.println("输入卡号");
        }
    },
    DEPOSIT_CARD{
        public void pay(){
            System.out.println("输入卡号");
        }
    };


}
