package com.worldline.fpl.recruitment.exception;

import lombok.Getter;
import lombok.Setter;

import com.worldline.fpl.recruitment.json.ErrorCode;

/**
 * Service exception
 * 
 * @author A525125
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 8303256484065597037L;

	@Getter
	@Setter
	private ErrorCode errorCode;

	@Getter
	@Setter
	private String message;

	public ServiceException(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public ServiceException(ErrorCode errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
}
