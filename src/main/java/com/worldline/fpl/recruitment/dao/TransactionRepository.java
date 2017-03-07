package com.worldline.fpl.recruitment.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);

	void deleteTransactionById(String accountId, Pageable p,
			String transactionId);

	boolean exists(String transactionId);

	Transaction createTransaction(Transaction transaction);
	/**
	 	 *
	 	 * @param updatedTransaction
	 	 *             the updated transaction
	 	 */
	 	void updateTransaction(Transaction updatedTransaction);

	Optional<Transaction> getTransaction(String transactionId);
}
