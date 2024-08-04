package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    int saveProduct(ProductDTO productDTO) throws IOException; // 상품 등록
    int deleteById() throws IOException; // 상품 제거
    List<ProductDTO> findAll() throws IOException; // 제품 전체 정보 조회
    List<ProductDTO> findByCity(String city) throws IOException; // 지역 별 상품 조회
}
