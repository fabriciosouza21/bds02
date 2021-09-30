package com.devsuperior.bds02.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;


@RestController
@RequestMapping(value = "/cities")
public class CityResource {
	
	@Autowired
	CityService service;
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> cities = service.findAll();
		return ResponseEntity.ok().body(cities);	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
		
	}
}
