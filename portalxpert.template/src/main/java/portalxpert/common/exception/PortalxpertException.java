package portalxpert.common.exception;

public class PortalxpertException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PortalxpertException(String message){
		super(message);
	}
	public PortalxpertException(String message, Throwable t){
		super(message, t);
	}
}
