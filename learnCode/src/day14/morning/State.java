package day14.morning;

public enum State implements Person{
    PLAYING{
        public void say() {
            System.out.println("播放");
        }
    },
    PREPAREING{
        public void say() {
            System.out.println("开始");
        }
    },
    SUSPEND{
        public void say() {
            System.out.println("暂停");
        }
    },
    STOP{
        public void say() {
            System.out.println("停止");
        }
    };

    /*@Override
    public void say() {

    }*/
}
