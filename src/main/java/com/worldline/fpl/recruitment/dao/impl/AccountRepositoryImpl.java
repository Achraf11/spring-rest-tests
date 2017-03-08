package com.worldline.fpl.recruitment.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.worldline.fpl.recruitment.dao.AccountRepository;
import com.worldline.fpl.recruitment.entity.Account;

/**
 * Implementation of {@link AccountRepository}
 * 
 * @author Charaf
 *
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Account> findAll(Pageable p) {
		// TO DO
		// return entityManager.createQuery(Account.class).getResultList();
		return null;
	}

	@Override
	public Optional<Account> findById(String accountId) {
		return Optional
				.ofNullable(entityManager.find(Account.class, accountId));
	}

	@Override
	public boolean exists(String accountId) {
		return findById(accountId).isPresent();
	}
}
