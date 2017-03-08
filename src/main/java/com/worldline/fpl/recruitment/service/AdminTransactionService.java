package com.worldline.fpl.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Account;
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
	@Transactional
	public void deleteTransactionById(String accountId, String transactionId) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
					"Account doesn't exist");
		}
		Transaction transaction = transactionRepository.getTransaction(
				transactionId).orElseThrow(
				() -> new ServiceException(ErrorCode.UNEXISTED_TRANSACTION,
						"Transaction doesn't exist"));
		if (!transaction.getAccount().getId().equals(accountId)) {
			throw new ServiceException(ErrorCode.TRANSACTION_NOT_BELONG_ACCOUNT,
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
	@Transactional
	public Transaction createTransaction(String accountId,
			Transaction transaction) {
		Account account = accountService.getAccount(accountId).orElseThrow(
				() -> new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
						"Account doesn't exist"));
		if (transaction == null || transaction.getBalance() == null) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
		transaction.setAccount(account);
		return transactionRepository.createTransaction(transaction);
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
			throw new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
					"Account doesn't exist");
		}
		if (updatedTransaction == null
				|| updatedTransaction.getBalance() == null
				|| updatedTransaction.getNumber() == null) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
		Transaction transaction = transactionRepository.getTransaction(
				transactionId).orElseThrow(
				() -> new ServiceException(ErrorCode.UNEXISTED_TRANSACTION,
						"Transaction Not Found"));
		if (!transaction.getAccount().getId().equals(accountId)) {
			throw new ServiceException(ErrorCode.TRANSACTION_NOT_BELONG_ACCOUNT,
					"Transaction isn't valid");
		}
		updatedTransaction.setId(transactionId);
		updatedTransaction.setAccount(transaction.getAccount());
		transactionRepository.updateTransaction(updatedTransaction);
	}
}
