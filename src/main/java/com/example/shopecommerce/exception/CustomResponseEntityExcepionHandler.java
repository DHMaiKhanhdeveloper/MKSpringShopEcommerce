package com.example.shopecommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Thông báo cho client khi xảy ra lỗi 400,405
//phương thức để xử lý các loại ngoại lệ cụ thể cho toàn bộ ứng dụng
//ResponseEntityExceptionHandler là class hỗ trợ xử lý ngoại lệ và trả về ResponseEntity chứa thông tin lỗi.
@ControllerAdvice
@RestController
public class CustomResponseEntityExcepionHandler extends ResponseEntityExceptionHandler {
    //@ExceptionHandler cho hệ thống nhận biết xử lý Exception nào ?
    @ExceptionHandler(CategoryException.class)
    public final ResponseEntity<Object>  handleCategoryException (CategoryException ex, WebRequest request) {
        CategoryExceptionResponse exceptionResponse = new CategoryExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    //giúp bạn tạo ra một trình xử lý ngoại lệ tùy chỉnh để trả về các thông báo lỗi định dạng chuẩn cho toàn bộ ứng dụng.
}
