package com.rabobank.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabobank.beans.CustomerStatement;
import com.rabobank.controller.CustomerStatementController;
/*
 * Common validate class
 */
public class StatementValidator {
	
	private final Logger log = LoggerFactory.getLogger(CustomerStatementController.class);
	
	/*This method takes total list as input and validate all records against Endbalance 
	 * and return the list of invalid end balance records
	 */
	public List<CustomerStatement> validateEndBalance(List<CustomerStatement> list){
		
		DecimalFormat df = new DecimalFormat("####0.00");
		List<CustomerStatement> invalidEndBalanceList = new ArrayList<CustomerStatement>();
		for(CustomerStatement ct :list) {
			if(df.format(ct.getEndBalance()).equals(df.format((ct.getStartBalance() + ct.getMutation())))) {
				continue;
			}else {
				invalidEndBalanceList.add(ct);
			}
		}
		log.debug("list of invalid EndBalance records: {}",invalidEndBalanceList.size());
		return invalidEndBalanceList;
	}
	/*
	 * Separate duplicate records which has more than one occurrence of txn ref and kep it in map as 
	 * transaction ref as key 
	 */
	public List<CustomerStatement> countRecords(List<CustomerStatement> list){
		
		List<CustomerStatement> failedTransactions = new ArrayList<CustomerStatement>() ;
		List<CustomerStatement> tempList ;
		Map<Integer,List<CustomerStatement>> custmap = new HashMap<Integer,List<CustomerStatement>>();
		for(CustomerStatement ct :list) {
			if(custmap.get(ct.getTransactionReference())!= null) {
				tempList = custmap.get(ct.getTransactionReference());
				tempList.add(ct);
			}else {
				tempList = new ArrayList<CustomerStatement>();
				tempList.add(ct);
				custmap.put(ct.getTransactionReference(), tempList);
			}
		}
		log.debug("customer records map size:{}",custmap.size());
		for(Map.Entry<Integer, List<CustomerStatement>> entry : custmap.entrySet()) {
			Integer i = entry.getKey();
			if(custmap.get(i) !=null && custmap.get(i).size()>1) {
				failedTransactions.addAll(custmap.get(i));
			}
		}
		log.info("failedTransactions list :{}",failedTransactions.size());
		return failedTransactions;
	}

	/*
	 * Buy using set removing duplicate records based on Transaction reference number
	 */
	/*	public List<CustomerStatement> validateUniqueTransactions(List<CustomerStatement> list){
		Set set = new TreeSet(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if(((CustomerStatement) o1).getTransactionReference().equals(((CustomerStatement) o2).getTransactionReference())) {
					return 1;
				}
				return 0;
			}
		}
		);
		set.addAll(list);
		System.out.println("set:"+set.size());
		System.out.println("set:"+set);
		List<CustomerStatement> newList = new ArrayList<CustomerStatement>();
		for(CustomerStatement st : list) {
			if(set.add(st) == false) {
				newList.add(st);
			}
		}
		System.out.println("newList:"+newList.size());// this returns unique records including one of the duplicate record 
		return newList;
	}*/
}
