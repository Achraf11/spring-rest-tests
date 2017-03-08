package com.worldline.fpl.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;

@Service
public class AdminTransactionService {

	private TransactionRepository transactionRepository;
	private AccountService accountService;

	@Autowired
	public AdminTransactionService(AccountService accountService,
			TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
		this.accountService = accountService;
	}

	/**
	 * delete transactions by id
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @return
	 */

	public void deleteTransactionById(String accountId, String transactionId) {

		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"Account doesn't exist");
		}
		Transaction transaction = transactionRepository.getTransactionById(
				accountId, transactionId).orElseThrow(
				() -> new ServiceException(ErrorCode.INVALID_TRANSACTION,
						"Transaction doesn't exist"));
		if (!transaction.getAccountId().equals(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction doesn't exist");
		}
		if (transactionId != null) {
			transactionRepository.deleteTransactionById(transaction);
		}
	}
}
