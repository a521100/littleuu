package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import cn.web.model.Product;
import cn.web.utils.JdbcUtils;

public class ProductDaoImpl {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product = new Product();
		product.setName("嗖嗖嗖");
		product.setPrice(123.99);
		//daoImpl.save(product);
		//System.out.println(product);
		daoImpl.delete(4);
	}
	
	public int delete(int id) {
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement("delete from product where id = ?");
			pre.setInt(1, id);
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
		
	}
	
	public int save(Product product) {
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement("insert into product (name,price,remark) values (?,?,?)");
			pre.setString(1, product.getName());
			pre.setDouble(2, product.getPrice());
			pre.setString(3, product.getRemark());
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
		
	}
}
