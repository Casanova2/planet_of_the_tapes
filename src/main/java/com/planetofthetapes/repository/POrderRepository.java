package com.planetofthetapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planetofthetapes.entity.POrder;

public interface POrderRepository extends JpaRepository<POrder, Integer>{

	
}