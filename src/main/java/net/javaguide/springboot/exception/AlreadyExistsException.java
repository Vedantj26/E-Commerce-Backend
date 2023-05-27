package net.javaguide.springboot.exception;

public class AlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String message;

	public AlreadyExistsException() {
	}

	public AlreadyExistsException(String msg) {
		super(msg);
		this.message = msg;
	}
}
