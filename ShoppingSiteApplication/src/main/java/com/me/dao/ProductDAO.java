package com.me.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.ProductException;
import com.me.pojo.Product;

public class ProductDAO extends DAO{

    public Product create(Product product)
            throws ProductException {
        try {
            begin();            
            getSession().save(product);     
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Exception while creating product: " + e.getMessage());
        }
    }

    public void delete(Product product)
            throws ProductException {
        try {
            begin();
            getSession().delete(product);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not delete product", e);
        }
    }
    
    public List<Product> list() throws ProductException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Product");
            List<Product> products = q.list();
            commit();
            return products;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not delete product", e);
        }
    	
    }


	public Product findProductById(long id) {
		// TODO Auto-generated method stub
		begin();
        Query q = getSession().createQuery("from Product where productId = :id");
        q.setLong("id", id);
        
        Product product = (Product)q.uniqueResult();
        commit();
        return product;
	}
	
	public List<Product> findProductsByUserID(long userID) {
		// TODO Auto-generated method stub
		begin();
        Query q = getSession().createQuery("from Product where user_userID = :userID");
        q.setLong("userID", userID);
        
        List<Product> products = q.list();
        
        commit();
        return products;
	}

}
