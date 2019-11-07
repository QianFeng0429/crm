package com.itheima.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>{
	private BaseDict baseDict = new BaseDict();
	@Override
	public BaseDict getModel() {
		// TODO Auto-generated method stub
		return baseDict;
	}

	//注入Service
	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	public String findByTypeCode() throws IOException {
		System.out.println("方法执行了...");
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"dict_enable","dict_memo","dict_sort"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		System.out.println(jsonArray.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
}
