package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Invoice;

@Repository
public interface InvoiceDao extends CrudRepository<Invoice, Long> {

}
