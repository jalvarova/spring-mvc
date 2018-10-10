package com.bolsadeideas.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.bolsadeideas.springboot.app.constants.ConstControllers.REDIRECT;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Invoice;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.ClientService;
import com.bolsadeideas.springboot.app.models.service.ProductService;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientService clientService;

	@GetMapping("/form/{id}")
	public String create(@PathVariable(value = "id") Long clientId, Map<String, Object> model,
			RedirectAttributes flash) {

		Client client = clientService.findOne(clientId);

		if (client == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return REDIRECT;
		}

		Invoice invoice = new Invoice();
		invoice.setClient(client);

		model.put("invoice", invoice);
		model.put("titulo", "Crear factura");
		
		return "/invoice/form";
	}

	@GetMapping("/load-products/{name}")
	public @ResponseBody List<Product> findName(@PathVariable String name,Map<String, Object> model) {
		
		List<Product> products = productService.findByName(name);
		return ResponseEntity
				.ok(products)
				.getBody();
	}
}
