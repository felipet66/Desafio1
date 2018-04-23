package com.felipeteles.desafio1.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipeteles.desafio1.domain.Login;
import com.felipeteles.desafio1.dto.LoginDTO;
import com.felipeteles.desafio1.service.LoginService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/logins")
public class LoginResource {
	
	@Autowired
	private LoginService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Login> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Login obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody LoginDTO objDto){
		Login obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LoginDTO>> findAll() {
		List<Login> list = service.findAll();
		List<LoginDTO> listDto = list.stream().map(obj -> new LoginDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
