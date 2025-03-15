package com.example.microservice1.repository;

import com.example.microservice1.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
//    Optional<Card> findById(UUID productId);
//
//    List<Card> findAll();
//
//    Card save(Card card);
//
//    void deleteById(UUID productId);
}
