package com.selftechlearner.mhr.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LabResultStatus {
    NORMAL("normal"),
    AB_NORMAL("ab-normal");
    private final String status;
}
