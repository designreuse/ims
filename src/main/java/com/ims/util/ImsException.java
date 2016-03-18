package com.ims.util;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImsException extends Exception {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(ImsException.class);

    private List<ImsError> errorsList;

	private ImsError error;

    public List<ImsError> getErrorsList() {
        return errorsList;
    }

    public void setErrorsList(List<ImsError> errorsList) {
        this.errorsList = errorsList;
    }

    public void addErrors(ImsError error) {
        if (errorsList == null) {
            errorsList = new ArrayList<ImsError>();
        }
        errorsList.add(error);
    }

    public ImsException(String message, ErrorCodes errorCode) {
        this.addErrors(new ImsError(message, errorCode));
		this.error = new ImsError();
		error.setMessage(message);
		error.setErrorCode(errorCode);
    }

	public ImsException(ImsError error) {
        super(error.getMessage());
        this.addErrors(error);
		this.error = error;
    }

	public ImsException(ImsError error, Throwable cause) {
        super(cause);
        this.addErrors(error);
		this.error = error;
		logger.error(error.getMessage(), cause);
    }

    public ImsException(String errorText, ErrorCodes errorCode, Throwable cause) {
        super(cause);
		this.error = new ImsError();
		error.setMessage(errorText);
		error.setErrorCode(errorCode);
        this.addErrors(new ImsError(errorText, errorCode));
    }

    public ImsException(String errorText, ErrorCodes errorCode, Throwable cause, Map<String, String> errorContextMap) {
        super(cause);

        this.addErrors(new ImsError(errorText, errorCode));
    }

	public ImsError getError() {
		return error;
	}

	public void setError(ImsError error) {
		this.error = error;
	}

}