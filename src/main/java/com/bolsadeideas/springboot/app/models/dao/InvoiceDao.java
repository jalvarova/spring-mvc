package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Invoice;

@Repository
public interface InvoiceDao extends CrudRepository<Invoice, Long> {

	@Query("select f from Invoice f join fetch f.client c join fetch f.items i join fetch i.product where f.id =?1")
	Invoice findInvoiceByIdWithClient(Long id);
}
