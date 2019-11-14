package com.itheima.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.UploadUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	//模型驱动使用的对象
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
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

	/*
	 * 文件上传的三个属性
	 */
	private String uploadFileName;
	private File upload;
	private String uploadContentType;
	
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	//保存客户
	public String save() throws IOException {
		//上传图片
		if (upload != null) {
			//上传文件
			//设置文件上传路径
			String path = "E:/upload";
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			String realPath = UploadUtils.getPath(uuidFileName);
			
			//创建目录
			String url = path+realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
		}  
		//保存客户
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
