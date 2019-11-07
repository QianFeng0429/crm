package com.itheima.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	//注入DAO
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

//	@Override
	//保存客户
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
}
