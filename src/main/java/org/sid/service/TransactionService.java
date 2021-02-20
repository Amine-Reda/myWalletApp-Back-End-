package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.entity.Transaction;
import org.sid.entity.Wallet;
import org.sid.exception.WalletException;
import org.sid.repository.TransactionRepository;
import org.sid.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository ;
	@Autowired
	private WalletRepository walletRepository;
	
	public List<Transaction> getAll(Long walletId){
		Optional<Wallet> wallet= walletRepository.findById(walletId);
		if(wallet.isPresent()) {
			return transactionRepository.findByWallet(wallet.get());	
		}
	return null;	
	}
	
	public Transaction createOrUpdate(Long walletId, Transaction transaction) {
		Optional<Wallet> wallet= walletRepository.findById(walletId);
		if(wallet.isPresent()) {
			transaction.setWallet(wallet.get());
			transactionRepository.save(transaction);
			return transaction;
		}
		return null; 
		
		
	}
	
	public boolean delete(Long id,Long wallet_id) {
		Optional<Wallet> wallet=walletRepository.findById(wallet_id);
		if(wallet.isPresent()) {
		Optional<Transaction> transaction=transactionRepository.findById(id);
		if(transaction.isPresent()) {
			transactionRepository.delete(transaction.get());
			return true;
		}}
		throw new WalletException("Transaction with "+id+" does not exists!");
	}
	
	public Transaction getById(Long wallet_id,Long id) {
		Optional<Wallet> wallet=walletRepository.findById(wallet_id);
		if(wallet.isPresent()) {
		Optional<Transaction> transaction=transactionRepository.findById(id);
		if(transaction.isPresent()) {
			return transaction.get();
		}
		}
		throw new WalletException("Transaction with "+id+"does not exists!");
		
	}
	
	
	


}
