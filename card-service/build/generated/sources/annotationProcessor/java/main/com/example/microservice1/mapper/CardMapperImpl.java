package com.example.microservice1.mapper;

import com.example.microservice1.dto.CardDto;
import com.example.microservice1.entity.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-15T14:45:59+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto cardToCardDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setProductId( card.getProductId() );
        cardDto.setType( card.getType() );
        cardDto.setGracePeriod( card.getGracePeriod() );
        cardDto.setCreditLimit( card.getCreditLimit() );
        cardDto.setFirstYearFee( card.getFirstYearFee() );
        cardDto.setSecondYearFee( card.getSecondYearFee() );
        cardDto.setMinAge( card.getMinAge() );
        cardDto.setMaxAge( card.getMaxAge() );
        cardDto.setCitizenship( card.getCitizenship() );
        cardDto.setRegistration( card.getRegistration() );
        cardDto.setWorkExperience( card.getWorkExperience() );
        cardDto.setMaximum( card.getMaximum() );
        cardDto.setRepaymentMethod( card.getRepaymentMethod() );
        cardDto.setMaxMonthlyCashback( card.getMaxMonthlyCashback() );
        cardDto.setNdfl_2_3_4( card.getNdfl_2_3_4() );
        cardDto.setBankCertificate( card.getBankCertificate() );
        cardDto.setOtherDocuments( card.getOtherDocuments() );
        cardDto.setPensionCertificate( card.getPensionCertificate() );
        cardDto.setFreeFormCertificate( card.getFreeFormCertificate() );
        cardDto.setAdditionalIncomeProof( card.getAdditionalIncomeProof() );
        cardDto.setPaymentSystem( card.getPaymentSystem() );
        cardDto.setCashback( card.getCashback() );
        cardDto.setCmtcIn( card.getCmtcIn() );
        cardDto.setCmtcOut( card.getCmtcOut() );
        cardDto.setCmtcBIn( card.getCmtcBIn() );
        cardDto.setCmtcBOut( card.getCmtcBOut() );
        cardDto.setMmtcIn( card.getMmtcIn() );
        cardDto.setMmtcOut( card.getMmtcOut() );
        cardDto.setMmtcBIn( card.getMmtcBIn() );
        cardDto.setMmtcBOut( card.getMmtcBOut() );
        cardDto.setProductName( card.getProductName() );
        cardDto.setBank( card.getBank() );
        cardDto.setReviewsCount( card.getReviewsCount() );
        cardDto.setRating( card.getRating() );

        return cardDto;
    }

    @Override
    public Card cardDtoToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setProductId( cardDto.getProductId() );
        card.setType( cardDto.getType() );
        card.setGracePeriod( cardDto.getGracePeriod() );
        card.setCreditLimit( cardDto.getCreditLimit() );
        card.setFirstYearFee( cardDto.getFirstYearFee() );
        card.setSecondYearFee( cardDto.getSecondYearFee() );
        card.setMinAge( cardDto.getMinAge() );
        card.setMaxAge( cardDto.getMaxAge() );
        card.setCitizenship( cardDto.getCitizenship() );
        card.setRegistration( cardDto.getRegistration() );
        card.setWorkExperience( cardDto.getWorkExperience() );
        card.setMaximum( cardDto.getMaximum() );
        card.setRepaymentMethod( cardDto.getRepaymentMethod() );
        card.setMaxMonthlyCashback( cardDto.getMaxMonthlyCashback() );
        card.setNdfl_2_3_4( cardDto.getNdfl_2_3_4() );
        card.setBankCertificate( cardDto.getBankCertificate() );
        card.setOtherDocuments( cardDto.getOtherDocuments() );
        card.setPensionCertificate( cardDto.getPensionCertificate() );
        card.setFreeFormCertificate( cardDto.getFreeFormCertificate() );
        card.setAdditionalIncomeProof( cardDto.getAdditionalIncomeProof() );
        card.setPaymentSystem( cardDto.getPaymentSystem() );
        card.setCashback( cardDto.getCashback() );
        card.setCmtcIn( cardDto.getCmtcIn() );
        card.setCmtcOut( cardDto.getCmtcOut() );
        card.setCmtcBIn( cardDto.getCmtcBIn() );
        card.setCmtcBOut( cardDto.getCmtcBOut() );
        card.setMmtcIn( cardDto.getMmtcIn() );
        card.setMmtcOut( cardDto.getMmtcOut() );
        card.setMmtcBIn( cardDto.getMmtcBIn() );
        card.setMmtcBOut( cardDto.getMmtcBOut() );
        card.setProductName( cardDto.getProductName() );
        card.setBank( cardDto.getBank() );
        card.setReviewsCount( cardDto.getReviewsCount() );
        card.setRating( cardDto.getRating() );

        return card;
    }
}
