package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.web.utils.JdbcUtils;

public abstract class BaseDao<T> {

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

	protected abstract T getRow(ResultSet rs) throws SQLException;

	protected T getById(String sql, int id) {
		T model = null;
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement("select * from product where id = ?");
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (rs.next()) {
				model = this.getRow(rs);
			}
			return model;
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtils.close(conn, pre);
		}
	}

	public List<T> queryByName(String sql, Object[] param) {
		List<T> tlist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				T t = this.getRow(rs);
				tlist.add(t);
			}
			return tlist;
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtils.close(conn, pre);
		}

	}
}
