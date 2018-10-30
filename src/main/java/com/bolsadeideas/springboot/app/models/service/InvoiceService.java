package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Invoice;

public interface InvoiceService {

	void save(Invoice invoice);
	void deleteInvoice(Long id);
	Invoice findInvoiceById(Long id);

	Invoice findInvoiceByIdWithClient(Long id);
}
