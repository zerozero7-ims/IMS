package com.ims.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ims.entity.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDAOTest {
	private Logger logger = LoggerFactory.getLogger(IUserDAOTest.class);
	
	@Autowired
	private IUserDAO userDAO;

	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("aaa");
		user.setPassword("111");
		this.userDAO.insert(user);
		logger.info("insert the user={}",user);

	}

	@Test
	public void testDelete() {
		this.userDAO.delete(4);
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(6);
		user.setUsername("abcd");
		user.setPassword("2222");
		logger.info("insert the user={}",user);
		this.userDAO.update(user);
		
	}

	@Test
	public void testFindById() {
		User u = this.userDAO.findById(1);
		logger.info("after delete the user={}", u);

	}

	@Test
	public void testFindAll() {
		List<User> users = this.userDAO.findAll();
        logger.info("all the users={}", users);
	}
	
	
	@Test
	public void testQuery(){
		String sql="SELECT * FROM user LIMIT 4";
		List<User> users = this.userDAO.query(sql);
		 logger.info("all the users={}", users);
	}

}
