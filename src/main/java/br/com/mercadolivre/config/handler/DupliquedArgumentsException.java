package br.com.mercadolivre.config.handler;

public class DupliquedArgumentsException extends RuntimeException {

    private int code;
    private String message;
    private String element;

    public DupliquedArgumentsException(int code, String message, String element) {
        this.code = code;
        this.message = message;
        this.element = element;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getElement() {
        return element;
    }
}
