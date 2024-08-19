package com.selftechlearner.mhr.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public abstract class Auditable<U> {
    @CreatedBy
    private U createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private U updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
