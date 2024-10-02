package com.selftechlearner.patient_service.model.patient;

import java.io.Serializable;

public record Address(String street, String city, String state, String zipCode) implements Serializable {
}
