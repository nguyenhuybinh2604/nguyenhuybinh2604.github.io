package entity;

public enum TransactionType {
    ADDBALANCE,
    FUNDTRANSFER,
    FOREIGNEXCHANGE,
    NA;

    @Override
    public String toString() {
        return switch (this) {
            case ADDBALANCE -> "BALANCE";
            case FUNDTRANSFER -> "FT";
            case FOREIGNEXCHANGE -> "FX";
            default -> "NA";
        };
    }
}
