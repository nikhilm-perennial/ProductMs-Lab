package xyz.mynt.wcbootcamp.exceptions;

public class ProductOutOfStockException extends RuntimeException{

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
