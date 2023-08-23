package com.example.shopecommerce.dto;

import com.example.shopecommerce.domain.CategoryStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Value;



import java.io.Serializable;

/**
 * DTO for {@link com.example.shopecommerce.domain.Category}
 */
// Lớp Dto là lớp giao tiếp giữa client và server

@Data
public class CategoryDto implements Serializable {

    Long id;
    @NotEmpty(message = "Name must be entered")
    String name;

    CategoryStatus status;
}