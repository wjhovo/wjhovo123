package cn.jxust.secondhandmarket.exception;

public class NoImageException extends Exception {
    public NoImageException() {
        super("图片数据为空");
    }
}
