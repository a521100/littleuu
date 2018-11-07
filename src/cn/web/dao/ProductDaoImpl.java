package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.web.model.Product;
import cn.web.utils.JdbcUtils;

public class ProductDaoImpl extends BaseDao{

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		daoImpl.delete(5);
		daoImpl.query(1);
	}
	
	public int update(Product product) {
		String sql = "update product set name = ? ,price = ? ,remark = ? where id = ?";
		return super.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}
	
	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.update(sql, new Object[] { id });
	}
	
	public int save(Product product) {
		String sql="insert into product (name,price,remark) values (?,?,?)";
		return super.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
	
	public int query(int id) {
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement("select * from demo where id = ?");
			// ?的下标是从1开始
			pre.setInt(1, id);
			// 2: 插入操作，返回受影响行数
			Product product = new Product();
			product.getId();
			product.getPrice();
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
