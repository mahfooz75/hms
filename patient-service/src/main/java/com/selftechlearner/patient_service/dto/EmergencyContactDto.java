package com.selftechlearner.patient_service.dto;

import java.io.Serializable;

public record EmergencyContactDto(String name, String relationship, String phone) implements Serializable {
}
