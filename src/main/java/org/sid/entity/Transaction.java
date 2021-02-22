package org.sid.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Min(1)
	@NotNull(message = "amount can't be null")
	private Double amount;
	private String description;
	@Min(1)
	@Max(3)
	private int type;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date transactionDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wallet_id",nullable = false)
	@JsonIgnore
	private Wallet wallet;
	
	@PrePersist
	public void setTransactionDate() {
		this.transactionDate=new Date();
	}

}
