package net.javaguide.springboot.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
//import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.Category;
import net.javaguide.springboot.entity.Products;
import net.javaguide.springboot.model.ResponseHandler;
import net.javaguide.springboot.service.CategoryService;
import net.javaguide.springboot.service.ProductsService;
import net.javaguide.springboot.upload.FileDownloadUtil;
import net.javaguide.springboot.upload.FileUploadResponse;
import net.javaguide.springboot.upload.FileUploadUtil;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("api/")
public class ProductController {
	@Autowired
	private ProductsService productsService;

	@Autowired
	private CategoryService categoryService;

	// build create Product REST API
	@PostMapping("products/createProduct")
	public ResponseEntity<Object> createProduct(@ModelAttribute Products products,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String filecode = FileUploadUtil.saveFile(fileName, multipartFile, "Files-Upload/Products");
		LocalDateTime ldt = LocalDateTime.now();
		String Date = DateTimeFormatter.ofPattern("yyyy-MM-dd.hh-MM-ss").format(ldt);
		products.setActive(true);
		products.setImagePath(filecode);
		products.setCreatedDate(Date);
		products.setUpdate_date(Date);
		Products savedProducts = productsService.createProduct(products);
		return ResponseHandler.generateResponse("Product added Successfully", HttpStatus.CREATED, savedProducts);
	}

	// build Update Product REST API
	@PutMapping("products/updateProduct/{id}")
	public ResponseEntity<Products> updateProduct(@PathVariable("id") long productId, @ModelAttribute Products products,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String filecode = FileUploadUtil.saveFile(fileName, multipartFile, "Files-Upload/Products");

		Products updatedProduct = productsService.updateProducts(products, filecode);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	// build get Products by id REST API
	// http://localhost:8080/api/users/1
	@GetMapping("products/getProductById/{id}")
	public Object getProductById(@PathVariable("id") Long productId) {
		Object products = productsService.getProductDetailsBycartId(productId);
		return ResponseHandler.generateResponse("Received", HttpStatus.OK, products);
	}

	// build get Products by Catid REST API
	@GetMapping("products/getProductByCatId/{id}")
	public ResponseEntity<List<Products>> getProductByCatId(@PathVariable("id") Long catId) {
		List<Products> prodList = productsService.getProductsByCatId(catId);
		return new ResponseEntity<>(prodList, HttpStatus.OK);
	}

	// build Get All Products REST API
	@GetMapping("products/getAllProducts")
	public ResponseEntity<List<Products>> getAllProducts() {
		List<Products> productsAll = productsService.getAllProducts();
		return new ResponseEntity<>(productsAll, HttpStatus.OK);
	}

	// Build Delete Product by Id REST API
	@DeleteMapping("products/deleteProductById/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") long productId) {
		productsService.deleteProducts(productId);
		return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
	}

	// Build Delete All Products API
	@DeleteMapping("products/deleteAll")
	public ResponseEntity<String> deleteAllProducts() {
		productsService.deleteAllProducts();
		return new ResponseEntity<>("All Products successfully deleted!", HttpStatus.OK);
	}

	// Build Create Category REST API
	@PostMapping("category/createCategory")
	public ResponseEntity<Object> createCategory(@ModelAttribute Category category) throws IOException {
		LocalDateTime ldt = LocalDateTime.now();
		String Date = DateTimeFormatter.ofPattern("yyyy-MM-dd.hh-MM-ss").format(ldt);
		category.setCatName(category.getCatName());
		category.setCreatedDate(Date);
		category.setUpdatedDate(Date);
		category.setActive(true);
		Category savedCategories = categoryService.createCategory(category);
		return ResponseHandler.generateResponse("Category added Successfully", HttpStatus.CREATED, savedCategories);
	}

	// build Update Category REST API
	@PutMapping("category/updateCategoryById/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") long catId, @ModelAttribute Category category) {
		Category updateCategory = categoryService.updateCategory(category);
		return new ResponseEntity<>(updateCategory, HttpStatus.OK);
	}

	// build get Category by id REST API
	// http://localhost:8080/api/users/1
	@GetMapping("category/getCategoryById/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long catId) {
		Category category = categoryService.getCategoryById(catId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	// build Get All Category REST API
	@GetMapping("category/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categoryAll = categoryService.getAllCategories();
		return new ResponseEntity<>(categoryAll, HttpStatus.OK);
	}

	// Build Delete Product by Id REST API
	@DeleteMapping("category/deleteCategoryById/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") long catId) {
		categoryService.deleteCategoryById(catId);
		return new ResponseEntity<>("Category successfully deleted!", HttpStatus.OK);
	}

	// Build Delete All Products API
	@DeleteMapping("category/deleteAll")
	public ResponseEntity<String> deleteAllCategorys() {
		categoryService.deleteAllCategory();
		return new ResponseEntity<>("All Categorys successfully deleted!", HttpStatus.OK);
	}

	// Build Upload File by Id REST API
	@PostMapping("users/uploadFile")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile)
			throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();

		String filecode = FileUploadUtil.saveFile(fileName, multipartFile, "Files-Upload");

		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(filecode + " " + fileName);
		response.setSize(size);
		response.setDownloadUri("/downloadFile/" + filecode);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	// Build Download File by Id REST API
	@GetMapping("users/downloadFile/{fileCode}")
	public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
		FileDownloadUtil downloadUtil = new FileDownloadUtil();

		Resource resource = null;
		try {
			resource = downloadUtil.getFileAsResource(fileCode);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resource);
	}

	@GetMapping("/products/search")
	public ResponseEntity<List<Products>> searchProducts(@RequestParam("query") String query) {
		return ResponseEntity.ok(productsService.searchProduct(query));
	}

	@GetMapping("/products/pagination/{pageNo}/{pageSize}")
	public ResponseEntity<Object> Pagination(@PathVariable("pageNo") Integer pageNo,
			@PathVariable("pageSize") Integer pageSize) {
		Object prod = productsService.paginatedProd(pageNo - 1, pageSize);
		return ResponseEntity.ok(prod);
	}
}
