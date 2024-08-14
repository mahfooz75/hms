package com.selftechlearner.patient_service.dto;

import java.io.Serializable;

public record AddressDto(String street, String city, String state, String zipCode) implements Serializable {
}
