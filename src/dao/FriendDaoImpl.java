package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojo.ChangePage;
import pojo.Friend;
import util.DBUtil;

public class FriendDaoImpl implements FriendDao {

	@Override
	public boolean insertFriend(String friend_name, String friend_url) {
		System.out.println("测试是否进入了insertFriend方法");
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "insert into dai_friend(friend_name,friend_url) values(?,?)";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, friend_name);
			prep.setString(2, friend_url);
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean delFriend(int friend_id) {
		System.out.println("测试是否进入了delFriend方法");
		Connection conn = DBUtil.getConnection();
		// 在数据库中删除记录
		String sql = "delete from dai_friend where friend_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, friend_id);
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Friend> getAllFriend() {
		System.out.println("测试是否进入了getAllFriend方法");
		ArrayList<Friend> fri = new ArrayList<Friend>();
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "select * from dai_friend";
		// 预编译
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rst = prep.executeQuery();
			while (rst.next()) {
				Friend f = new Friend();
				f.setFriend_id(rst.getInt(1));
				f.setFriend_name(rst.getString("friend_name"));
				f.setFriend_url(rst.getString("friend_url"));
				fri.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fri;
	}

	@Override
	public ArrayList<Friend> getPageFriend(int page, int limit) {
		System.out.println("测试是否进入了getPageFriend方法");
		ArrayList<Friend> fri = new ArrayList<Friend>();
		Connection conn = DBUtil.getConnection();
		ChangePage cp = new ChangePage();
		cp.setLimit(limit);
		cp.setPage(page);
		int startRow = cp.getStartRow();
		// 在数据库中添加记录
		String sql = "select * from dai_friend limit " + startRow + "," + limit + "";
		// 预编译
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rst = prep.executeQuery();
			while (rst.next()) {
				Friend f = new Friend();
				f.setFriend_id(rst.getInt(1));
				f.setFriend_name(rst.getString("friend_name"));
				f.setFriend_url(rst.getString("friend_url"));
				fri.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fri;
	}

	@Override
	public boolean updateFriend(Friend f) {
		System.out.println("测试是否进入了updateFriend的方法");
		Connection conn = DBUtil.getConnection();
		// 在数据库中修改记录
		String sql = "update dai_friend set friend_name = ?,friend_url = ? where friend_id=?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, f.getFriend_name());
			prep.setString(2, f.getFriend_url());
			prep.setInt(3, f.getFriend_id());
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Friend getOneFriend(int friend_id) {
		Friend f = null;
		//链接数据库
		Connection conn = DBUtil.getConnection();
		// 预编译
		PreparedStatement prep;
		String sql = "select * from dai_friend where friend_id = ?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1,friend_id);
			ResultSet rst = prep.executeQuery();
			if(rst.next()) {
				f = new Friend();
				f.setFriend_id(rst.getInt(1));
				f.setFriend_name(rst.getString("friend_name"));
				f.setFriend_url(rst.getString("friend_url"));
				return f;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return f;

	}

}
