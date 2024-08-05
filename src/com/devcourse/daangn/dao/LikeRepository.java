package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;

import java.io.IOException;
import java.util.List;

public interface LikeRepository {
    int findByProduct(ProductDTO productDTO) throws IOException;
    int addLike(ProductDTO productDTO,UserDTO userDTO) throws IOException;
    int removeLike(ProductDTO productDTO,UserDTO userDTO) throws IOException;
//    List<ProductDTO> findByUserId(UserDTO userDTO) throws IOException;
}
