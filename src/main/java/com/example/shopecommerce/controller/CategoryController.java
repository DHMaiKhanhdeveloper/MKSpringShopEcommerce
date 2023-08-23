package com.example.shopecommerce.controller;

import com.example.shopecommerce.domain.Category;
import com.example.shopecommerce.dto.CategoryDto;
import com.example.shopecommerce.service.CategoryService;
import com.example.shopecommerce.service.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    //BindingResult luôn luôn đi với annotation @Valid kiểm tra tính hợp lệ dữ liệu
    //Save Entity Category truyền vào từ phía client
    @PostMapping
    public ResponseEntity<?> createCategory (@Valid @RequestBody CategoryDto dto,
                                             BindingResult result){
//        if(result.hasErrors()){// có lỗi xảy ra
//            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
//            //trả về tất cả các lỗi
//        }
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);
        if (responseEntity != null) {// có lỗi xảy ra
            return responseEntity;
        }
        //ResponseEntity: chứa thông tin trạng thái phản hồi
        //sử dụng dấu ? thay cho CategoryDto
        Category entity = new Category();
        //copy nội dung từ API client CategoryDto(API) sang cơ sở dữ liệu Category(Database)
        BeanUtils.copyProperties (dto, entity);
        //entity.setId(dto.getId());
        //entity.setName(dto.getName());
        //entity.setStatus(dto.getStatus());

        //lưu vào cơ sở dữ liệu
        entity =categoryService.save(entity);
        //cập nhật lại thông tin id
        dto.setId(entity.getId());
        // trả vê thông tin về phía client
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory (@PathVariable("id") Long id, @RequestBody CategoryDto dto){
        Category entity = new Category();
        BeanUtils.copyProperties (dto, entity);
        entity = categoryService.update(id,entity);
        dto.setId(entity.getId());
    return new ResponseEntity<> (dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getCategories(@PageableDefault(size = 5, sort = "name", direction = Sort. Direction.ASC)
                                           Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<?> getCategories (@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory (@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        //delete có kèm theo ngoại lê nên chèn thêm mess "Category with Id:"
        //                + id + "was deleted" vào ResponseEntity
        return new ResponseEntity<>(  "Category with Id:"
                + id + "was deleted", HttpStatus.OK);
    }





}
