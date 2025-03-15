package com.example.microservice1.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Table(name = "cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "type")
    private String type;

    @Column(name = "grace_period")
    private String gracePeriod;

    @Column(name = "credit_limit")
    private String creditLimit;

    @Column(name = "first_year_fee")
    private String firstYearFee;

    @Column(name = "second_year_fee")
    private String secondYearFee;

    @Column(name = "min_age")
    private String minAge;

    @Column(name = "max_age")
    private String maxAge;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "registration")
    private String registration;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "maximum")
    private String maximum;

    @Column(name = "repayment_method")
    private String repaymentMethod;

    @Column(name = "max_monthly_cashback")
    private String maxMonthlyCashback;

    @Column(name = "ndfl_2_3_4")
    private String ndfl_2_3_4;

    @Column(name = "bank_certificate")
    private String bankCertificate;

    @Column(name = "other_documents")
    private String otherDocuments;

    @Column(name = "pension_certificate")
    private String pensionCertificate;

    @Column(name = "free_form_certificate")
    private String freeFormCertificate;

    @Column(name = "additional_income_proof")
    private String additionalIncomeProof;

    @Column(name = "payment_system")
    private String paymentSystem;

    @Column(name = "cashback")
    private String cashback;

    @Column(name = "cmtc_in")
    private String cmtcIn;

    @Column(name = "cmtc_out")
    private String cmtcOut;

    @Column(name = "cmtc_b_in")
    private String cmtcBIn;

    @Column(name = "cmtc_b_out")
    private String cmtcBOut;

    @Column(name = "mmtc_in")
    private String mmtcIn;

    @Column(name = "mmtc_out")
    private String mmtcOut;

    @Column(name = "mmtc_b_in")
    private String mmtcBIn;

    @Column(name = "mmtc_b_out")
    private String mmtcBOut;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "bank")
    private String bank;

    @Column(name = "reviews_count")
    private Double reviewsCount;

    @Column(name = "rating")
    private Double rating;
}