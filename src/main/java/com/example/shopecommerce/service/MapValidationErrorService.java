package com.example.shopecommerce.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {
    //ánh xạ các thông tin lỗi trong quá trình kiểm tra tính hợp lệ của dữ liệu
    public ResponseEntity<?> mapValidationFields (BindingResult result ){
        if (result.hasErrors()) {// nếu có lỗi
            //lưu trữ các thông tin về các lỗi validation.
            Map<String, String> errorMap = new HashMap<>();
            //Với mỗi FieldError, nó sẽ thêm thông tin về lỗi vào errorMap,
            // sử dụng tên trường (error.getField()) làm khóa và thông báo lỗi (error.getDefaultMessage()) làm giá trị.
            for (FieldError error :
                    result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            //trả về ResponseEntity bao gồm các thông tin lỗi ánh xạ và  HTTP BAD REQUEST (400)
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        // Nếu không có lỗi validation, phương thức trả về null
        return null;
    }
}
