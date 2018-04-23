package com.felipeteles.desafio1.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeteles.desafio1.domain.Login;
import com.felipeteles.desafio1.dto.LoginDTO;
import com.felipeteles.desafio1.repositories.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;
	
	public Login find(Integer id) {
		Optional<Login> obj = loginRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Login.class.getName(), null));
	}
	
	@Transactional
	public Login insert(Login obj) {
		obj.setId(null);
		return loginRepository.save(obj);
	}
	
	public List<Login> findAll(){
		return loginRepository.findAll();
	}

	public Login fromDTO(LoginDTO objDto) {
		return new Login(objDto.getId(), objDto.getfName(), objDto.getlName() , objDto.getEmail());
		
	}
	
}
