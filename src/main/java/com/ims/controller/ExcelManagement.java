package com.ims.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ims.dto.ExcelInputOutputTO;
import com.ims.excel.ExcelInputOutput;
import com.ims.excel.ExcelOperation;

@Controller
@RequestMapping(value = "/excel")
public class ExcelManagement {
	
	@Autowired
	private HttpServletResponse res;

	@RequestMapping(value = "/go", method = RequestMethod.GET)
	public ModelAndView landingPage(Model model) {
		model.addAttribute("excelInputOutputTO", new ExcelInputOutputTO());
		return new ModelAndView("excel/index");
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView addUser(
			Model model,
			ExcelInputOutputTO excelInputOutputTO,
			BindingResult bindingResult,
			@RequestParam(value = "inputFileNameII", required = false) MultipartFile inputFileNameII,
			@RequestParam(value = "randomizationSchFileName", required = false) MultipartFile randomizationSchFileName,
			HttpServletResponse response) {

		ExcelInputOutput excelInputOutput = new ExcelInputOutput();
		if (!inputFileNameII.isEmpty()) {
			try {

				excelInputOutput.setOutputFileNameII(excelInputOutputTO
						.getOutputFileNameII() + ".xlsx");
				ExcelOperation operation = new ExcelOperation();
				operation.readRandomization(excelInputOutput,
						randomizationSchFileName.getBytes());
				operation.readExcel(excelInputOutput,
						inputFileNameII.getBytes());

				response.setHeader("Content-Disposition", "inline; filename="
						+ excelInputOutput.getOutputFileNameII());
				// Make sure to set the correct content type
				response.setContentType("application/vnd.ms-excel");
				ServletOutputStream outputStream = response.getOutputStream();
				operation.writeExcel(excelInputOutput, outputStream);

			} catch (RuntimeException | IOException re) {
				bindingResult.reject(re.getMessage());
			}
		}

		return null;
	}
	
}
