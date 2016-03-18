package com.ims.util;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Arrays;

public class ImsError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Object errorObject;

	private ErrorCodes errorCode;

	private String errorMessageCode;

	private String[] messageArguments;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getErrorObject() {
		return errorObject;
	}

	public void setErrorObject(Object errorObject) {
		this.errorObject = errorObject;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

	public ImsError() {
		super();
	}

	public ImsError(String message, ErrorCodes errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public ImsError(ErrorCodes code, String errorMessageCode, Object[] messageArguments) {
		this.errorCode = code;
		this.errorMessageCode = errorMessageCode;
		this.messageArguments = Arrays.copyOf(messageArguments, messageArguments.length, String[].class);
	}

	public ImsError(ErrorCodes code, String errorMessageCode) {
		this.errorCode = code;
		this.errorMessageCode = errorMessageCode;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getErrorCode());
		return builder.toHashCode();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (errorCode != null) {
			sb.append("Error Code: " + this.errorCode.toString());
			sb.append("\n");
		}
		sb.append("Status Message: " + this.message);
		return sb.toString();
	}

	public String getErrorMessageCode() {
		return errorMessageCode;
	}

	public void setErrorMessageCode(String errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}

	public String[] getMessageArguments() {
		return messageArguments;
	}

	public void setMessageArguments(String[] messageArguments) {
		this.messageArguments = messageArguments;
	}

}