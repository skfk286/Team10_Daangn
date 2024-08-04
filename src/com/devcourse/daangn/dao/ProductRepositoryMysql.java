package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ProductRepositoryMysql implements ProductRepository{
    private ProductRepositoryMysql() {}

    private static ProductRepository instance = new ProductRepositoryMysql();

    public static ProductRepository getInstance() { return instance; }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int saveProduct(ProductDTO productDTO) throws IOException {
        /* TODO : 상품 등록 */
        return 0;
    }

    @Override
    public int deleteById() throws IOException {
        /* TODO : 상품 제거 */
        return 0;
    }

    @Override
    public List<ProductDTO> findAll() throws IOException {
        /* TODO : 제품 전체 정보 조회 */
        return List.of();
    }

    @Override
    public List<ProductDTO> findByCity(String city) throws IOException {
        /* TODO : 지역 별 상품 조회 */
        return List.of();
    }
}
