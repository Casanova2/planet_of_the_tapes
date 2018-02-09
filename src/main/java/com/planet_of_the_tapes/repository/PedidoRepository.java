package com.planet_of_the_tapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.planet_of_the_tapes.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{


}