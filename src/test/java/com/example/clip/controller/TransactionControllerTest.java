package com.example.clip.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.clip.entities.Payment;
import com.example.clip.model.DisbursmentReportDto;
import com.example.clip.model.PayLoadDto;
import com.example.clip.model.TransactionReportDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Ivan
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@MockBean
	private TransactionService service;

	@Autowired
	private MockMvc mockMvc;

	private static List<Payment> paymentUsers;
	private static PayLoadDto payLoadDto;
	private static PaymentRequest paymentRequest;
	private static List<DisbursmentReportDto> disbursmentReportDto;
	private static TransactionReportDto transactionReportDto;

	private static final String PAYMENTS_ENDPOINT = "/api/clip/transaction/users";
	private static final String DISBURSEMENT_ENDPOINT = "/api/clip/transaction/disbursment";
	private static final String PAYMENT_ENDPOINT = "/api/clip/transaction/createpayload";
	private static final String REPORT_ENDPOINT = "/api/clip/transaction/Report/";

	private static final Integer USER_ID = 1;

	@BeforeEach
	public void init() throws IOException {
		paymentUsers = new ObjectMapper().readValue(
				Paths.get("src/test/resources/controller/PaymentsUsers.json").toFile(),
				new TypeReference<List<Payment>>() {
				});
		disbursmentReportDto = new ObjectMapper().readValue(
				Paths.get("src/test/resources/controller/disbursment.json").toFile(),
				new TypeReference<List<DisbursmentReportDto>>() {
				});

		payLoadDto = new ObjectMapper().readValue(Paths.get("src/test/resources/controller/payment.json").toFile(),
				new TypeReference<PayLoadDto>() {
				});
		paymentRequest = new ObjectMapper().readValue(
				Paths.get("src/test/resources/controller/paymentRequest.json").toFile(),
				new TypeReference<PaymentRequest>() {
				});
		transactionReportDto = new ObjectMapper().readValue(
				Paths.get("src/test/resources/controller/transactionReport.json").toFile(),
				new TypeReference<TransactionReportDto>() {
				});
	}

	@Test
	public void getTransactionReportTest() throws Exception {
		doReturn(transactionReportDto).when(service).getTransactionReport(USER_ID);
		this.mockMvc.perform(get(REPORT_ENDPOINT.concat(USER_ID.toString())).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void createPayLoadTest() throws JsonProcessingException, Exception {

		doReturn(payLoadDto).when(service).createPayLoad(paymentRequest);
		this.mockMvc
				.perform(post(PAYMENT_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(payLoadDto)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void getUsersWithPaymentsTest() throws Exception {
		doReturn(paymentUsers).when(service).getAllPayLoad();
		this.mockMvc.perform(get(PAYMENTS_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	@WithMockUser(username = "admin", password = "clip")
	public void processDisbursementAutenticatedTest() throws Exception {
		doReturn(disbursmentReportDto).when(service).executeDisbursment();
		this.mockMvc.perform(post(DISBURSEMENT_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	public void executeDisbursmentFailTest() throws Exception {
		doReturn(disbursmentReportDto).when(service).executeDisbursment();
		this.mockMvc.perform(post(DISBURSEMENT_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isUnauthorized());

	}

}
