package com.felipeteles.desafio1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeteles.desafio1.domain.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

}
