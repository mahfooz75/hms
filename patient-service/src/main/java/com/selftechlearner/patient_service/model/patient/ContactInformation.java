package com.selftechlearner.patient_service.model.patient;

import java.io.Serializable;

public record ContactInformation(String email, String phone) implements Serializable {
}