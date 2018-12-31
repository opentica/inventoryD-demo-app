package com.opentica.inventory.app.bean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the products
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, Long> {

}
