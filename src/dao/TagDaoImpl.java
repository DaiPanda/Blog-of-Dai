package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojo.ChangePage;
import pojo.Tag;
import util.DBUtil;

public class TagDaoImpl implements TagDao {

	@Override
	public boolean insertTag(String tag_name) {
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "insert into dai_tag(tag_name) values(?)";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, tag_name);
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
	public boolean deleteTag(int tag_id) {
		Connection conn = DBUtil.getConnection();
		// 在数据库中删除记录
		String sql = "delete from dai_tag where tag_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, tag_id);
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
	public ArrayList<Tag> getAllTag() {
		ArrayList<Tag> t = new ArrayList<Tag>();
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "select * from dai_tag";
		// 预编译
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rst = prep.executeQuery();
			while (rst.next()) {
				Tag tag = new Tag();
				tag.setTag_id(rst.getInt("tag_id"));
				tag.setTag_name(rst.getString("tag_name"));
				t.add(tag);
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
		return t;
	}

	@Override
	public ArrayList<Tag> getPageTag(int page, int limit) {
		ArrayList<Tag> t = new ArrayList<Tag>();
		Connection conn = DBUtil.getConnection();
		ChangePage cp = new ChangePage();
		cp.setLimit(limit);
		cp.setPage(page);
		int startRow = cp.getStartRow();
		// System.out.println("我的limit是："+limit);
		// System.out.println("我的page是"+page);
		// System.out.println(startRow);
		String sql = "select * from dai_tag limit " + startRow + "," + limit + "";
		// 预编译
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rst = prep.executeQuery();
			while (rst.next()) {
				Tag tag = new Tag();
				tag.setTag_id(rst.getInt("tag_id"));
				tag.setTag_name(rst.getString("tag_name"));
				t.add(tag);
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
		return t;

	}

	@Override
	public Tag getOneTag(int tag_id) {
		Tag t = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from dai_tag where tag_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, tag_id);
			ResultSet rst = prep.executeQuery();
			if (rst.next()) {
//				System.out.println("啊啊啊，我是servlet，我已经查到信息了！！！");
				t = new Tag();
				t.setTag_id(rst.getInt(1));
				t.setTag_name(rst.getString("tag_name"));
				return t;
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
		return t;

	}

	@Override
	public boolean updataTag(Tag t) {
		//System.out.println("你进了我这个方法吗？！！！");
		Connection conn = DBUtil.getConnection();
		// 在数据库中修改记录
		String sql = "update dai_tag set tag_name = ? where tag_id=?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1,t.getTag_name());
			prep.setInt(2, t.getTag_id());
			prep.executeUpdate();
			return true;
		} catch (Exception e) {
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
	public boolean queryIsExist(String tag_name) {
		Connection conn = DBUtil.getConnection();
		// 在数据库中查询记录
		String sql = "select * from dai_tag where tag_name = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, tag_name);
			ResultSet rst = prep.executeQuery();
			if(rst.next()) {
				return true;
			}else {
				return false;
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
		return false;
	}

}
