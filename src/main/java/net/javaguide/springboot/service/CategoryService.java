package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.entity.Category;

public interface CategoryService {

	Category createCategory(Category category);

	Category getCategoryById(Long catId);

	List<Category> getAllCategories();

	Category updateCategory(Category category);

	void deleteCategoryById(Long catId);

	void deleteAllCategory();
}
