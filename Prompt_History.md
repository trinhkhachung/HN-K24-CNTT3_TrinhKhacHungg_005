# Prompt_History.md

## AI APPLICATION IN ACTION - ĐỀ 005

---

## Prompt 1 - Phân tích Base Code

```text
Đọc toàn bộ Base Code của project Spring Boot hiện tại.

Phân tích:
- Cấu trúc package
- Entity
- Repository
- Service
- Controller
- DTO
- Security
- Exception Handler
- Database

KHÔNG code.

Cho tôi biết project hiện có gì và cần bổ sung những gì để hoàn thành Đề 005.
```

---

## Prompt 2 - Xác nhận sử dụng đúng Base Code

```text
Đây là project Base Code E-Learning của Đề 005.

Giữ nguyên toàn bộ kiến trúc hiện tại.

KHÔNG xây dựng hệ thống Hotel Management.

KHÔNG sử dụng nội dung của file System_Design_SRS.pdf.

Hãy thực hiện đúng Đề 005:

- Giữ nguyên Entity User và Course.
- Thiết kế và bổ sung Entity Certificate.
- Viết SRS.md theo yêu cầu đề.
- Bổ sung Repository, Service, Controller, DTO (nếu cần), Exception và GlobalExceptionHandler.
- Áp dụng đúng Security hiện có.
- API tra cứu phải xử lý:
  + Không tồn tại → HTTP 404
  + REVOKED → "Chứng chỉ không hợp lệ do vi phạm"
  + Hết hạn → "Đã hết hạn"
  + Còn hiệu lực → trả thông tin chứng chỉ.

Sau mỗi bước hãy giải thích ngắn gọn và chỉ sửa những file cần thiết.
```

---

## Prompt 3 - Thiết kế SRS

```text
Bắt đầu với Nhiệm vụ 1.

Đọc User và Course hiện có, sau đó tạo file SRS.md hoàn chỉnh theo yêu cầu Đề 005.

SRS phải gồm:

- Business Rules
- Thiết kế Entity Certificate
- Quan hệ với User và Course
- ERD
- State Machine
- Thuật toán API tra cứu (Pseudo Code)
- Phân quyền API
- Danh sách API

Chưa viết code Java.
```

---

## Prompt 4 - Hiện thực chức năng Certificate

```text
Tiếp tục Nhiệm vụ 2.

Dựa trên SRS.md và Base Code hiện tại, hãy hiện thực toàn bộ chức năng Certificate.

Yêu cầu:

- Giữ nguyên kiến trúc project.
- Không sửa User và Course nếu không cần.
- Thêm Certificate Entity.
- Tạo Repository.
- Tạo DTO nếu project đang sử dụng.
- Tạo Service.
- Tạo Controller.

Logic:

- issueDate = LocalDate.now()
- expireDate = issueDate.plusYears(1)
- status = ACTIVE

Lookup:

- Không tồn tại -> HTTP 404
- REVOKED -> "Chứng chỉ không hợp lệ do vi phạm"
- Hết hạn -> "Đã hết hạn"
- Còn hiệu lực -> trả về thông tin chứng chỉ.

Thu hồi:

- status = REVOKED
- lưu revokeDate
- lưu revokeReason

Áp dụng Security hiện có:

- Lookup: Public
- Issue: Admin
- Revoke: Admin

Không tạo Security mới.
```

---

## Prompt 5 - Kiểm tra và sửa lỗi

```text
Build project.

Kiểm tra toàn bộ lỗi compile.

Nếu có lỗi thì tự sửa.

Lặp lại cho đến khi project BUILD SUCCESS.

Sau đó liệt kê toàn bộ các file đã tạo hoặc chỉnh sửa.
```

---

## Prompt 6 - Review theo tiêu chí chấm

```text
Review toàn bộ project theo tiêu chí chấm của Đề 005.

Kiểm tra:

- SRS.md
- Certificate Entity
- Repository
- Service
- Controller
- Security
- Exception Handler
- API
- Logic hết hạn
- Logic thu hồi
- Quan hệ JPA

Nếu còn thiếu hãy bổ sung.

Nếu có thay đổi ngoài yêu cầu hãy khôi phục.
```

---

## Prompt 7 - Kiểm tra cuối

```text
Kiểm tra toàn bộ project như Senior Java Spring Boot Developer.

Thực hiện:

- Build project.
- Kiểm tra compile.
- Kiểm tra Entity Mapping.
- Kiểm tra Repository.
- Kiểm tra Service.
- Kiểm tra Controller.
- Kiểm tra Security.
- Kiểm tra Exception Handler.
- Kiểm tra API.