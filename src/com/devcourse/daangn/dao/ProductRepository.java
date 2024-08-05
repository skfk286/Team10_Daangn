package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    int saveProduct(ProductDTO productDTO, UserDTO userDTO) throws IOException; // 상품 등록
    int deleteProductById(int productId) throws IOException; // 상품 제거
    List<ProductDTO> findProductByLocation(String location) throws IOException; // 지역 별 상품 조회
    List<ProductDTO> findProductByLike(String userId) throws IOException; // 지역 별 상품 조회
    ProductDTO findProductByProductId(int productId)throws IOException; // 상품 Id로 상세 조회
}
