package com.example.shopecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//trạng thái trả về cho client, phía server có vấn đề
//ném ra khi có sự cố xảy ra trong quá trình xử lý dữ liệu liên quan đến danh mục (category)
//ngoại lệ này xảy ra, HTTP response sẽ có mã trạng thái là "BAD_REQUEST" (mã 400).
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryException extends RuntimeException{
    //constructor của lớp CategoryException, cho phép bạn tạo một đối tượng ngoại lệ với một thông báo cụ thể mô tả lý do của ngoại lệ.
    public CategoryException(String message) {
        super(message);
    }


}
