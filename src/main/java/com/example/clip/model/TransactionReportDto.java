package com.example.clip.model;

import java.math.BigDecimal;

import com.example.clip.entities.User;

import lombok.Data;
/**
 * @author Ivan
 *
 */
@Data
public class TransactionReportDto {
	
	private User name;
	private int paymentsSum;
	private Long newPaymentsSum;
	private BigDecimal newPaymentsAmount;

}
