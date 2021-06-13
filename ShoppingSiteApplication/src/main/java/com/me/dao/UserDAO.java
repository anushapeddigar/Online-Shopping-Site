package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.me.pojo.User;
import com.me.dao.DAO;
import com.me.exception.UserException;


public class UserDAO extends DAO{

	public UserDAO() {
	}
//checkLogin
	public User get(String username, String password) throws UserException {
		try {
			begin();
			String hqlQuery = "from User where username = :username and password = :password";
			Query q = getSession().createQuery(hqlQuery);
			q.setString("username", username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID= :userID");
			q.setInteger("userID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}

	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			User user = new User(u.getUsername(), u.getPassword(),u.getEmail(), u.getUsertype());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}


}
