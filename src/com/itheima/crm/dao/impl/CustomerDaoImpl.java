package com.itheima.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

//	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

}
