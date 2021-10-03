package com.example.clip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.clip.entities.Payment;
import com.example.clip.model.DisbursmentReportDto;
import com.example.clip.model.PayLoadDto;
import com.example.clip.model.TransactionReportDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.service.TransactionService;

/**
 * @author Ivan
 *
 */
@RestController
@RequestMapping("/api/clip/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/createpayload", method = RequestMethod.POST)
	public ResponseEntity<PayLoadDto> create(@RequestBody PaymentRequest paymentRequest) {
		return new ResponseEntity<PayLoadDto>(transactionService.createPayLoad(paymentRequest), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> getAllPayLoad() {
		return new ResponseEntity<List<Payment>>(transactionService.getAllPayLoad(), HttpStatus.OK);

	}

	@RequestMapping(value = "/disbursment", method = RequestMethod.POST)
	public ResponseEntity<List<DisbursmentReportDto>> executeDisbursment() {
		return new ResponseEntity<List<DisbursmentReportDto>>(transactionService.executeDisbursment(), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/Report/{userId}", method = RequestMethod.GET)
	public ResponseEntity<TransactionReportDto> getTransactionReport(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<TransactionReportDto>(transactionService.getTransactionReport(userId), HttpStatus.OK);

	}
}
