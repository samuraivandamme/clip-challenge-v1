package com.example.clip.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.clip.entities.Payment;
import com.example.clip.entities.User;
import com.example.clip.model.DisbursmentReportDto;
import com.example.clip.model.PayLoadDto;
import com.example.clip.model.TransactionReportDto;
import com.example.clip.model.enums.StatusEnum;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UserRepository;
import com.example.clip.request.PaymentRequest;

/**
 * @author Ivan
 *
 */
@Service
public class TransactionService extends Operation{

	@Autowired
	UserRepository userRepository;

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ModelMapper mapper;

	public PayLoadDto createPayLoad(@RequestBody PaymentRequest paymentRequest) {
		User userEntity = userRepository.findById(paymentRequest.getUserId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("User %d not found", paymentRequest.getUserId())));

		Payment paymentEntity = new Payment();
		paymentEntity.setAmount(paymentRequest.getAmount());
		paymentEntity.setStatus(StatusEnum.NEW);
		paymentEntity.setUser(userEntity);
		return mapper.map(paymentRepository.save(paymentEntity), PayLoadDto.class);
	}
	
	public List<Payment> getAllPayLoad(){
		return paymentRepository.findAll();
	}

	public List<DisbursmentReportDto> executeDisbursment() {

		List<Payment> paymentsWithNew = paymentRepository.findByStatus(StatusEnum.NEW);
		List<DisbursmentReportDto> response = new ArrayList<>();
		paymentsWithNew.forEach(p -> {
			DisbursmentReportDto current = new DisbursmentReportDto();
			current.setAmount(BigDecimal.valueOf(getDiscount(p.getAmount())));
			current.setPayment(p.getAmount());
			current.setUserId(p.getUser());
			p.setStatus(StatusEnum.PROCESSED);
			paymentRepository.save(p);
			response.add(current);
		});
		return response;
	}

	public TransactionReportDto getTransactionReport(Integer userId) {
		User userEntity = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId)));

		TransactionReportDto transactionReportDto = new TransactionReportDto();
		List<Payment> payments = paymentRepository.findByUserId(userId);
		transactionReportDto.setName(userEntity);
		transactionReportDto.setPaymentsSum(payments.size());
		transactionReportDto
				.setNewPaymentsSum(payments.stream().filter(p -> p.getStatus().equals(StatusEnum.NEW)).count());

		transactionReportDto.setNewPaymentsAmount(payments.stream().filter(p -> p.getStatus().equals(StatusEnum.NEW))
				.map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		return transactionReportDto;
	}


}
