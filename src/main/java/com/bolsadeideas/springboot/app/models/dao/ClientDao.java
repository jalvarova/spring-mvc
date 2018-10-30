package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Client;

public interface ClientDao extends PagingAndSortingRepository<Client, Long> {

	@Query("select c from Client c join fetch c.invoices i where c.id =?1")
	Client fetchByIdWidthInvoice(Long id);
}
