package com.rabobank.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.beans.CustomerStatement;
import com.rabobank.utils.StatementValidator;

@Controller
public class CustomerStatementController {
	
	private final Logger log = LoggerFactory.getLogger(CustomerStatementController.class);
	
	@RequestMapping("/index")
	public String indexPage() {
		log.debug("index page loaded");
		return "index";
	}
	
	@RequestMapping(value="/importexcel", method=RequestMethod.POST)
	public String readExcel(@RequestParam("file") MultipartFile file, Model model) {
		log.debug("importexcel called");
		if(file.isEmpty()) {
			return "index";
		}
		log.debug("Imported csv file is not empty called");
		try {
			InputStream in = file.getInputStream();
			List<CustomerStatement> customerStatementList = new ArrayList<CustomerStatement>();
			
			/* Create the list by reading the the excel */
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
			CSVParser parser = new CSVParser(br, format);
			
			for(CSVRecord record : parser) {
				CustomerStatement customer = new CustomerStatement();
				customer.setTransactionReference((Integer.parseInt(record.get("Reference"))));
				customer.setAccountNumber(record.get("AccountNumber"));
				customer.setDescription(record.get("Description"));
				customer.setStartBalance(Double.parseDouble(record.get("Start Balance")));
				customer.setMutation(Double.parseDouble(record.get("Mutation")));
				customer.setEndBalance(Double.parseDouble(record.get("End Balance")));
				customerStatementList.add(customer);
			}
			parser.close();
			log.info("Total records size :{}",customerStatementList.size());
			log.info("Total records: {}",customerStatementList);
			StatementValidator validator = new StatementValidator();
			List<CustomerStatement> newList = validator.countRecords(customerStatementList);
			newList.addAll(validator.validateEndBalance(customerStatementList));
			log.info("Validation failed records size :{}",newList.size());
			log.info("Validation failed records :{}",newList);
			model.addAttribute("failedTxnList", newList);
			
		} catch (FileNotFoundException fe) {
			log.error("Exception while reading file ",fe);
		}catch (Exception e) {
			log.error("Exception in CustomerStatementController",e);
		}
		return "result";
	}
}
