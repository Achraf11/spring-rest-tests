package com.worldline.fpl.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;

@Service
public class AdminTransactionService {

	private TransactionRepository transactionRepository;

	@Autowired
	public AdminTransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
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

	public void deleteTransactionById(String accountId, Pageable p,
			String transactionId) {

		if (transactionId != null) {
			transactionRepository.deleteTransactionById(accountId, p,
					transactionId);
		}
	}

}
