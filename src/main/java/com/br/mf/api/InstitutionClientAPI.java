package com.br.mf.api;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.br.mf.model.InstitutionClient;
@Component
public class InstitutionClientAPI {
	@Autowired
	KafkaTemplate<String, List<InstitutionClient>> template;
	@Autowired
	KafkaTemplate<String, List<String>> ktemplate;
	
	public String sendJsonMessageToKafka(List<InstitutionClient> data) {
	    final ProducerRecord<String, List<InstitutionClient>> record = createRecord1(data);

	    CompletableFuture<SendResult<String, List<InstitutionClient>>> future = template.send(record);
	    future.whenComplete((result, ex) -> {
	        if (ex == null) {
	             handleSuccess(data);
	        }
	        else {
	             handleFailure(data, record, ex);
	        }
	    });
		return "success";
	}

	public String sendSchemaMessageToKafka(List<String> data) {
	    final ProducerRecord<String, List<String>> record = createRecord2(data);

	    CompletableFuture<SendResult<String, List<String>>> future = ktemplate.send(record);
	    future.whenComplete((result, ex) -> {
	        if (ex == null) {
	            handleSuccess1(data);
	        }
	        else {
	            handleFailure1(data, record, ex);
	        }
	    });
	    return "success";
	}

	private ProducerRecord<String, List<InstitutionClient>> createRecord1(List<InstitutionClient> data) {
		// TODO Auto-generated method stub
		return new ProducerRecord("instclientid-feed-topic1","pe",data);	
	}
	
	private ProducerRecord<String, List<String>> createRecord2(List<String> data) {
		// TODO Auto-generated method stub
		return new ProducerRecord("instclientid-feed-topic2","pv",data);	
	}
	
	private String handleSuccess(List<InstitutionClient> data) {
		// TODO Auto-generated method stub
		System.out.println("successfully published event into topic");
		return "success";
	}
	private String handleSuccess1(List<String> data) {
		// TODO Auto-generated method stub
		System.out.println("successfully published event into topic");
		return "success";
	}
	private String handleFailure(List<InstitutionClient> data, ProducerRecord<String, List<InstitutionClient>> record,
			Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println("failed to publish event into topic");
		return "failure";
	}
	
	private String handleFailure1(List<String> data, ProducerRecord<String, List<String>> record,
			Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println("failed to publish event into topic");
		return "failure";
	}

	





}
