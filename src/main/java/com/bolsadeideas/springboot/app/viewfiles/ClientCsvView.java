package com.bolsadeideas.springboot.app.viewfiles;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.bolsadeideas.springboot.app.models.entity.Client;

@Component("listar.csv")
public class ClientCsvView extends AbstractView {

	public ClientCsvView() {

		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		getMessageSourceAccessor();
		response.setHeader("Content-Disposition", "attachment; filename=\"clients.csv\"");
		response.setContentType(getContentType());

		
		Page<Client> clientes = (Page<Client>) model.get("clients");

		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "id", "name", "lastName", "email", "createDate" };
		beanWriter.writeHeader(header);

		for (Client cliente : clientes) {
			beanWriter.write(cliente, header);
		}

		beanWriter.close();

	}

}
