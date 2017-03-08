package com.worldline.fpl.recruitment.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Implementation of {@link TransactionRepository}
 * 
 * @author A525125
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository,
		InitializingBean {

	private List<Transaction> transactions;

	@Override
	public void afterPropertiesSet() throws Exception {
		transactions = new ArrayList<>();
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(42.12));
			transaction.setId("1");
			transaction.setNumber("12151885120");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(456.00));
			transaction.setId("2");
			transaction.setNumber("12151885121");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(-12.12));
			transaction.setId("3");
			transaction.setNumber("12151885122");
			transactions.add(transaction);
		}
	}

	@Override
	public Page<Transaction> getTransactionsByAccount(String accountId,
			Pageable p) {
		return new PageImpl<Transaction>(transactions.stream()
				.filter(t -> t.getAccountId().equals(accountId))
				.collect(Collectors.toList()));
	}

	@Override
	public void deleteTransactionById(Transaction maTransaction) {
		transactions.remove(maTransaction);
	}

	public Optional<Transaction> getTransaction(String accountId,
			String transactionId) {
		return transactions.stream()
				.filter(t -> t.getId().equals(transactionId)).findFirst();
	}

	@Override
	public boolean exists(String transactionId) {
		return transactions.stream().anyMatch(
				a -> a.getId().equals(transactionId));
	}

	@Override
	public Transaction createTransaction(String accountId,
			Transaction transaction) {
		transaction.setId(newIndex());
		transaction.setAccountId(accountId);
		transactions.add(transaction);
		return transaction;
	}

	@Override
	public void updateTransaction(String transactionId,
			Transaction updatedTransaction) {
		transactions.stream().filter(s -> s.getId().equals(transactionId))
				.findFirst().ifPresent(s -> {
					s.setBalance(updatedTransaction.getBalance());
				});
	}

	private String newIndex() {
		int index = transactions.size() + 1;
		return String.valueOf(index);
	}
}
