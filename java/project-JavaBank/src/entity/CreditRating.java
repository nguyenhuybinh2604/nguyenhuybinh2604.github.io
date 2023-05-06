package entity;

public enum CreditRating {
    A,
    B,
    C,
    NA;

    @Override
    public String toString() {
        return switch (this) {
            case A -> "A";
            case B -> "B";
            case C -> "C";
            default -> "NA";
        };
    }
}
