package com.medkaapp.error;

public class MedicamentoNotFoundException extends RuntimeException {
    public MedicamentoNotFoundException(String message) {
        super(message);
    }
}
