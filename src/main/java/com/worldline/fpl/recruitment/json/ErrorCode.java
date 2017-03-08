package com.worldline.fpl.recruitment.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

/**
 * Error code
 * 
 * @author A525125
 *
 */
@AllArgsConstructor
public enum ErrorCode {

	UNEXISTED_ACCOUNT(HttpStatus.NOT_FOUND), TRANSACTION_NOT_BELONG_ACCOUNT(
			HttpStatus.FORBIDDEN), UNEXISTED_TRANSACTION(HttpStatus.NOT_FOUND), INVALID_TRANSACTION(
			HttpStatus.BAD_REQUEST) ;

	@Getter
	@Setter
	private HttpStatus httpStatus;
}
