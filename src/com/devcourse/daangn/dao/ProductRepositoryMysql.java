package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;
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

    @Override
    public int saveProduct(ProductDTO productDTO, UserDTO userDTO) throws IOException {
        String sql = "INSERT INTO daangn.tb_product (user_id, title, content) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userDTO.getUserId());
            ps.setString(2, productDTO.getTitle());
            ps.setString(3, productDTO.getContent());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new IOException("Error saving product", e);
        }
    }

    @Override
    public int deleteProductById(int productId) throws IOException {
        String sql = "DELETE FROM daangn.tb_product WHERE product_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new IOException("Error deleting product", e);
        }
    }

    @Override
    public List<ProductDTO> findProductByLocation(String location) throws IOException {
        String sql =
                "SELECT p.* " +
                "FROM daangn.tb_product p " +
                "JOIN daangn.tb_user u ON p.user_id = u.user_id " +
                "WHERE u.location = ?";

        List<ProductDTO> products = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, location);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductDTO product = new ProductDTO();
                    product.setProductId(rs.getInt("product_id"));
                    product.setUserId(rs.getInt("user_id"));
                    product.setTitle(rs.getString("title"));
                    product.setContent(rs.getString("content"));

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new IOException("Error finding products by location", e);
        }
        return products;
    }

    @Override
    public List<ProductDTO> findProductByLike(String userId) throws IOException {
        String sql =
                "SELECT p.* " +
                        "FROM daangn.tb_product p " +
                        "JOIN daangn.tb_like l ON p.product_id = l.product_id " +
                        "WHERE l.user_id = ?";

        List<ProductDTO> products = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductDTO product = new ProductDTO();
                    product.setProductId(rs.getInt("product_id"));
                    product.setUserId(rs.getInt("user_id"));
                    product.setTitle(rs.getString("title"));
                    product.setContent(rs.getString("content"));

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new IOException("Error finding products by like", e);
        }
        return products;
    }

    @Override
    public ProductDTO findProductByProductId(int productId) throws IOException {
        String sql = "SELECT * FROM daangn.tb_product WHERE product_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductDTO product = new ProductDTO();
                    product.setProductId(rs.getInt("product_id"));
                    product.setUserId(rs.getInt("user_id"));
                    product.setTitle(rs.getString("title"));
                    product.setContent(rs.getString("content"));

                    return product;
                }
            }
        } catch (SQLException e) {
            throw new IOException("Error finding product by productId", e);
        }
        return null; // 제품을 찾지 못한 경우 null 반환
    }
}
