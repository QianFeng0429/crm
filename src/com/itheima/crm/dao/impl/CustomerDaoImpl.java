package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

//	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@Override
	//带条件统计个数
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size()>0) {
			return list.get(0).intValue();
 		}
		return null;
		
	}

	@Override
	//分页查询客户
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
		
	}

}
