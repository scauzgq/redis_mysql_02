package com.scauzgq.redis_mysql_02.services;

import com.scauzgq.redis_mysql_02.beans.User;
import com.scauzgq.redis_mysql_02.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User selectUserByUserName(String userName){
		return userDao.selectUserByUserName(userName);
	}

	public void updateUser(User user){
		userDao.updateUser(user);
	}


}
