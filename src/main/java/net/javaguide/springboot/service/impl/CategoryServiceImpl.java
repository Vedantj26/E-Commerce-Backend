package net.javaguide.springboot.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.Category;
import net.javaguide.springboot.exception.RecordNotFoundException;
import net.javaguide.springboot.repository.CategoryRepository;
import net.javaguide.springboot.service.CategoryService;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Long catId) {
		Optional<Category> optionalCategory = categoryRepository.findById(catId);
		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			throw new RecordNotFoundException("Category not found with Id " + catId);
		}
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category category) {
		LocalDateTime ldt = LocalDateTime.now();
		String Date = DateTimeFormatter.ofPattern("yyyy-MM-dd.hh-MM-ss").format(ldt);
		Category existingCategory = categoryRepository.findById(category.getId()).get();
		existingCategory.setCatName(category.getCatName());
		existingCategory.setUpdatedDate(Date);
		categoryRepository.save(existingCategory);
		return existingCategory;
	}

	@Override
	public void deleteCategoryById(Long catId) {
		categoryRepository.deleteById(catId);
	}

	@Override
	public void deleteAllCategory() {
		categoryRepository.deleteAll();
	}

}
