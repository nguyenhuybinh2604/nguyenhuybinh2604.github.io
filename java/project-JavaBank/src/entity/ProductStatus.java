package entity;

public enum ProductStatus {
    ACTIVE,
    INACTIVE,
    LOCKED,
    NA;

    @Override
    public String toString() {
        return switch (this) {
            case ACTIVE -> "ACTIVE";
            case INACTIVE -> "INACTIVE";
            case LOCKED -> "LOCKED";
            default -> "NA";
        };
    }
}