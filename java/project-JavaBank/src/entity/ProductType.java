package entity;

public enum ProductType {
    ACCOUNT,
    LOAN,
    SAVING,
    NA;

    @Override
    public String toString() {
        return switch (this) {
            case ACCOUNT -> "ACCOUNT";
            case LOAN -> "LOAN";
            case SAVING -> "SAVING";
            default -> "NA";
        };
    }
}
