package entity;

import java.time.LocalDateTime;

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

    public CreditRating getProposedRating() {
        return proposedRating;
    }

    public void setProposedRating(CreditRating proposedRating) {
        this.proposedRating = proposedRating;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
