package com.linkedin.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseModel {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
