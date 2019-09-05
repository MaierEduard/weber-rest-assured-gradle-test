package com.restAssuredTest.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditListResponse {

    private List<AuditResponse> content;

    public List<AuditResponse> getContent() {
        return content;
    }

    public void setContent(List<AuditResponse> content) {
        this.content = content;
    }
}
