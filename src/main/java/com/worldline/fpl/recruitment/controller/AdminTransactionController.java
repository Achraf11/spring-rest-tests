package com.worldline.fpl.recruitment.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.json.ErrorResponse;
import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction controller
 * 
 * @author A525125
 *
 */
@RequestMapping(value = "/accounts/{accountId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AdminTransactionController {

	/**
	 * delete transaction by id
	 * 
	 * @param accountId
	 *            the account id
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @param p
	 *            the pageable information
	 * @return the transaction list
	 */
	@RequestMapping(value = "{transactionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete transaction related to an account by id", response = TransactionResponse.class, responseContainer = "transactionDeleted")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 204, message = "No transactions", response = ErrorResponse.class) })
	ResponseEntity<Void> deleteTransactionsById(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Transaction ID") @PathVariable("transactionId") String transactionId);

}
