package com.selftechlearner.patient_service.entity;

import java.io.Serializable;

public record Address(String street, String city, String state, String zipCode) implements Serializable {
}
