package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exception.NotFoundException;


@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO updateEvent) {
		
		try {
			Event entity = repository.getOne(id);
			copyDtoToEntity(updateEvent,entity);
			entity = repository.save(entity);
					
			return new EventDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new NotFoundException("not found"+ id);
		}

	}

	private void copyDtoToEntity(EventDTO updateEvent, Event entity) {
		entity.setCity(new City(updateEvent.getCityId(),null));
		entity.setDate(updateEvent.getDate());
		entity.setName(updateEvent.getName());
		entity.setUrl(updateEvent.getUrl());
		
	}
}
