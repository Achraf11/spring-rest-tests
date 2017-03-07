package com.worldline.fpl.recruitment.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.controller.AdminTransactionController;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import com.worldline.fpl.recruitment.service.AdminTransactionService;

/**
 * Implementation of {@link AdminTransactionController}
 * 
 * @author Charaf
 *
 */
@RestController
public class AdminTransactionControllerImpl implements
		AdminTransactionController {

	private AdminTransactionService adminTransactionService;

	@Autowired
	public AdminTransactionControllerImpl(
			AdminTransactionService adminTransactionService) {
		this.adminTransactionService = adminTransactionService;
	}

	@Override
	public ResponseEntity<Page<TransactionResponse>> deleteTransactionsById(
			@PathVariable("accountId") String accountId,
			@PageableDefault Pageable p,
			@PathVariable("transactionId") String transactionId) {
		if (transactionId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		adminTransactionService.deleteTransactionById(accountId, p,
				transactionId);
		return new ResponseEntity<Page<TransactionResponse>>(HttpStatus.OK);
	}

}
