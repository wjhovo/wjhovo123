package cn.jxust.secondhandmarket.exception;

public class InvalidArgsException extends Exception {
    public InvalidArgsException() {
        super("缺少参数!");
    }
}
