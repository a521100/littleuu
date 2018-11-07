package cn.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.web.model.Product;

public class ProductDaoImpl extends BaseDao<Product> {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		// Product product = new Product();
		// product.setName("华为手机");
		// product.setPrice(5999.99);
		// product.setRemark("测试一下!!");
		// product.setId(4);
		// daoImpl.save(product);
		// daoImpl.delete(5);
		// Product product = daoImpl.getById(2);
		// System.out.println(product);
		List<Product> proList = daoImpl.queryByName("西服");
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	public List<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
		return super.queryByName(sql, new Object[] { "%" + keyword + "%" });
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		return super.getById(sql, id);
		// Connection conn = null;
		// PreparedStatement pre = null;
		// ResultSet rs = null;
		// Product product = new Product();
		// try {
		// conn = JdbcUtils.getConnection();
		// pre = conn.prepareStatement("select * from product where id = ?");
		// pre.setInt(1, id);
		// rs = pre.executeQuery();
		// if (rs.next()) {
		// product.setId(rs.getInt("id"));
		// product.setName(rs.getString("name"));
		// product.setPrice(rs.getDouble("price"));
		// product.setRemark(rs.getString("remark"));
		// }
		// return product;
		// } catch (SQLException e) {
		// throw new RuntimeException();
		// } finally {
		// JdbcUtils.close(conn, pre);
		// }
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
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		Object[] a = { product.getName(), product.getPrice(), product.getRemark() };
		return super.update(sql, a);
	}

	@Override
	protected Product getRow(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getDouble("price"));
		product.setRemark(rs.getString("remark"));
		return product;
	}
}
