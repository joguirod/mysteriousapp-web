package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.NonPositiveNumberException;
import br.com.mysterious.mysteriousapi.application.exceptions.ProductNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.RequiredValueException;
import br.com.mysterious.mysteriousapi.application.usecases.product.*;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.ProductDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.ProductRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.CategoryIncomeResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.GenreIncomeResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.ProductResponseDTO;
import br.com.mysterious.mysteriousapi.persistence.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final ListAllProductsUseCase listAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ProductDTOMapper productDTOMapper;
    private final GetCategoriesSoldByYearUseCase getCategoriesSoldByYearUseCase;
    private final GetCategoriesSoldByMonthUseCase getCategoriesSoldByMonthUseCase;
    private final GetCategoriesSoldByEpochUseCase getCategoriesSoldByEpochUseCase;
    private final GetGenresSoldByYearUseCase getGenresSoldByYearUseCase;
    private final GetGenresSoldByMonthUseCase getGenresSoldByMonthUseCase;
    private final GetGenresSoldByEpochUseCase getGenresSoldByEpochUseCase;

    public ProductController(ProductRepository productRepository, ListAllProductsUseCase listAllProductsUseCase, CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, GetCategoriesSoldByYearUseCase getCategoriesSoldByYearUseCase, GetCategoriesSoldByMonthUseCase getCategoriesSoldByMonthUseCase, GetCategoriesSoldByEpochUseCase getCategoriesSoldByEpochUseCase, GetGenresSoldByYearUseCase getGenresSoldByYearUseCase, GetGenresSoldByMonthUseCase getGenresSoldByMonthUseCase, GetGenresSoldByEpochUseCase getGenresSoldByEpochUseCase) {
        this.listAllProductsUseCase = listAllProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.productDTOMapper = new ProductDTOMapper();
        this.createProductUseCase = createProductUseCase;
        this.getCategoriesSoldByYearUseCase = getCategoriesSoldByYearUseCase;
        this.getCategoriesSoldByMonthUseCase = getCategoriesSoldByMonthUseCase;
        this.getCategoriesSoldByEpochUseCase = getCategoriesSoldByEpochUseCase;
        this.getGenresSoldByYearUseCase = getGenresSoldByYearUseCase;
        this.getGenresSoldByMonthUseCase = getGenresSoldByMonthUseCase;
        this.getGenresSoldByEpochUseCase = getGenresSoldByEpochUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList = listAllProductsUseCase.execute()
                .stream()
                .map(product -> productDTOMapper.toProductResponseDTO(product))
                .toList();

        return new ResponseEntity<>(productResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/totalSold/category/year/{year}")
    public ResponseEntity<List<CategoryIncomeResponseDTO>> getCategoriesTotalSoldByYear(@PathVariable String year) {
        List<Object[]> categories = getCategoriesSoldByYearUseCase.execute(year);
        return new ResponseEntity<>(productDTOMapper.toResponseCategoryIncomeDTOList(categories), HttpStatus.OK);
    }

    @GetMapping("/totalSold/category/month/{month}")
    public ResponseEntity<List<CategoryIncomeResponseDTO>> getCategoriesTotalSoldByMonth(@PathVariable Integer month) {
        List<Object[]> categories = getCategoriesSoldByMonthUseCase.execute(month);
        return new ResponseEntity<>(productDTOMapper.toResponseCategoryIncomeDTOList(categories), HttpStatus.OK);
    }

    @GetMapping("/totalSold/category/epoch")
    public ResponseEntity<List<CategoryIncomeResponseDTO>> getCategoriesTotalSoldByEpoch(@RequestParam Integer month, @RequestParam String year) {
        List<Object[]> categories = getCategoriesSoldByEpochUseCase.execute(month, year);
        return new ResponseEntity<>(productDTOMapper.toResponseCategoryIncomeDTOList(categories), HttpStatus.OK);
    }

    @GetMapping("/totalSold/genre/year/{year}")
    public ResponseEntity<List<GenreIncomeResponseDTO>> getGenresTotalSoldByYear(@PathVariable String year) {
        List<Object[]> categories = getGenresSoldByYearUseCase.execute(year);
        return new ResponseEntity<>(productDTOMapper.toResponseGenreIncomeDTOList(categories), HttpStatus.OK);
    }

    @GetMapping("/totalSold/genre/month/{month}")
    public ResponseEntity<List<GenreIncomeResponseDTO>> getGenresTotalSoldByMonth(@PathVariable Integer month) {
        List<Object[]> categories = getGenresSoldByMonthUseCase.execute(month);
        return new ResponseEntity<>(productDTOMapper.toResponseGenreIncomeDTOList(categories), HttpStatus.OK);
    }

    @GetMapping("/totalSold/genre/epoch")
    public ResponseEntity<List<GenreIncomeResponseDTO>> getGenresTotalSoldByEpoch(@RequestParam Integer month, @RequestParam String year) {
        List<Object[]> categories = getGenresSoldByEpochUseCase.execute(month, year);
        return new ResponseEntity<>(productDTOMapper.toResponseGenreIncomeDTOList(categories), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws NonPositiveNumberException, RequiredValueException {
        Product product = createProductUseCase.execute(productDTOMapper.toProduct(productRequestDTO));
        return new ResponseEntity<>(productDTOMapper.toProductResponseDTO(product), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<ProductResponseDTO> editProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO) throws NonPositiveNumberException, RequiredValueException, ProductNotFoundException {
        Product product = productDTOMapper.toProduct(productRequestDTO);
        product.setProductId(productId);
        product = updateProductUseCase.execute(product);
        return new ResponseEntity<>(productDTOMapper.toProductResponseDTO(product), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {
        deleteProductUseCase.execute(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
