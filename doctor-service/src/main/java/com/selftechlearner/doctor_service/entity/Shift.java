package com.selftechlearner.doctor_service.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record Shift(String shiftId, LocalDate date, LocalTime startTime, LocalTime endTime,
                    boolean isLeave) implements Serializable {
}
