package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Article;
import pojo.Article_Tag;
import pojo.ChangePage;
import util.DBUtil;

public class Article_Tag_DaoImpl implements Article_Tag_Dao {

	@Override
	public boolean insertArticle_Tag(int article_id, int tag_id) {
		System.out.println("是否进入了insertArticle_Tag方法");
		Connection conn = DBUtil.getConnection();
		String sql = "insert into tag_article(article_id,tag_id) values(?,?)";
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, article_id);
			prep.setInt(2, tag_id);
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
	public boolean updateArticle_Tag(int article_id, int tag_id) {
		System.out.println("是否进入了updateArticle_Tag方法");
		Connection conn = DBUtil.getConnection();
		String sql = "update tag_article set tag_id = ? where article_id=?";
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, tag_id);
			prep.setInt(2, article_id);
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
	public boolean deleteArticle_Tag(int article_id, int tag_id) {
		System.out.println("是否进入了deleteArticle_Tag方法呢？？？");
		Connection conn = DBUtil.getConnection();
		// 在数据库中删除记录
		String sql = "delete from tag_article where article_id = ? and tag_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, article_id);
			prep.setInt(2, tag_id);
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
	public ArrayList<Article_Tag> searchArticle_Tag(int article_id) {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tag_article where article_id = ?";
		// 预编译
		PreparedStatement prep;
		ArrayList<Article_Tag> a = null;
		try {
			a = new ArrayList<Article_Tag>();
			prep = conn.prepareStatement(sql);
			prep.setInt(1, article_id);
			ResultSet rst = prep.executeQuery();
			while (rst.next()) {
				Article_Tag at = new Article_Tag();
				at.setArticle_id(rst.getInt("article_id"));
				at.setTag_id(rst.getInt("tag_id"));
				a.add(at);
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

		return a;
	}

	/*
	 * @Override public ArrayList<Article_Tag> searchTag_Article(int tag_id, int
	 * page, int limit) { Connection conn = DBUtil.getConnection(); ChangePage cp =
	 * new ChangePage(); cp.setLimit(limit); cp.setPage(page); int startRow =
	 * cp.getStartRow(); String sql =
	 * "select * from tag_article where tag_id = ? limit ?,?"; // 预编译
	 * PreparedStatement prep; ArrayList<Article_Tag> a = null; try { a = new
	 * ArrayList<Article_Tag>(); prep = conn.prepareStatement(sql); // 换一种写法
	 * prep.setInt(1, tag_id); prep.setInt(2, startRow); prep.setInt(3,
	 * cp.getLimit()); ResultSet rst = prep.executeQuery(); while (rst.next()) {
	 * Article_Tag at = new Article_Tag();
	 * at.setArticle_id(rst.getInt("article_id"));
	 * at.setTag_id(rst.getInt("tag_id")); a.add(at); } } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { DBUtil.closeConnection(conn); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 * 
	 * return a; }
	 */

	@Override
	public int searchAllTag_Article(int tag_id) {
		int count = 0;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tag_article where tag_id = ?";
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, tag_id);
			ResultSet rst = prep.executeQuery();
			rst.last();// 把其放在表尾
			count = rst.getRow();
		/*	System.out.println("count是什么呢？应该是1不该是3啊！！！"+count);*/
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public ArrayList<Article> searchTag_Article(int tag_id, int page, int limit) {
		Connection conn = DBUtil.getConnection();
		ChangePage cp = new ChangePage();
		cp.setLimit(limit);
		cp.setPage(page);
		int startRow = cp.getStartRow();
		String sql = "select * from dai_article,tag_article where tag_article.article_id = dai_article.article_id and tag_article.tag_id =? limit ?,?";
		// 预编译
		PreparedStatement prep;
		ArrayList<Article> aList = null;
		try {
			aList = new ArrayList<Article>();
			prep = conn.prepareStatement(sql);
			// 换一种写法
			prep.setInt(1, tag_id);
			prep.setInt(2, startRow);
			prep.setInt(3, cp.getLimit());
			ResultSet rst = prep.executeQuery();
			while (rst.next()) {
				Article a = new Article();
				a.setArticle_content(rst.getString("article_content"));
				a.setArticle_desc(rst.getString("article_desc"));
				a.setArticle_createtime(rst.getLong("article_createtime"));
				a.setArticle_id(rst.getInt("article_id"));
				a.setArticle_image(rst.getString("article_image"));
				a.setArticle_isTop(rst.getInt("article_isTop"));
				a.setArticle_title(rst.getString("article_title"));
				a.setArticle_views(rst.getInt("article_views"));
				a.setArticle_likenum(rst.getInt("article_likenum"));
				a.setCategory_id(rst.getInt("category_id"));
				aList.add(a);
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

		return aList;
	}

}
