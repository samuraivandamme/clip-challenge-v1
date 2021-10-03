package com.example.clip.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.clip.model.enums.StatusEnum;

import lombok.Data;
/**
 * @author Ivan
 *
 */
@Entity
@Table(name = "payment")
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "amount")
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private StatusEnum status;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}
