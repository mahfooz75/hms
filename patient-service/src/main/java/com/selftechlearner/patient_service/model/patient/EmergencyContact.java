package com.selftechlearner.patient_service.model.patient;

import java.io.Serializable;

public record EmergencyContact(String name, String relationship, String phone) implements Serializable {
}
