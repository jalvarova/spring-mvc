package com.bolsadeideas.springboot.app.viewfiles;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bolsadeideas.springboot.app.models.entity.Client;

@Component("listar.json")
public class ClientJsonView extends MappingJackson2JsonView{

@SuppressWarnings("unchecked")
@Override
protected Object filterModel(Map<String, Object> model) {
	
	model.remove("titulo");
	model.remove("page");

	Page<Client> clientes = (Page<Client>) model.get("clients");
	model.remove("client");
	
	model.put("clients", clientes.getContent());
	return super.filterModel(model);
}
}
