package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.ProductTag;

import java.util.List;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Integer> {
    List<ProductTag> findByProductProductId(Integer productId);
}
