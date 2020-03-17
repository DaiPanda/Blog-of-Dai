package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.ChangePage;
import pojo.Comment2;
import util.DBUtil;

public class Comment2DaoImpl implements Comment2Dao{

	@Override
	public ArrayList<Comment2> getAllComment(int article_id) {
		ArrayList<Comment2> cList = null;                                                                                                   
		Connection conn = DBUtil.getConnection();
		String sql = "select * from comment_article where article_id = ?";
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, article_id);
			ResultSet rst = prep.executeQuery();
			cList = new ArrayList<Comment2>(); 
			while (rst.next()) {			
				Comment2 c = new Comment2();
				c.setComment_content(rst.getString("comment_content"));
				c.setComment_createtime(rst.getLong("comment_createtime"));
				c.setComment_id(rst.getInt(1));
				c.setComment_likenum(rst.getInt("comment_likenum"));
				c.setComment_name(rst.getString("comment_name"));
				c.setArticle_id(article_id);
				c.setParent_id(rst.getInt("parent_id"));
				c.setComment_email(rst.getString("comment_email"));
				cList.add(c);
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
		return cList;
	}

	@Override
	public ArrayList<Comment2> getPageComment(int article_id,int page, int limit) {
		ChangePage cp = new ChangePage();
		cp.setLimit(limit);
		cp.setPage(page);
		int startRow = cp.getStartRow();
		ArrayList<Comment2> cList = null;                                                                                                   
		Connection conn = DBUtil.getConnection();
		String sql = "select * from comment_article where article_id = ? limit "+startRow+","+limit+"";
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, article_id);
			ResultSet rst = prep.executeQuery();
			cList = new ArrayList<Comment2>(); 
			while (rst.next()) {			
				Comment2 c = new Comment2();
				c.setComment_content(rst.getString("comment_content"));
				c.setComment_createtime(rst.getLong("comment_createtime"));
				c.setComment_id(rst.getInt(1));
				c.setComment_likenum(rst.getInt("comment_likenum"));
				c.setComment_name(rst.getString("comment_name"));
				c.setArticle_id(article_id);
				c.setParent_id(rst.getInt("parent_id"));
				c.setComment_email(rst.getString("comment_email"));
				cList.add(c);
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
		return cList;
	}

	@Override
	public boolean delComment(int comment_id) {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from comment_article where comment_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, comment_id);
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
	public boolean insertComment(Comment2 c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSearchComment2(String comment_content) {
		int count = 0 ;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from comment_article where comment_content like '%"+comment_content+"%'";
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			ResultSet rst = prep.executeQuery();
			rst.last();//把其放在表尾
			count = rst.getRow();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public ArrayList<Comment2> getSearchComment2(String comment_content, int page, int limit) {
		ArrayList<Comment2> c2List = null;
		Connection conn = DBUtil.getConnection();
		ChangePage cp = new ChangePage();
		cp.setLimit(limit);
		cp.setPage(page);
		int startRow = cp.getStartRow();
		String sql = "select * from comment_article where comment_content like '%"+comment_content+"%' limit "+startRow+","+limit+"";
		try {
			c2List = new ArrayList<Comment2>();
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet rst = prep.executeQuery();
			while(rst.next()) {
				Comment2 c = new Comment2();
				c.setComment_content(rst.getString("comment_content"));
				c.setComment_createtime(rst.getLong("comment_createtime"));
				c.setComment_id(rst.getInt(1));
				c.setComment_likenum(rst.getInt("comment_likenum"));
				c.setComment_name(rst.getString("comment_name"));
				c.setArticle_id(rst.getInt("article_id"));
				c.setParent_id(rst.getInt("parent_id"));
				c.setComment_email(rst.getString("comment_email"));
				c2List.add(c);
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
		return c2List;
	}

}
