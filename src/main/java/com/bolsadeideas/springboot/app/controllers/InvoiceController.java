package com.bolsadeideas.springboot.app.controllers;

import static com.bolsadeideas.springboot.app.constants.ConstControllers.REDIRECT;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.constants.ConstControllers;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Invoice;
import com.bolsadeideas.springboot.app.models.entity.ItemInvoice;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.ClientService;
import com.bolsadeideas.springboot.app.models.service.InvoiceService;
import com.bolsadeideas.springboot.app.models.service.ProductService;
import com.bolsadeideas.springboot.app.util.objects.ObjectsUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
@Slf4j
public class InvoiceController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/form/{id}")
	public String create(@PathVariable(value = "id") Long clientId, Map<String, Object> model,
			RedirectAttributes flash) {

		Client client = clientService.findOne(clientId);

		if (ObjectsUtils.isEmpty(client)) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return REDIRECT;
		}

		Invoice invoice = new Invoice();
		invoice.setClient(client);

		model.put("invoice", invoice);
		model.put("titulo", "Crear factura");

		return "invoice/form";
	}

	@GetMapping("/load-products/{name}")
	public @ResponseBody List<Product> findName(@PathVariable String name, Map<String, Object> model) {

		List<Product> products = productService.findByName(name);
		return ResponseEntity.ok(products).getBody();
	}

	@PostMapping("/form")
	public String guardar(@Valid Invoice invoice, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] id,
			@RequestParam(name = "cantidad[]", required = false) Integer[] amount, RedirectAttributes flash,
			SessionStatus sessionStatus) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "invoice/form";

		}

		if (ObjectsUtils.isEmptyNumber(id)) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "La factura debe tener productos asociados");
			return ConstControllers.REDIRECT_INVOICE;
		}

		for (int i = 0; i < id.length; i++) {
			Product product = productService.findProductById(id[i]);

			ItemInvoice item = new ItemInvoice();

			item.setAmount(amount[i]);
			item.setProduct(product);

			invoice.addItemInvoice(item);

			log.debug("Id: " + id[i].toString() + ", cantidad: " + amount[i].toString());
		}
		invoiceService.save(invoice);
		sessionStatus.setComplete();

		flash.addFlashAttribute("success", "Factura Creada con exito");
		return "redirect:/ver/" + invoice.getClient().getId();
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id,Model model, RedirectAttributes flash) {
		
		Invoice invoice = invoiceService.findInvoiceByIdWithClient(id);
		
		if (ObjectsUtils.isEmpty(invoice)) {
			flash.addFlashAttribute("error","Error al encontrar la factura");
			return REDIRECT;
		}
		
		model.addAttribute("invoice", invoice);
		model.addAttribute("titulo", "Factura : ".concat(invoice.getDescription()));
		return "invoice/ver";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Invoice invoice = invoiceService.findInvoiceById(id);

		if (ObjectsUtils.isNotEmpty(invoice)) {
			invoiceService.deleteInvoice(id);
			flash.addFlashAttribute("success", "Factura eliminada con éxito!");
			return "redirect:/ver/" + invoice.getClient().getId();
		}
		flash.addFlashAttribute("error", "La factura no ha sido encontrada. No se pudo eliminar!");

		return REDIRECT;
	}

}
