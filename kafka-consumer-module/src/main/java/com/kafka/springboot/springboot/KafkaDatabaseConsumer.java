package com.kafka.springboot.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.springboot.springboot.entity.WikimediaData;
import com.kafka.springboot.springboot.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	private WikimediaDataRepository wikimediaDataRepository;

	public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
		this.wikimediaDataRepository = wikimediaDataRepository;
	}

	@KafkaListener(topics = "topic_project",
					groupId = "myGroup")
	public void consume(String eventMessage) {
		LOGGER.info(String.format("Message received -> %s",  eventMessage));
		WikimediaData wikimediaData = new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);
		wikimediaDataRepository.save(wikimediaData);
		 
	}
}
