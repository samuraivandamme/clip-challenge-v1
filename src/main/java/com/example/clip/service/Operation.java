package com.example.clip.service;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class Operation {
	
	static double FEE=0.035;
	
	public double getDiscount(BigDecimal amount) {
		return amount.doubleValue() - (amount.doubleValue() * FEE);
		
	}
	
	public  double getFee() {
		return 0.035;
	}
	

}
