package com.stockflow.repository;

import com.stockflow.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
abstract class ProductRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Product> findByNameUnsafe(String name) {
        String query = "SELECT * FROM product WHERE name = '" + name + "'";
        return entityManager.createNativeQuery(query, Product.class).getResultList();
    }
}

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameUnsafe(String name);
}
