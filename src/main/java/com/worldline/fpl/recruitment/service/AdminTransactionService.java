package com.worldline.fpl.recruitment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worldline.fpl.recruitment.dao.AccountRepository;
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
	public AdminTransactionService(TransactionRepository transactionRepository,
			AccountService accountService) {
		this.transactionRepository = transactionRepository;
		this.accountService = accountService;
	}

	/**
	 * delete transactions by id
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @param p
	 *            the pageable object
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteTransactionById(String accountId, Pageable p,
			String transactionId) {

		if (transactionId != null) {
			transactionRepository.deleteTransactionById(accountId, p,
					transactionId);
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
		if (transaction == null) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
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
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"Account doesn't exist");
		}
		if (updatedTransaction == null
				|| updatedTransaction.getBalance() == null
				|| updatedTransaction.getNumber() == null) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
					"Transaction isn't valid");
		}
		Optional<Transaction> transaction = transactionRepository
				.getTransaction(transactionId);
		updatedTransaction.setId(transactionId);
		transactionRepository.updateTransaction(updatedTransaction);
	}

}
