package com.kafka.springboot;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.shaded.com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.shaded.com.launchdarkly.eventsource.EventSource;

@Service
public class ChangesProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChangesProducer.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	public ChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	public static final String WIKIMEDIA_URL = "https://stream.wikimedia.org/v2/stream/recentchange";
	public void sendMessage() throws InterruptedException {
		String topic = "topic_project";
		// to read real time stream data from source, we use event source
		EventHandler eventHandler = new ChangesHandler(kafkaTemplate, topic);
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(WIKIMEDIA_URL));
		EventSource eventSource = builder.build();
		eventSource.start();
		TimeUnit.MINUTES.sleep(10);
		
	}
}
