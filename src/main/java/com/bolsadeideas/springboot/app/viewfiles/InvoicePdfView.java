package com.bolsadeideas.springboot.app.viewfiles;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Invoice;
import com.bolsadeideas.springboot.app.models.entity.ItemInvoice;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("invoice/ver")
public class InvoicePdfView extends AbstractPdfView {

	@Autowired
	private MessageSource meesageSource;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Invoice invoice = (Invoice) model.get("invoice");

		Locale locale = localeResolver.resolveLocale(request);

		MessageSourceAccessor message = getMessageSourceAccessor();

		PdfPTable table = new PdfPTable(1);
		table.setSpacingAfter(20);

		PdfPCell cell = new PdfPCell(
				Phrase.getInstance(meesageSource.getMessage("text.factura.ver.datos.cliente", null, locale)));
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(8f);

		table.addCell(cell);
		table.addCell(invoice.getClient().nameComplete().concat("\t"));
		table.addCell(invoice.getClient().getEmail());

		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);

		cell = new PdfPCell(
				Phrase.getInstance(meesageSource.getMessage("text.factura.ver.datos.factura", null, locale)));
		cell.setBackgroundColor(Color.CYAN);
		cell.setPadding(8f);
		table2.addCell(cell);

		table2.addCell(message.getMessage("text.cliente.factura.folio") + " : " + invoice.getId());
		table2.addCell(message.getMessage("text.cliente.factura.descripcion") + " : " + invoice.getDescription());
		table2.addCell(message.getMessage("text.cliente.factura.fecha") + " : " + invoice.getCreateDate());

		PdfPTable table3 = new PdfPTable(4);
		table3.setWidths(new float[] { 2.5f, 1f, 1f, 1f });
		table3.addCell(message.getMessage("text.factura.form.item.nombre"));
		table3.addCell(message.getMessage("text.factura.form.item.precio"));
		table3.addCell(message.getMessage("text.factura.form.item.cantidad"));
		table3.addCell(message.getMessage("text.factura.form.item.total"));

		for (ItemInvoice item : invoice.getItems()) {
			table3.addCell(item.getProduct().getName());
			table3.addCell(item.getProduct().getPrice().toString());

			cell = new PdfPCell(new Phrase(item.getAmount().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table3.addCell(cell);
			table3.addCell(item.calculateAmount().toString());

		}

		cell = new PdfPCell(new Phrase(message.getMessage("text.factura.form.total") + " :	"));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table3.addCell(cell);
		table3.addCell(invoice.getTotal().toString());

		addAll(document, table, table2, table3);
	}

	private void addAll(Document document, PdfPTable... pdfPTables) throws DocumentException {

		for (int i = 0; i < pdfPTables.length; i++) {
			document.add(pdfPTables[i]);
		}
	}
}
