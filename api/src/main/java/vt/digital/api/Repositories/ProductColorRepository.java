package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.ProductColor;

import java.util.List;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    List<ProductColor> findByProductProductId(Integer productId);
}