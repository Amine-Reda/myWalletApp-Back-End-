package org.sid.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("deprecation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Name can't be blank")
	@Size(min = 2, max = 30)
	private String name;
	@Size(max = 30)
	private String accountNumber;
	@Size(max = 100)
	private String description;
	@Min(1)
	@Max(3)
	private Integer priority;
	private Double currentBalance;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "wallet", orphanRemoval = true)
	@JsonIgnore
	private List<Transaction> transactions = new ArrayList<>();

	@PrePersist
	public void setBalance() {
		this.currentBalance = new Double(0);
	}

}
