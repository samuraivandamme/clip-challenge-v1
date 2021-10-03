package com.example.clip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clip.entities.Payment;
import com.example.clip.model.enums.StatusEnum;
/**
 * @author Ivan
 *
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	List<Payment> findByStatus(StatusEnum status);
	
	List<Payment> findByUserId(Integer userId);

}
