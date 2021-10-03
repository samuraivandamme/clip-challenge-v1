package com.example.clip.request;

import java.math.BigDecimal;

import lombok.Data;
/**
 * @author Ivan
 *
 */
@Data
public class PaymentRequest {

    Integer userId;
    BigDecimal amount;
}
