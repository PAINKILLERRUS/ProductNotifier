package by.developing.example.productmmcroservice.service;

import by.developing.example.productmmcroservice.service.dto.CreateProductDTO;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(CreateProductDTO dto) throws ExecutionException, InterruptedException;
}
