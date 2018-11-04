package com.bolsadeideas.springboot.app.viewfiles;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.viewfiles.model.ClientList;

@Component("listar.xml")
public class ClientXmlView extends MarshallingView {

	@Autowired
	public ClientXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		model.remove("titulo");
		model.remove("page");

		Page<Client> clientes = (Page<Client>) model.get("clients");

		model.put("clientList", new ClientList(clientes.getContent()));
		model.remove("client");

		super.renderMergedOutputModel(model, request, response);
	}

}
