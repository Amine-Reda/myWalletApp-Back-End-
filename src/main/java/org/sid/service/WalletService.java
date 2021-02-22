package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.entity.Transaction;
import org.sid.entity.Wallet;
import org.sid.exception.WalletException;import org.sid.repository.TransactionRepository;
import org.sid.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
	@Autowired
	private WalletRepository walletRepository;
	
	
	
	public List<Wallet> getAll(){
		
		return walletRepository.findAllByOrderByPriority();
	}
	
	public Wallet getById(Long id) {
		Optional<Wallet> wallet=walletRepository.findById(id);
		if(wallet.isPresent()) {
			return wallet.get();
		}
		throw new WalletException("wallet with "+id+"does not exists!");
		
	}
	
	public Wallet createOrUpdate( Wallet wallet) {
		if(wallet.getId()==null) {
			walletRepository.save(wallet);
		}
		else {
			walletRepository.save(wallet);
		}
		return wallet;
	}
	
	
	public boolean delete(Long id) {
		Optional<Wallet> wallet=walletRepository.findById(id);
		if(wallet.isPresent()) {
			walletRepository.delete(wallet.get());
			return true;
		}
		throw new WalletException("wallet with "+id+"does not exists!");
	}
	
	

	

	
	
	
}

