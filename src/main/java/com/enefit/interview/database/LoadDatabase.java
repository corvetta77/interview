
package com.enefit.interview.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Calendar;

@Configuration
class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(BillingRepository repository) {

    return args -> {
      Calendar cal = Calendar.getInstance();
      String currentDateRepresentation = cal.get(Calendar.YEAR)+"-"+String.format("%02d", cal.get(Calendar.MONTH)+1);

      log.info("Preparing data: " + repository.save(new BillingEntity("1", "Sofia Tamm", "EE", currentDateRepresentation, new BigDecimal("20.20"))));
      log.info("Preparing data: " + repository.save(new BillingEntity("2", "Maija Meikäläinen", "FI", currentDateRepresentation, new BigDecimal("80.20"))));
      log.info("Preparing data: " + repository.save(new BillingEntity("3", "Marija Ivanova", "LT", "2024-07", new BigDecimal("40.20"))));
      log.info("Preparing data: " + repository.save(new BillingEntity("4", "Jonas Balchunas", "LT", "2024-07", new BigDecimal("60.20"))));
      log.info("Preparing data: " + repository.save(new BillingEntity("5", "Jan Nowak", "PL", currentDateRepresentation, new BigDecimal("50.20"))));
    };
  }
}