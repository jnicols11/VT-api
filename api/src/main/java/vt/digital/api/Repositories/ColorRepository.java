package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
