package com.worldline.fpl.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
		Transaction transaction = transactionRepository.getTransaction(
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

	/**
	 * create transaction
	 * 
	 * @param accountId
	 *            the account id
	 * @param transaction
	 *            the Transaction object
	 * @return
	 */
	@Transactional(readOnly = false)
	public Transaction createTransaction(String accountId,
			Transaction transaction) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"Account doesn't exist");
		}
		if (transaction == null || transaction.getBalance() == null) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
		return transactionRepository.createTransaction(accountId, transaction);
	}

	/**
	 * @param accountId
	 *            the account id
	 * @param transactionId
	 *            the transaction id
	 * @param updatedTransaction
	 *            the updated transaction
	 */
	@Transactional
	public void updateTransaction(String accountId, String transactionId,
			Transaction updatedTransaction) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"Account doesn't exist");
		}
		if (updatedTransaction == null
				|| updatedTransaction.getBalance() == null
				|| updatedTransaction.getNumber() == null) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
		Transaction transaction = transactionRepository.getTransaction(
				accountId, transactionId).orElseThrow(
				() -> new ServiceException(ErrorCode.INVALID_TRANSACTION,
						"Transaction Not Found"));
		if (!transaction.getAccountId().equals(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
		transactionRepository.updateTransaction(transactionId,
				updatedTransaction);
	}
}
