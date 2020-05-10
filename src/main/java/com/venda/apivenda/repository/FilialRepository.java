package com.venda.apivenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venda.apivenda.models.Filial;

public interface FilialRepository extends JpaRepository<Filial, Long>{

	Filial findById(long id);
}
