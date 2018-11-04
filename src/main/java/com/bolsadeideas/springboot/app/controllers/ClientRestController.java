package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.service.ClientService;

@RestController
@RequestMapping("api/client")
public class ClientRestController {
	@Autowired
	private ClientService clienteService;

	@GetMapping(value = "/listar-rest")
	public List<Client> listarRest() {
		return clienteService.findAll();
	}

}
