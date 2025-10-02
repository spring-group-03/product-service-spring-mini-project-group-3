package com.hrd.productservice.repository;

import com.hrd.productservice.model.enitity.Product;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByUserId(UUID userId, PageRequest pageRequest);
    Optional<Product> findByProductIdAndUserId(UUID productId, UUID userId);
    Optional<List<Product>> findAllByCategoryIdAndUserId(UUID categoryId, UUID userId);
    Boolean existsByName(String name);
}
