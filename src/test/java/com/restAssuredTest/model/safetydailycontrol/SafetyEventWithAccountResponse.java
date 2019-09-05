package com.restAssuredTest.model.safetydailycontrol;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restAssuredTest.common.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SafetyEventWithAccountResponse {

    private List<SafetyEvent> safetyEvents;
    private AccountResponse reporter;

    public AccountResponse getReporter() {
        return reporter;
    }

    public void setReporter(AccountResponse reporter) {
        this.reporter = reporter;
    }

    public List<SafetyEvent> getSafetyEvents() {
        return safetyEvents;
    }

    public void setSafetyEvents(List<SafetyEvent> safetyEvents) {
        this.safetyEvents = safetyEvents;
    }
}
