/*package com.br.mf.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.br.mf.model.InstitutionClient;
@Configuration
public class MainframeProducerConfig {
	
	@Bean
	public ProducerFactory<String, List<InstitutionClient>> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	    // See https://kafka.apache.org/documentation/#producerconfigs for more properties
	    return props;
	}

	@Bean
	public KafkaTemplate<String, List<InstitutionClient>> kafkaTemplate() {
	    return new KafkaTemplate<String, List<InstitutionClient>>(producerFactory());
	}

}
*/
