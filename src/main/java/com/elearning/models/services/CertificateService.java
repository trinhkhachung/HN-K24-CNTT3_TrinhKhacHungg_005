package com.elearning.models.services;

import com.elearning.exceptions.BusinessException;
import com.elearning.models.dto.CertificateResponse;
import com.elearning.models.dto.IssueCertificateRequest;
import com.elearning.models.dto.RevokeCertificateRequest;
import com.elearning.models.entities.Certificate;
import com.elearning.models.entities.Course;
import com.elearning.models.entities.User;
import com.elearning.models.repositories.CertificateRepository;
import com.elearning.models.repositories.CourseRepository;
import com.elearning.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public CertificateResponse issueCertificate(IssueCertificateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BusinessException(404, "User not found"));
        
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BusinessException(404, "Course not found"));

        if (certificateRepository.existsByUserIdAndCourseId(request.getUserId(), request.getCourseId())) {
            throw new BusinessException(400, "Certificate already exists for this user and course");
        }

        Certificate certificate = new Certificate();
        String uniqueCode = "CERT-" + user.getId() + "-" + course.getId() + "-" + System.currentTimeMillis();
        
        certificate.setCertificateCode(uniqueCode);
        certificate.setUser(user);
        certificate.setCourse(course);
        certificate.setGrade(request.getGrade());
        
        LocalDate now = LocalDate.now();
        certificate.setIssueDate(now);
        certificate.setExpireDate(now.plusYears(1));
        certificate.setStatus("ACTIVE");

        Certificate saved = certificateRepository.save(certificate);
        return convertToResponse(saved);
    }

    public CertificateResponse lookupCertificate(String certificateCode) {
        Certificate certificate = certificateRepository.findByCertificateCode(certificateCode)
                .orElseThrow(() -> new BusinessException(404, "Certificate not found"));

        // Dynamically check expiration
        if ("ACTIVE".equals(certificate.getStatus()) && LocalDate.now().isAfter(certificate.getExpireDate())) {
            certificate.setStatus("EXPIRED");
            certificate = certificateRepository.save(certificate);
        }

        if ("REVOKED".equals(certificate.getStatus())) {
            throw new BusinessException(400, "Chứng chỉ không hợp lệ do vi phạm");
        }

        if ("EXPIRED".equals(certificate.getStatus())) {
            throw new BusinessException(400, "Đã hết hạn");
        }

        return convertToResponse(certificate);
    }

    public CertificateResponse revokeCertificate(Long id, RevokeCertificateRequest request) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "Certificate not found"));

        certificate.setStatus("REVOKED");
        certificate.setRevokeDate(LocalDate.now());
        certificate.setRevokeReason(request.getRevokeReason());

        Certificate saved = certificateRepository.save(certificate);
        return convertToResponse(saved);
    }

    private CertificateResponse convertToResponse(Certificate certificate) {
        CertificateResponse response = new CertificateResponse();
        response.setId(certificate.getId());
        response.setCertificateCode(certificate.getCertificateCode());
        response.setUserId(certificate.getUser().getId());
        response.setUserFullName(certificate.getUser().getFullName());
        response.setCourseId(certificate.getCourse().getId());
        response.setCourseTitle(certificate.getCourse().getTitle());
        response.setGrade(certificate.getGrade());
        response.setIssueDate(certificate.getIssueDate());
        response.setExpireDate(certificate.getExpireDate());
        response.setStatus(certificate.getStatus());
        response.setRevokeDate(certificate.getRevokeDate());
        response.setRevokeReason(certificate.getRevokeReason());
        return response;
    }
}
