package com.example.shopecommerce.service;


import com.example.shopecommerce.domain.Category;
import com.example.shopecommerce.exception.CategoryException;
import com.example.shopecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category  save(Category entity) {
        return categoryRepository.save(entity);
    }

    public Category update (Long id, Category entity) {
        Optional<Category> existed = categoryRepository.findById(id);
        if (existed.isEmpty()){ //Kiểm tra xem có tồn tại một đối tượng "Category" với ID đã cho hay không.
            throw new CategoryException("Category id"
                    +id + "does not exist");
            //Nếu không tồn tại, một ngoại lệ CategoryException được ném với thông báo lỗi tương ứng.
        }
        try{
            //Lấy ra đối tượng "Category" từ Optional nếu nó tồn tại.
            Category existedCategory= existed.get();
            //Cập nhật tên của đối tượng "Category" đã tồn tại
            existedCategory.setName(entity.getName());
            // Cập nhật trạng thái của đối tượng "Category" đã tồn tại
            existedCategory.setStatus (entity.getStatus());
            return categoryRepository.save(existedCategory);
        }catch (Exception ex) {
            throw new CategoryException("Category is updated fail");
        }

    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    // phương thức tìm kiếm và hỗ tro phân trang
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category findById(Long id) {
        Optional<Category> found= categoryRepository.findById(id);
        if (found.isEmpty()){ // id không tồn tại
            throw new CategoryException("Category with id " + id + "does not exist");
        }
        // có  id Category sẽ trả về thông tin Category
        return found.get();
    }

    public void deleteById(Long id) {
        Category existed = findById(id);
        categoryRepository.delete(existed);
    }
}
