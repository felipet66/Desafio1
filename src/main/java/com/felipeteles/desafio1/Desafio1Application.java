package com.felipeteles.desafio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipeteles.desafio1.domain.Login;
import com.felipeteles.desafio1.repositories.LoginRepository;

@SpringBootApplication
public class Desafio1Application implements CommandLineRunner{
	
	@Autowired
	private LoginRepository loginRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Desafio1Application.class, args);
	}
	
	public void run(String... args) throws Exception {
		
		String csv = "C:/dados.csv";
		BufferedReader conteudoCSV = null;
		String linha = "";
		String divisor = ";";
		
		try {
			conteudoCSV = new BufferedReader(new FileReader(csv));
			
			while((linha = conteudoCSV.readLine()) != null) {
				String[] login = linha.split(divisor);
				Login importExcel = new Login(null,login[0],login[1],login[2]);
				loginRepository.saveAll(Arrays.asList(importExcel));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
