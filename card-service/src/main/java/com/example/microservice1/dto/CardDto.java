package com.example.microservice1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @JsonProperty("product_id")
    private UUID productId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("grace_period")
    private String gracePeriod;
    @JsonProperty("credit_limit")
    private String creditLimit;
    @JsonProperty("first_year_fee")
    private String firstYearFee;
    @JsonProperty("second_year_fee")
    private String secondYearFee;
    @JsonProperty("min_age")
    private String minAge;
    @JsonProperty("max_age")
    private String maxAge;
    @JsonProperty("citizenship")
    private String citizenship;
    @JsonProperty("registration")
    private String registration;
    @JsonProperty("work_experience")
    private String workExperience;
    @JsonProperty("maximum")
    private String maximum;
    @JsonProperty("repayment_method")
    private String repaymentMethod;
    @JsonProperty("max_monthly_cashback")
    private String maxMonthlyCashback;
    @JsonProperty("ndfl_2_3_4")
    private String ndfl_2_3_4;
    @JsonProperty("bank_certificate")
    private String bankCertificate;
    @JsonProperty("other_documents")
    private String otherDocuments;
    @JsonProperty("pension_certificate")
    private String pensionCertificate;
    @JsonProperty("free_form_certificate")
    private String freeFormCertificate;
    @JsonProperty("additional_income_proof")
    private String additionalIncomeProof;
    @JsonProperty("payment_system")
    private String paymentSystem;
    @JsonProperty("cashback")
    private String cashback;
    @JsonProperty("cmtc_in")
    private String cmtcIn;
    @JsonProperty("cmtc_out")
    private String cmtcOut;
    @JsonProperty("cmtc_b_in")
    private String cmtcBIn;
    @JsonProperty("cmtc_b_out")
    private String cmtcBOut;
    @JsonProperty("mmtc_in")
    private String mmtcIn;
    @JsonProperty("mmtc_out")
    private String mmtcOut;
    @JsonProperty("mmtc_b_in")
    private String mmtcBIn;
    @JsonProperty("mmtc_b_out")
    private String mmtcBOut;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("reviews_count")
    private Double reviewsCount;
    @JsonProperty("rating")
    private Double rating;
}