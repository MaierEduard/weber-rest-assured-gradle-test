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
public class CriticalRiskObjectiveList {

    private List<CriticalRiskObjective> content;

    public List<CriticalRiskObjective> getContent() {
        return content;
    }

    public void setContent(List<CriticalRiskObjective> content) {
        this.content = content;
    }
}
