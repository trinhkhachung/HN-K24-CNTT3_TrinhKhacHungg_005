package com.elearning.models.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CertificateResponse {
    private Long id;
    private String certificateCode;
    private Long userId;
    private String userFullName;
    private Long courseId;
    private String courseTitle;
    private String grade;
    private LocalDate issueDate;
    private LocalDate expireDate;
    private String status;
    private LocalDate revokeDate;
    private String revokeReason;
}
