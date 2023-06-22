package com.freelance.models;

public class AccountStatus {

    private Integer statusId;
    private String username;
    private String status;
    private String reasonForDenial;

    public AccountStatus() {
    }

    public AccountStatus(String username) {

        this.username = username;
    }

    public AccountStatus(String username, String reasonForDenial) {
        this.username = username;
        this.reasonForDenial = reasonForDenial;
    }

    public AccountStatus(Integer statusId, String username, String status, String reasonForDenial) {
        this.statusId = statusId;
        this.username = username;
        this.status = status;
        this.reasonForDenial = reasonForDenial;
    }

    public Integer getStatusId() {
        return this.statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String setStatus() {
        return this.status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getReasonForDenial() {
        return this.reasonForDenial;
    }

    public void setReasonForDenial(String reasonForDenial) {
        this.reasonForDenial = reasonForDenial;
    }

    @Override
    public String toString() {
        return "{" +
                " statusId='" + getStatusId() + "'" +
                ", username='" + getUsername() + "'" +
                ", status='" + getStatus() + "'" +
                ", reasonForDenial='" + getReasonForDenial() + "'" +
                "}";
    }

}
