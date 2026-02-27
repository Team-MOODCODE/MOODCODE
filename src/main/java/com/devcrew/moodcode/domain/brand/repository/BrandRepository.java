package com.devcrew.moodcode.domain.brand.repository;

import com.devcrew.moodcode.domain.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {


}
