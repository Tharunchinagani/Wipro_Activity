package com.monocartCategory.repositoty;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartCategory.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

