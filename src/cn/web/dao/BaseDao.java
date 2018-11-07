package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.web.utils.JdbcUtils;

public class BaseDao {

	protected int update(String sql, Object[] param) {
		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
