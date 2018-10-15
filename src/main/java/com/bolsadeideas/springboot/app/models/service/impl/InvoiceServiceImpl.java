package com.bolsadeideas.springboot.app.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.InvoiceDao;
import com.bolsadeideas.springboot.app.models.entity.Invoice;
import com.bolsadeideas.springboot.app.models.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public void save(Invoice invoice) {
		invoiceDao.save(invoice);
		
	}
	@Override
	@Transactional(readOnly=true)
	public Invoice findInvoiceById(Long id) {
		return invoiceDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void deleteInvoice(Long id) {
		invoiceDao.deleteById(id);
		
	}
	
	
	
}
