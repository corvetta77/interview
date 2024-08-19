package com.enefit.interview.controller;

import com.enefit.interview.database.BillingEntity;
import com.enefit.interview.database.BillingRepository;
import com.enefit.interview.web.BillingControllerV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

@RestController
public class BillingController {

    @Autowired
    private BillingRepository billingRepository;

    /** get report for current month */
    @GetMapping("/v1/report")
    public BillingData getReport(@RequestParam String clientId) {
        Calendar cal = Calendar.getInstance();
        String dateRepresentation = cal.get(Calendar.YEAR)+"-"+String.format("%02d", cal.get(Calendar.MONTH)+1);

        BillingData billReport = getBillReport(clientId, dateRepresentation);
        return billReport;
    }

    public BillingData getReport(String clientId, BillingControllerV2.BillingDataRequest billingDataRequest) {
        BillingData billReport = getBillReport(clientId, billingDataRequest.getDate());
        return billReport;
    }

    BillingData getBillReport(String clientId, String date) {
        BillingEntity billingEntity = billingRepository.findByClientIdAndDate(clientId, date);

        //for PL market we need to convert from EUR TO PLN
        CurrencyApiResponse currencyApiResponse = WebClient.create()
                .get()
                .uri(builder -> builder.scheme("https")
                        .host("api.currencyapi.com")
                        .path("v3/latest")
                        .queryParam("base_currency", "EUR")
                        .queryParam("currencies", "PLN")
                        .queryParam("apikey", "cur_live_ZD6rXeUgMCl0RWnTDwzgnt7vjkWnAn3AVwChuj4v")
                        .build()
                ).retrieve()
                .bodyToMono(CurrencyApiResponse.class)
                .block();

        BillingData newBillingData;
        if (billingEntity.getCountry().equals("PL")) {
            ApiCurrency plnCurrency = currencyApiResponse.getData().get("PLN");
            System.out.println(plnCurrency);
            newBillingData = new BillingData(billingEntity.getName(), billingEntity.getCountry(), billingEntity.getDate(), currencyApiResponse.getData().get("PLN").getValue().floatValue());
            return newBillingData;
        }

        return new BillingData(billingEntity.getName(), billingEntity.getCountry(), billingEntity.getDate(), billingEntity.getAmountToPay().floatValue());
    }

    public record BillingData(String name, String country, String date, Float money) {
    }

    public static class CurrencyApiResponse {
        Map<String, ApiCurrency> data;

        public Map<String, ApiCurrency> getData() {
            return data;
        }

        public void setData(Map<String, ApiCurrency> data) {
            this.data = data;
        }
    }

    private static class ApiCurrency {
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        String code;

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        BigDecimal value;
    }
}
