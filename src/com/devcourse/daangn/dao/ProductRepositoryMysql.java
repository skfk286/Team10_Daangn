package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.util.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryMysql implements ProductRepository {
    private ProductRepositoryMysql() {
    }

    private static final ProductRepository instance = new ProductRepositoryMysql();

    public static ProductRepository getInstance() {
        return instance;
    }
    private final Connection conn = DBUtil.getConnection();

    @Override
    public int saveProduct(ProductDTO productDTO) throws IOException {
        String sql = "INSERT INTO daangn.tb_product (product_id, user_id, title, location, content) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productDTO.getProductId());
            ps.setInt(2, productDTO.getUserId());
            ps.setString(3, productDTO.getTitle());
            ps.setString(4, productDTO.getLocation());
            ps.setString(5, productDTO.getContent());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new IOException("Error saving product", e);
        }
    }

    @Override
    public int deleteById(int productId) throws IOException {
        String sql = "DELETE FROM daangn.tb_product WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new IOException("Error deleting product", e);
        }
    }

    @Override
    public List<ProductDTO> findByCity(String location) throws IOException {
        String sql = "SELECT * FROM daangn.tb_product WHERE location = ?";
        List<ProductDTO> products = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, location);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductDTO product = new ProductDTO();
                    product.setProductId(rs.getInt("product_id"));
                    product.setUserId(rs.getInt("user_id"));
                    product.setTitle(rs.getString("title"));
                    product.setLocation(rs.getString("location"));
                    product.setContent(rs.getString("content"));

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new IOException("Error finding products by city", e);
        }
        return products;
    }
}
