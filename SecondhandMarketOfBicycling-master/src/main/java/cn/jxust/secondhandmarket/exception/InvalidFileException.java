package cn.jxust.secondhandmarket.exception;

public class InvalidFileException extends Exception {
    public InvalidFileException() {
        super("上传文件格式非法!");
    }
}
