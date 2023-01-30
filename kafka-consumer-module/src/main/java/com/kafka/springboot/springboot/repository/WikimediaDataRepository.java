package com.kafka.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.springboot.springboot.entity.WikimediaData;


public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
	

}
