package com.building_mannager_system.enums;

public enum PaymentStatus {
    PAID("Paid"),    // Trạng thái đã thanh toán
    UNPAID("Unpaid"); // Trạng thái chưa thanh toán

    private final String status;

    // Constructor
    PaymentStatus(String status) {
        this.status = status;
    }

    // Getter
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
