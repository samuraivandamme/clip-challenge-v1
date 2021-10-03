package com.example.clip.model;

import java.math.BigDecimal;

import com.example.clip.entities.User;
import com.example.clip.model.enums.StatusEnum;

import lombok.Data;

/**
 * @author Ivan
 *
 */
@Data
public class PayLoadDto {
	private Integer id;
	private BigDecimal amount;
	private StatusEnum status;
	private User user;
}



	
