package entity;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    LOCKED,
    NA;

    @Override
    public String toString() {
        return switch (this) {
            case ACTIVE -> "ACTIVE";
            case INACTIVE -> "INACTIVE"; // not accessible
            case LOCKED -> "LOCKED"; // pending approval
            default -> "NA";
        };
    }
}
