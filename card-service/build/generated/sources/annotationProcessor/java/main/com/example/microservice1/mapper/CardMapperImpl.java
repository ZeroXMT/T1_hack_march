package com.example.microservice1.mapper;

import com.example.microservice1.dto.CardDto;
import com.example.microservice1.entity.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-15T10:53:49+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto cardToCardDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        if ( card.getProductId() != null ) {
            cardDto.setProductId( card.getProductId() );
        }
        if ( card.getType() != null ) {
            cardDto.setType( card.getType() );
        }
        if ( card.getGracePeriod() != null ) {
            cardDto.setGracePeriod( card.getGracePeriod() );
        }
        if ( card.getCreditLimit() != null ) {
            cardDto.setCreditLimit( card.getCreditLimit() );
        }
        if ( card.getFirstYearFee() != null ) {
            cardDto.setFirstYearFee( card.getFirstYearFee() );
        }
        if ( card.getSecondYearFee() != null ) {
            cardDto.setSecondYearFee( card.getSecondYearFee() );
        }
        if ( card.getMinAge() != null ) {
            cardDto.setMinAge( card.getMinAge() );
        }
        if ( card.getMaxAge() != null ) {
            cardDto.setMaxAge( card.getMaxAge() );
        }
        if ( card.getCitizenship() != null ) {
            cardDto.setCitizenship( card.getCitizenship() );
        }
        if ( card.getRegistration() != null ) {
            cardDto.setRegistration( card.getRegistration() );
        }
        if ( card.getWorkExperience() != null ) {
            cardDto.setWorkExperience( card.getWorkExperience() );
        }
        if ( card.getMaximum() != null ) {
            cardDto.setMaximum( card.getMaximum() );
        }
        if ( card.getRepaymentMethod() != null ) {
            cardDto.setRepaymentMethod( card.getRepaymentMethod() );
        }
        if ( card.getMaxMonthlyCashback() != null ) {
            cardDto.setMaxMonthlyCashback( card.getMaxMonthlyCashback() );
        }
        if ( card.getNdfl_2_3_4() != null ) {
            cardDto.setNdfl_2_3_4( card.getNdfl_2_3_4() );
        }
        if ( card.getBankCertificate() != null ) {
            cardDto.setBankCertificate( card.getBankCertificate() );
        }
        if ( card.getOtherDocuments() != null ) {
            cardDto.setOtherDocuments( card.getOtherDocuments() );
        }
        if ( card.getPensionCertificate() != null ) {
            cardDto.setPensionCertificate( card.getPensionCertificate() );
        }
        if ( card.getFreeFormCertificate() != null ) {
            cardDto.setFreeFormCertificate( card.getFreeFormCertificate() );
        }
        if ( card.getAdditionalIncomeProof() != null ) {
            cardDto.setAdditionalIncomeProof( card.getAdditionalIncomeProof() );
        }
        if ( card.getPaymentSystem() != null ) {
            cardDto.setPaymentSystem( card.getPaymentSystem() );
        }
        if ( card.getCashback() != null ) {
            cardDto.setCashback( card.getCashback() );
        }
        if ( card.getCmtcIn() != null ) {
            cardDto.setCmtcIn( card.getCmtcIn() );
        }
        if ( card.getCmtcOut() != null ) {
            cardDto.setCmtcOut( card.getCmtcOut() );
        }
        if ( card.getCmtcBIn() != null ) {
            cardDto.setCmtcBIn( card.getCmtcBIn() );
        }
        if ( card.getCmtcBOut() != null ) {
            cardDto.setCmtcBOut( card.getCmtcBOut() );
        }
        if ( card.getMmtcIn() != null ) {
            cardDto.setMmtcIn( card.getMmtcIn() );
        }
        if ( card.getMmtcOut() != null ) {
            cardDto.setMmtcOut( card.getMmtcOut() );
        }
        if ( card.getMmtcBIn() != null ) {
            cardDto.setMmtcBIn( card.getMmtcBIn() );
        }
        if ( card.getMmtcBOut() != null ) {
            cardDto.setMmtcBOut( card.getMmtcBOut() );
        }
        if ( card.getProductName() != null ) {
            cardDto.setProductName( card.getProductName() );
        }
        if ( card.getBank() != null ) {
            cardDto.setBank( card.getBank() );
        }
        if ( card.getReviewsCount() != null ) {
            cardDto.setReviewsCount( card.getReviewsCount() );
        }
        if ( card.getRating() != null ) {
            cardDto.setRating( card.getRating() );
        }

        return cardDto;
    }

    @Override
    public Card cardDtoToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        if ( cardDto.getProductId() != null ) {
            card.setProductId( cardDto.getProductId() );
        }
        if ( cardDto.getType() != null ) {
            card.setType( cardDto.getType() );
        }
        if ( cardDto.getGracePeriod() != null ) {
            card.setGracePeriod( cardDto.getGracePeriod() );
        }
        if ( cardDto.getCreditLimit() != null ) {
            card.setCreditLimit( cardDto.getCreditLimit() );
        }
        if ( cardDto.getFirstYearFee() != null ) {
            card.setFirstYearFee( cardDto.getFirstYearFee() );
        }
        if ( cardDto.getSecondYearFee() != null ) {
            card.setSecondYearFee( cardDto.getSecondYearFee() );
        }
        if ( cardDto.getMinAge() != null ) {
            card.setMinAge( cardDto.getMinAge() );
        }
        if ( cardDto.getMaxAge() != null ) {
            card.setMaxAge( cardDto.getMaxAge() );
        }
        if ( cardDto.getCitizenship() != null ) {
            card.setCitizenship( cardDto.getCitizenship() );
        }
        if ( cardDto.getRegistration() != null ) {
            card.setRegistration( cardDto.getRegistration() );
        }
        if ( cardDto.getWorkExperience() != null ) {
            card.setWorkExperience( cardDto.getWorkExperience() );
        }
        if ( cardDto.getMaximum() != null ) {
            card.setMaximum( cardDto.getMaximum() );
        }
        if ( cardDto.getRepaymentMethod() != null ) {
            card.setRepaymentMethod( cardDto.getRepaymentMethod() );
        }
        if ( cardDto.getMaxMonthlyCashback() != null ) {
            card.setMaxMonthlyCashback( cardDto.getMaxMonthlyCashback() );
        }
        if ( cardDto.getNdfl_2_3_4() != null ) {
            card.setNdfl_2_3_4( cardDto.getNdfl_2_3_4() );
        }
        if ( cardDto.getBankCertificate() != null ) {
            card.setBankCertificate( cardDto.getBankCertificate() );
        }
        if ( cardDto.getOtherDocuments() != null ) {
            card.setOtherDocuments( cardDto.getOtherDocuments() );
        }
        if ( cardDto.getPensionCertificate() != null ) {
            card.setPensionCertificate( cardDto.getPensionCertificate() );
        }
        if ( cardDto.getFreeFormCertificate() != null ) {
            card.setFreeFormCertificate( cardDto.getFreeFormCertificate() );
        }
        if ( cardDto.getAdditionalIncomeProof() != null ) {
            card.setAdditionalIncomeProof( cardDto.getAdditionalIncomeProof() );
        }
        if ( cardDto.getPaymentSystem() != null ) {
            card.setPaymentSystem( cardDto.getPaymentSystem() );
        }
        if ( cardDto.getCashback() != null ) {
            card.setCashback( cardDto.getCashback() );
        }
        if ( cardDto.getCmtcIn() != null ) {
            card.setCmtcIn( cardDto.getCmtcIn() );
        }
        if ( cardDto.getCmtcOut() != null ) {
            card.setCmtcOut( cardDto.getCmtcOut() );
        }
        if ( cardDto.getCmtcBIn() != null ) {
            card.setCmtcBIn( cardDto.getCmtcBIn() );
        }
        if ( cardDto.getCmtcBOut() != null ) {
            card.setCmtcBOut( cardDto.getCmtcBOut() );
        }
        if ( cardDto.getMmtcIn() != null ) {
            card.setMmtcIn( cardDto.getMmtcIn() );
        }
        if ( cardDto.getMmtcOut() != null ) {
            card.setMmtcOut( cardDto.getMmtcOut() );
        }
        if ( cardDto.getMmtcBIn() != null ) {
            card.setMmtcBIn( cardDto.getMmtcBIn() );
        }
        if ( cardDto.getMmtcBOut() != null ) {
            card.setMmtcBOut( cardDto.getMmtcBOut() );
        }
        if ( cardDto.getProductName() != null ) {
            card.setProductName( cardDto.getProductName() );
        }
        if ( cardDto.getBank() != null ) {
            card.setBank( cardDto.getBank() );
        }
        if ( cardDto.getReviewsCount() != null ) {
            card.setReviewsCount( cardDto.getReviewsCount() );
        }
        if ( cardDto.getRating() != null ) {
            card.setRating( cardDto.getRating() );
        }

        return card;
    }

    @Override
    public void updateCardFromDto(CardDto cardDto, Card existingCard) {
        if ( cardDto == null ) {
            return;
        }

        if ( cardDto.getProductId() != null ) {
            existingCard.setProductId( cardDto.getProductId() );
        }
        if ( cardDto.getType() != null ) {
            existingCard.setType( cardDto.getType() );
        }
        if ( cardDto.getGracePeriod() != null ) {
            existingCard.setGracePeriod( cardDto.getGracePeriod() );
        }
        if ( cardDto.getCreditLimit() != null ) {
            existingCard.setCreditLimit( cardDto.getCreditLimit() );
        }
        if ( cardDto.getFirstYearFee() != null ) {
            existingCard.setFirstYearFee( cardDto.getFirstYearFee() );
        }
        if ( cardDto.getSecondYearFee() != null ) {
            existingCard.setSecondYearFee( cardDto.getSecondYearFee() );
        }
        if ( cardDto.getMinAge() != null ) {
            existingCard.setMinAge( cardDto.getMinAge() );
        }
        if ( cardDto.getMaxAge() != null ) {
            existingCard.setMaxAge( cardDto.getMaxAge() );
        }
        if ( cardDto.getCitizenship() != null ) {
            existingCard.setCitizenship( cardDto.getCitizenship() );
        }
        if ( cardDto.getRegistration() != null ) {
            existingCard.setRegistration( cardDto.getRegistration() );
        }
        if ( cardDto.getWorkExperience() != null ) {
            existingCard.setWorkExperience( cardDto.getWorkExperience() );
        }
        if ( cardDto.getMaximum() != null ) {
            existingCard.setMaximum( cardDto.getMaximum() );
        }
        if ( cardDto.getRepaymentMethod() != null ) {
            existingCard.setRepaymentMethod( cardDto.getRepaymentMethod() );
        }
        if ( cardDto.getMaxMonthlyCashback() != null ) {
            existingCard.setMaxMonthlyCashback( cardDto.getMaxMonthlyCashback() );
        }
        if ( cardDto.getNdfl_2_3_4() != null ) {
            existingCard.setNdfl_2_3_4( cardDto.getNdfl_2_3_4() );
        }
        if ( cardDto.getBankCertificate() != null ) {
            existingCard.setBankCertificate( cardDto.getBankCertificate() );
        }
        if ( cardDto.getOtherDocuments() != null ) {
            existingCard.setOtherDocuments( cardDto.getOtherDocuments() );
        }
        if ( cardDto.getPensionCertificate() != null ) {
            existingCard.setPensionCertificate( cardDto.getPensionCertificate() );
        }
        if ( cardDto.getFreeFormCertificate() != null ) {
            existingCard.setFreeFormCertificate( cardDto.getFreeFormCertificate() );
        }
        if ( cardDto.getAdditionalIncomeProof() != null ) {
            existingCard.setAdditionalIncomeProof( cardDto.getAdditionalIncomeProof() );
        }
        if ( cardDto.getPaymentSystem() != null ) {
            existingCard.setPaymentSystem( cardDto.getPaymentSystem() );
        }
        if ( cardDto.getCashback() != null ) {
            existingCard.setCashback( cardDto.getCashback() );
        }
        if ( cardDto.getCmtcIn() != null ) {
            existingCard.setCmtcIn( cardDto.getCmtcIn() );
        }
        if ( cardDto.getCmtcOut() != null ) {
            existingCard.setCmtcOut( cardDto.getCmtcOut() );
        }
        if ( cardDto.getCmtcBIn() != null ) {
            existingCard.setCmtcBIn( cardDto.getCmtcBIn() );
        }
        if ( cardDto.getCmtcBOut() != null ) {
            existingCard.setCmtcBOut( cardDto.getCmtcBOut() );
        }
        if ( cardDto.getMmtcIn() != null ) {
            existingCard.setMmtcIn( cardDto.getMmtcIn() );
        }
        if ( cardDto.getMmtcOut() != null ) {
            existingCard.setMmtcOut( cardDto.getMmtcOut() );
        }
        if ( cardDto.getMmtcBIn() != null ) {
            existingCard.setMmtcBIn( cardDto.getMmtcBIn() );
        }
        if ( cardDto.getMmtcBOut() != null ) {
            existingCard.setMmtcBOut( cardDto.getMmtcBOut() );
        }
        if ( cardDto.getProductName() != null ) {
            existingCard.setProductName( cardDto.getProductName() );
        }
        if ( cardDto.getBank() != null ) {
            existingCard.setBank( cardDto.getBank() );
        }
        if ( cardDto.getReviewsCount() != null ) {
            existingCard.setReviewsCount( cardDto.getReviewsCount() );
        }
        if ( cardDto.getRating() != null ) {
            existingCard.setRating( cardDto.getRating() );
        }
    }
}
