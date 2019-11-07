package com.itheima.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;


/**
 * 用户管理的DAO的实现类
 * @author jt
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	// DAO中保存用户的方法
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	//根据用户名和密码进行查询
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", 
				user.getUser_code(),user.getUser_password());
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
