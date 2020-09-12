package com.atguigu.banking;

public class OverdraftException extends Exception {
    private static final long serialVersionUID = 7437579892381851311L;
    private double deficit;

    public OverdraftException(String message) {
        super(message);
    }

    public OverdraftException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }

    public double getDeficit() {
        return deficit;
    }

}
