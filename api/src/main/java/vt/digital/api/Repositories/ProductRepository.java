package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String name);

    List<Product> findByProductTags_Tag_TagId(Integer tagId);

    List<Product> findByProductTags_Tag_TagIdIn(List<Integer> tagIds);
}