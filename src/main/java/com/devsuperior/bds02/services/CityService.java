package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exception.DatabaseException;
import com.devsuperior.bds02.services.exception.NotFoundException;




@Service
public class CityService {
	@Autowired
	CityRepository repository;
	
	public List<CityDTO> findAll(){
		List<City> categories = repository.findAll(Sort.by("name"));		
		return categories.stream().map( x -> new CityDTO(x)).collect(Collectors.toList());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		}
		catch(EmptyResultDataAccessException e) {
			throw new NotFoundException("not found id :"+ id);			
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("data integrity violation");
		}
		
	}
	
}
