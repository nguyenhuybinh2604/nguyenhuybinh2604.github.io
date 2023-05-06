package entity;

public enum UserRole {
    CUSTOMER,
    STAFF,
    MANAGER;

    @Override
    public String toString() {
        return switch (this) {
            case CUSTOMER -> "CUSTOMER";
            case STAFF -> "STAFF";
            case MANAGER -> "MANAGER";
        };
    }
}
