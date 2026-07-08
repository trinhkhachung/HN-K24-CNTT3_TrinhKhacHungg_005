package com.elearning.controllers;

import com.elearning.advice.ApiResponse;
import com.elearning.models.dto.CertificateResponse;
import com.elearning.models.dto.IssueCertificateRequest;
import com.elearning.models.dto.RevokeCertificateRequest;
import com.elearning.models.services.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<ApiResponse<CertificateResponse>> issueCertificate(
            @RequestBody IssueCertificateRequest request) {
        CertificateResponse response = certificateService.issueCertificate(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Certificate issued successfully"));
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<ApiResponse<CertificateResponse>> lookupCertificate(
            @PathVariable("code") String code) {
        CertificateResponse response = certificateService.lookupCertificate(code);
        return ResponseEntity.ok(ApiResponse.success(response, "Certificate verified successfully"));
    }

    @PatchMapping("/{id}/revoke")
    public ResponseEntity<ApiResponse<CertificateResponse>> revokeCertificate(
            @PathVariable("id") Long id,
            @RequestBody RevokeCertificateRequest request) {
        CertificateResponse response = certificateService.revokeCertificate(id, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Certificate revoked successfully"));
    }
}
