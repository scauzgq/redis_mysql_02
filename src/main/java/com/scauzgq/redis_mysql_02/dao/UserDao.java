package com.scauzgq.redis_mysql_02.dao;



import com.scauzgq.redis_mysql_02.beans.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

	public abstract User selectUserByUserName(String userName);
	public abstract void updateUser(User user);

}


