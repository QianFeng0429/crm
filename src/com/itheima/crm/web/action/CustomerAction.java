package com.itheima.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	//模型驱动使用的对象
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	//注入service
	private CustomerService customerService;
	private Integer currPage=1;
	private Integer pageSize=3;
	
	public void setPageSize(Integer pageSize) {
		if (pageSize==null) {
			pageSize=3;
		}
		this.pageSize = pageSize;
	}

	public void setCurrPage(Integer currPage) {
		if (currPage==null) {
			currPage=1;
		}
		this.currPage = currPage;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//跳转到添加页面
	public String saveUI() {
		return "saveUI";
	}

	
	//保存客户
	public String save() {
		customerService.save(customer);
		return NONE;
	}
	
	//查询方法
	public String findAll() {
		//接受参数  分页参数
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAll";
	}

}
