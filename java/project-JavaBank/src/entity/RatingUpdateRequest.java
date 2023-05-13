package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RatingUpdateRequest {
    private int requestId;
    private LocalDateTime requestCreation;
    private int staffId;
    private int customerId;
    private CreditRating currentRating;
    private CreditRating proposedRating;
    private boolean isActive;

    public RatingUpdateRequest() {
    }

    public RatingUpdateRequest(int requestId, LocalDateTime requestCreation, int staffId, int customerId,
                               CreditRating currentRating, CreditRating proposedRating, boolean isActive) {
        this.requestId = requestId;
        this.requestCreation = requestCreation;
        this.staffId = staffId;
        this.customerId = customerId;
        this.currentRating = currentRating;
        this.proposedRating = proposedRating;
        this.isActive = isActive;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getRequestCreation() {
        return requestCreation;
    }

    public void setRequestCreation(LocalDateTime requestCreation) {
        this.requestCreation = requestCreation;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CreditRating getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(CreditRating currentRating) {
        this.currentRating = currentRating;
    }

    public void setCurrentRating(String currentRatingStr) {
        if (currentRatingStr.equalsIgnoreCase("A")) this.currentRating = CreditRating.A;
        else if (currentRatingStr.equalsIgnoreCase("B")) this.currentRating = CreditRating.B;
        else if (currentRatingStr.equalsIgnoreCase("C")) this.currentRating = CreditRating.C;
        else this.currentRating = CreditRating.NA;
    }

    public CreditRating getProposedRating() {
        return proposedRating;
    }

    public void setProposedRating(CreditRating proposedRating) {
        this.proposedRating = proposedRating;
    }

    public void setProposedRating(String proposedRatingStr) {

        if (proposedRatingStr.equalsIgnoreCase("A")) this.proposedRating = CreditRating.A;
        else if (proposedRatingStr.equalsIgnoreCase("B")) this.proposedRating = CreditRating.B;
        else if (proposedRatingStr.equalsIgnoreCase("C")) this.proposedRating = CreditRating.C;
        else this.proposedRating = CreditRating.NA;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setActive(String activeStr) {
        if (activeStr.equalsIgnoreCase("true")) this.isActive = true;
        else this.isActive = false;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-5d%20s%-10d%-12d%10s%10s\n", requestId, requestCreation.format(fmtDateTime), staffId,
                customerId, currentRating.toString(), proposedRating.toString());
    }

}
