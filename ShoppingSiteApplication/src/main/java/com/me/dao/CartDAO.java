package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.CartException;
import com.me.pojo.Cart;
import com.me.pojo.User;

public class CartDAO extends DAO{

	
	public CartDAO(){
	
	}

	public Cart insert(Cart cart) throws CartException {
		try{
			begin();            
			getSession().save(cart);     
            commit();
            return cart;
		} catch (HibernateException e){
			rollback();
            throw new CartException("Could not save the cart", e);
		}
	}
	
	public void update(Cart cart) throws CartException {
        try {
            begin();
            getSession().update(cart);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CartException("Could not save the cart", e);
        }
    }
	
	public User update(User user) throws CartException {
        try {
            begin();
            getSession().update(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CartException("Could not save the user", e);
        }
        return user;
    }
	
	public List<Cart> list(){
		begin();
		Query q = getSession().createQuery("from Cart");
		List<Cart> cart1 = q.list();
		commit();
		return cart1;
	}
	
	public Cart updateCart(Cart cart) throws CartException {
        try {
            begin();
            getSession().update(cart);
            commit();
            return cart;
        } catch (HibernateException e) {
            rollback();
            throw new CartException("Could not save the cart", e);
        }
    }
	 public Cart findByUserId(long userID){
		begin();
		Query q = getSession().createQuery("from Cart where user_userID = :userID");
		Cart cart1 = (Cart) q.uniqueResult();
		commit();
		return cart1;
	}
	
	public List<Integer> findByCartId(long cartID){
		begin();
		Query q = getSession().createQuery("from cart_table_product_table where cartID = :cartID");
		List<Integer> prodIds = q.list();
		commit();
		return prodIds;
	}
	
	



}
