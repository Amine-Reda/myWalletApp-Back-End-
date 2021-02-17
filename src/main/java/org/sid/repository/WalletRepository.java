package org.sid.repository;

import java.util.List;

import org.sid.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	List<Wallet> findAllByOrderByPriority();
}
