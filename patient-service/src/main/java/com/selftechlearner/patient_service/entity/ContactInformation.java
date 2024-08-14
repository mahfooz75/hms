package com.selftechlearner.patient_service.entity;

import java.io.Serializable;

public record ContactInformation(String email, String phone) implements Serializable {
}