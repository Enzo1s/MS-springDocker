package com.paymentchain.transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.transactions.entities.Transaction;
import com.paymentchain.transactions.repository.TransactionRepository;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepository;
	
	@GetMapping()
	public List<Transaction> list() {
		return transactionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return transactionRepository.findById(id).map(t ->
		ResponseEntity.ok(t)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{reference}")
	public ResponseEntity<?> getReference(@PathVariable String reference) {
		return transactionRepository.findByReference(reference).map(t ->
		ResponseEntity.ok(t)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{accountIban}")
	public List<Transaction> listByAccountIban(@PathVariable String accountIban){
		return transactionRepository.findByAccountIban(accountIban);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Transaction transaction) {
		return transactionRepository.findById(id).map(t ->
		ResponseEntity.ok(transactionRepository.save(transaction))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	public ResponseEntity<?> post(@RequestBody Transaction transaction){
		Transaction save = transactionRepository.save(transaction);
		return ResponseEntity.ok(save);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return null;
		/*transactionRepository.deleteById(id);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);*/
	}
	
}
