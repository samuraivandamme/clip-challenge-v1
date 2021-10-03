package com.example.clip.model;

import java.math.BigDecimal;

import com.example.clip.entities.User;

import lombok.Data;
/**
 * @author Ivan
 *
 */
@Data
public class DisbursmentReportDto {
	
	private User userId;
	private BigDecimal amount;
	private BigDecimal payment;

}
