package org.sid.contoller;

import javax.validation.Valid;

import org.sid.entity.Transaction;
import org.sid.entity.Wallet;
import org.sid.service.TransactionService;
import org.sid.service.ValidationErrorService;
import org.sid.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ValidationErrorService validationErrorService;

	@GetMapping("/{wallet_id}")
	public ResponseEntity<?> getAll(@PathVariable Long wallet_id){
		return new ResponseEntity<>(transactionService.getAll(wallet_id),HttpStatus.OK);
	}
	
	@PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id,@Valid @RequestBody Transaction transaction,BindingResult result){
	ResponseEntity errors=validationErrorService.validate(result);
			if(errors!=null)return errors;
	Transaction transactionSaved=transactionService.createOrUpdate(wallet_id,transaction);
	return new ResponseEntity<Transaction>(transactionSaved,HttpStatus.CREATED);
}
	@DeleteMapping("/{wallet_id}/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id,@PathVariable Long wallet_id){
		transactionService.delete(id,wallet_id);
		return new ResponseEntity(HttpStatus.OK);
	}
	@PutMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> update(@PathVariable Long wallet_id,@PathVariable Long id,@Valid @RequestBody Transaction transaction,BindingResult result){
	ResponseEntity errors=validationErrorService.validate(result);
			if(errors!=null)return errors;
	transaction.setId(id);
	Transaction transactionSaved=transactionService.createOrUpdate(wallet_id,transaction);
	return new ResponseEntity<Transaction>(transactionSaved,HttpStatus.OK);
}
	@GetMapping("/{wallet_id}/{id}")
	public ResponseEntity<?> getById(@PathVariable Long wallet_id,@PathVariable Long id){
		return new ResponseEntity<>(transactionService.getById(wallet_id, id),HttpStatus.OK);
	}
	
}
