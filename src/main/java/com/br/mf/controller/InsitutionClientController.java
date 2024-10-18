package com.br.mf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.mf.model.InstitutionClient;
import com.br.mf.service.InstutionClientService;

@RestController
public class InsitutionClientController {
	
	@Autowired
	InstutionClientService instClientService;
	
	@GetMapping(value = "/sendInstutionClientFeed1")
	public String sendInstutionClient1() {
		System.out.println("cintroller method start execution");
		String instfeed="src/main/resources/instclient.dat";
		String instctl="src/main/resources/instclient.ctl";
		return instClientService.sendInstutionalClient1(instfeed,instctl);	
	}
	@GetMapping(value="/sendInstitutionClientFeed2")
	public String sendInstutionClient2() {
		System.out.println("cintroller method start execution");
		String instfeed="src/main/resources/instclient.dat";
		String instctl="src/main/resources/instclient.ctl";
		return instClientService.sendInstutionalClient2(instfeed,instctl);	
	}
}
