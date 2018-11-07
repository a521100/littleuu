package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.web.utils.JdbcUtils;

public class BaseDao {

	public int update(String sql,Object[] aa) {
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement(sql);
			for (int i = 0;i < aa.length; i++) {
				pre.setObject(i + 1, aa[i]);
			}
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
