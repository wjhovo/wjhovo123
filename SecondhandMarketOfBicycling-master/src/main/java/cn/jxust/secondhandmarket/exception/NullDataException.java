package cn.jxust.secondhandmarket.exception;

public class NullDataException extends Exception {
    public NullDataException() {
        super("所查数据为空");
    }
}
