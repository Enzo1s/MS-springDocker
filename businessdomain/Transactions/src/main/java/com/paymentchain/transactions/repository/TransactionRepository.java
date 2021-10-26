package com.paymentchain.transactions.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.transactions.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	public List<Transaction> findByAccountIban(String accountIban);
	
	public Optional<Transaction> findByReference(String reference);
}
