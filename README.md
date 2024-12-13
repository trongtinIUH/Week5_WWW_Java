# 21054421_TranTrongTin_WWWJava_BTWeek


### Hướng dẫn chuẩn bị và chạy dự án Week5

#### 1. Chuẩn bị trước khi chạy dự án:
Chạy file SQL đi kèm: Dùng để khởi tạo dữ liệu mẫu ban đầu (ví dụ: danh sách kỹ năng - skills).
Cấu hình MariaDB:
Mở file application.properties và chỉnh sửa thông tin userName và passWord phù hợp với môi trường của bạn.
Tạo dữ liệu mẫu:
Trong file BaiTapWeek5Application.java, bỏ chú thích (uncomment) đoạn mã liên quan đến việc khởi tạo 1000 bản ghi vào cơ sở dữ liệu, chạy ứng dụng để thêm dữ liệu mẫu, sau đó có thể đóng chú thích lại.

#### 2. Giới thiệu về dự án
Dự án này xây dựng một trang web tuyển dụng với các chức năng chính sau:Trang chủ (http://localhost:8080):
Hiển thị các công việc được đăng bởi các công ty tuyển dụng.
Ứng tuyển: (Chức năng này đang được phát triển).
Quản lý ứng viên:
Danh sách ứng viên:Hiển thị danh sách ứng viên ở dạng có phân trang và không phân trang. Thêm ứng viên mới. Xóa ứng viên: (Không khuyến khích sử dụng). Chỉnh sửa thông tin ứng viên.
Ứng viên đăng nhập:
Đăng nhập bằng email.
Gợi ý kỹ năng: Đề xuất các kỹ năng ứng viên chưa có để thêm vào.
Chỉnh sửa hồ sơ cá nhân.
Tìm kiếm công việc phù hợp: Dựa trên kỹ năng hiện có của ứng viên.
Quản lý công ty:

Đăng nhập: Công ty có thể đăng nhập bằng email (hoặc đăng ký nếu chưa có tài khoản).
Đăng tin tuyển dụng:
Các công việc được đăng sẽ hiển thị trên trang chủ.
Cho phép tìm kiếm ứng viên phù hợp với từng công việc.
Quản lý thông tin công ty:
Sửa thông tin hồ sơ công ty (nếu cần).

#### 3. Lưu ý khi sử dụng
Không sử dụng tiếng Việt có dấu: Ứng dụng hiện chưa hỗ trợ tốt việc lưu trữ và xử lý tiếng Việt có dấu.

#### 4. Hướng dẫn sử dụng
Truy cập trang chủ: http://localhost:8080.
Chạy các chức năng:
Ứng viên:
Thêm mới, chỉnh sửa, xóa (nếu cần), đăng nhập, tìm kiếm kỹ năng hoặc công việc phù hợp.
Công ty:
Đăng ký, đăng nhập, đăng bài tuyển dụng, chỉnh sửa thông tin, tìm kiếm ứng viên.
Tìm kiếm và quản lý:
Công việc phù hợp với ứng viên.
Ứng viên phù hợp với yêu cầu tuyển dụng của công ty.

#### 5. Điểm nổi bật
Tích hợp phân trang: Giúp hiển thị danh sách dài dễ dàng hơn.
Giao diện đơn giản, dễ sử dụng.
Gợi ý kỹ năng: Hỗ trợ cải thiện hồ sơ ứng viên, nâng cao cơ hội tìm được công việc phù hợp.
Cảm ơn bạn đã sử dụng hệ thống!
