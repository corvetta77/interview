package com.enefit.interview.web;

import com.enefit.interview.controller.BillingController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BillingControllerV2 {
    private final BillingController billingController;

    public BillingControllerV2(BillingController billingController) {
        this.billingController = billingController;
    }

    @GetMapping(value = "/v2/report/{clientId}/raw")
    @ResponseBody
    @Operation(summary = "Get bill pdf", description = "")
    public BillingController.BillingData getBetterReport(@PathVariable String clientId,
                                                         @Parameter(description = "Request containing information about 'clientId' as string and 'date' as string in a format 'yyyy-mm'")
                                                         BillingDataRequest billingDataRequest) {
        return billingController.getReport(clientId, billingDataRequest);
    }

    @GetMapping("/v2/report/{clientId}/html")
    /* Get report as html*/
    public String getBetterReportInHtml(Model model, @PathVariable String clientId) {
        BillingController.BillingData data = billingController.getReport(clientId);

        model.addAttribute("country", data.country());
        model.addAttribute("name", data.name());
        model.addAttribute("date", data.date());
        model.addAttribute("money", data.money());
        return "invoice";
    }

    public static class ClientId {
        private String clientId;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }
    }

    public static class BillingDataRequest {
        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        private String clientId;
        private String date;

    }
}
