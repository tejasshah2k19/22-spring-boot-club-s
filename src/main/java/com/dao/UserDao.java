package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertUser(UserBean user) {
		stmt.update("insert into users (firstname,email,password) values (?,?,?) ", user.getFirstName(),
				user.getEmail(), user.getPassword());
	}

	public UserBean getUserByEmail(String email) {
		UserBean user = null;
		try {
			user = stmt.queryForObject("select * from users where email = ? ",
					new BeanPropertyRowMapper<UserBean>(UserBean.class), new Object[] { email });
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
}
