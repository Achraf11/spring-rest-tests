package com.worldline.fpl.recruitment.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Account;
import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Implementation of {@link TransactionRepository}
 * 
 * @author A525125
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Transaction> getTransactionsByAccount(String accountId,
			Pageable p) {
		final StringBuilder vRequete = new StringBuilder();
		vRequete.append(" SELECT account FROM Transaction account ");
		vRequete.append(" WHERE account.id = :accountId");
		final Query vQuery = entityManager.createQuery(vRequete.toString());
		vQuery.setParameter("id", accountId);
		final List<Account> resultats = vQuery.getResultList();
		return (Page<Transaction>) resultats.get(0);
	}

	@Override
	public void deleteTransactionById(Transaction maTransaction) {
		entityManager.remove(maTransaction);
	}

	public Optional<Transaction> getTransaction(String transactionId) {
		return Optional.ofNullable(entityManager.find(Transaction.class,
				transactionId));
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		transaction.setId(newIndex());
		entityManager.persist(transaction);
		return transaction;
	}

	@Override
	public void updateTransaction(Transaction updatedTransaction) {
		entityManager.merge(updatedTransaction);
	}

	private String newIndex() {
		// TODO
		Long newId = Long.parseLong(String.valueOf(entityManager
				.createQuery("").getSingleResult())) + 1;
		return newId.toString();
	}
}
