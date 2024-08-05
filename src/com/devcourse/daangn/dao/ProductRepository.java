package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    int saveProduct(ProductDTO productDTO, UserDTO userDTO) throws IOException; // 상품 등록
    int deleteById(int productId) throws IOException; // 상품 제거
    List<ProductDTO> findByLocation(String location) throws IOException; // 지역 별 상품 조회
    List<ProductDTO> findByLike(String userId) throws IOException; // 지역 별 상품 조회
}
