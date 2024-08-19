package com.selftechlearner.doctor_service.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Availability {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    ON_CALL("On Call"),
    UNAVAILABLE("Unavailable");

    private final String value;
}
