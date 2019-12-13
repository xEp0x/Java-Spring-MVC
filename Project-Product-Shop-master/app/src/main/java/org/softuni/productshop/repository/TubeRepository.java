package org.softuni.productshop.repository;

import org.softuni.productshop.domain.entities.Tube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TubeRepository extends JpaRepository<Tube, String> {
}
