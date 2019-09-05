package com.restAssuredTest.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditResponse {

    private String id;
    private LocalDate date;
    private String status;
    private String authorAccountId;
    private String authorFirstName;
    private String authorLastName;
    private String externalAuthorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorAccountId() {
        return authorAccountId;
    }

    public void setAuthorAccountId(String authorAccountId) {
        this.authorAccountId = authorAccountId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getExternalAuthorName() {
        return externalAuthorName;
    }

    public void setExternalAuthorName(String externalAuthorName) {
        this.externalAuthorName = externalAuthorName;
    }
}
