package com.erpnext.oa.act.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.flowable.bpmn.model.BpmnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.service.exception.InternalServerErrorException;
import com.erpnext.oa.act.domain.AbstractModel;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.service.ModelService;

@RestController
@RequestMapping(value = "/oa/act/app")
public class ModelBpmnController {
	
	@Autowired
	private ModelService modelService;
	
	@GetMapping("/rest/models/{processModelId}/bpmn20")
	public void getProcessModelBpmn20Xml(HttpServletResponse response, @PathVariable String processModelId)
			throws IOException {
		Model model = modelService.getModel(processModelId);
		try {
			generateBpmn20Xml(response, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateBpmn20Xml(HttpServletResponse response, AbstractModel model) throws Exception {
		String name = model.getName().replaceAll(" ", "_");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(name, "UTF-8") + ".bpmn20.xml");
		if (model.getModelEditorJson() != null) {
			try {
				ServletOutputStream servletOutputStream = response.getOutputStream();
				response.setContentType("application/xml");

				BpmnModel bpmnModel = modelService.getBpmnModel(model);
				byte[] xmlBytes = modelService.getBpmnXML(bpmnModel);
				BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(xmlBytes));

				byte[] buffer = new byte[8096];
				while (true) {
					int count = in.read(buffer);
					if (count == -1) {
						break;
					}
					servletOutputStream.write(buffer, 0, count);
				}

				// Flush and close stream
				servletOutputStream.flush();
				servletOutputStream.close();

			} catch (Exception e) {
				//log.error("Could not generate BPMN 2.0 XML", e);
				throw new InternalServerErrorException("Could not generate BPMN 2.0 xml");
			}
		}
	}

}
