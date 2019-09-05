package com.restAssuredTest.model.safetydailycontrol;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class SafetyEventContentList {

    private List<SafetyEventWithAccountResponse> content;

    public List<SafetyEventWithAccountResponse> getContent() {
        return content;
    }

    public void setContent(List<SafetyEventWithAccountResponse> content) {
        this.content = content;
    }
}
