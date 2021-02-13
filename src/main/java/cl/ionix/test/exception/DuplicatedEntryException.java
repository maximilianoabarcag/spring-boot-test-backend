package cl.ionix.test.exception;

public class DuplicatedEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedEntryException(String message) {
		super(message);
	}
}
