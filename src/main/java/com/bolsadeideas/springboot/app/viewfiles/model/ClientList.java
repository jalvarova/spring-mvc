package com.bolsadeideas.springboot.app.viewfiles.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bolsadeideas.springboot.app.models.entity.Client;

import lombok.Getter;

@Getter
@XmlRootElement(name="Clients")
public class ClientList {

	@XmlElement(name="Client")
	private List<Client> clients;

	
	public ClientList() {
	}


	public ClientList(List<Client> clients) {
		this.clients = clients;
	}

}
