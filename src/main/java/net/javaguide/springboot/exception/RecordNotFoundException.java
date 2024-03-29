package net.javaguide.springboot.exception;

public class RecordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String message;

	public RecordNotFoundException() {
	}

	public RecordNotFoundException(String msg) {
		super(msg);
		this.message = msg;
	}

}
