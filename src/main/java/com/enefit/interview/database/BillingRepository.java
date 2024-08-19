package com.enefit.interview.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<BillingEntity, Long> {
    BillingEntity findByCountry(String country);
    BillingEntity findByClientIdAndDate(String country, String date);
}