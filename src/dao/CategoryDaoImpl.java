package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBUtil;
import pojo.Adm;
import pojo.Category;
import pojo.ChangePage;

public class CategoryDaoImpl implements CategoryDao {

	//1.增加分类
	@Override
	public boolean insertCategory(int parent_id,String category_name) {
		//System.out.println("1.你走了我这个方法哦");
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "insert into dai_category(parent_id,category_name) values(?,?)";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, parent_id);
			prep.setString(2, category_name);
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

	//2.获取所有的分类
	@Override
	public ArrayList<Category> getAllCategory() {
		ArrayList<Category> c = new ArrayList<Category>();
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "select * from dai_category";
		// 预编译
				try {
					PreparedStatement prep = conn.prepareStatement(sql);
					ResultSet rst = prep.executeQuery();
					while (rst.next()) {
						Category c1 = new Category();
						c1.setCategory_id(rst.getInt(1));
						c1.setCategory_name(rst.getString("category_name"));
						c1.setParent_id(rst.getInt("parent_Id"));
						c.add(c1);
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
		return c;
	}
	
	//3.根据id到数据库中查询分类
	@Override
	public Category getOneCategory(int category_id) {
		//System.out.println("又到了万众瞩目的测试环节啦！！！");
		Category c = null;
		Connection conn = DBUtil.getConnection();
		// 在数据库中修改记录
		String sql = "select * from dai_category where category_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1,category_id);
			ResultSet rst = prep.executeQuery();
			c = new Category();
			if(rst.next()) {
				c.setCategory_id(rst.getInt("category_id"));
				c.setCategory_name(rst.getString("category_name"));
				c.setParent_id(rst.getInt("parent_id"));
				return c;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	//4.修改分类
	@Override
	public boolean update(Category category) {
		Connection conn = DBUtil.getConnection();
		// 在数据库中修改记录
		String sql = "update dai_category set category_name = ?,parent_id = ? where category_id=?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1,category.getCategory_name());
			prep.setInt(2, category.getParent_id());
			prep.setInt(3, category.getCategory_id());
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//5.删除分类
	@Override
	public boolean delete(int category_id) {
		Connection conn = DBUtil.getConnection();
		// 在数据库中删除记录
		String sql = "delete from dai_category where category_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, category_id);
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

	//获得一级目录
	@Override
	public ArrayList<Category> getFirstCategory() {
		System.out.println("获得一级目录");
		ArrayList<Category> c = new ArrayList<Category>();
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "select * from dai_category where parent_id = 0";
		// 预编译
				try {
					PreparedStatement prep = conn.prepareStatement(sql);
					ResultSet rst = prep.executeQuery();
					while (rst.next()) {
						Category c1 = new Category();
						c1.setCategory_id(rst.getInt(1));
						c1.setCategory_name(rst.getString("category_name"));
						c1.setParent_id(rst.getInt("parent_Id"));
						c.add(c1);
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
				System.out.println("一级目录下有"+c.size()+"条数据");
		return c;
	}

	//获得二级目录
	@Override
	public ArrayList<Category> getSecondCategory(int parent_id) {
		System.out.println("获得二级目录");
		ArrayList<Category> c = new ArrayList<Category>();
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql = "select * from dai_category where parent_id = ?";
		// 预编译
				try {
					PreparedStatement prep = conn.prepareStatement(sql);
					prep.setInt(1, parent_id);
					ResultSet rst = prep.executeQuery();
					while (rst.next()) {
						Category c1 = new Category();
						c1.setCategory_id(rst.getInt(1));
						c1.setCategory_name(rst.getString("category_name"));
						c1.setParent_id(rst.getInt("parent_Id"));
						c.add(c1);
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
		return c;
	}

	@Override
	public ArrayList<Category> getPageCategory(int page, int limit) {
		ArrayList<Category> c = new ArrayList<Category>();
		ChangePage cp = new ChangePage();
		cp.setLimit(limit);
		cp.setPage(page);
		int startRow = cp.getStartRow();	
		Connection conn = DBUtil.getConnection();
		// 在数据库中添加记录
		String sql =  "select * from dai_category limit "+startRow+","+limit+"";
		// 预编译
				try {
					PreparedStatement prep = conn.prepareStatement(sql);
					ResultSet rst = prep.executeQuery();
					while (rst.next()) {
						Category c1 = new Category();
						c1.setCategory_id(rst.getInt(1));
						c1.setCategory_name(rst.getString("category_name"));
						c1.setParent_id(rst.getInt("parent_Id"));
						c.add(c1);
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
		return c;
	}

	@Override
	public int getParentCategory(int category_id) {
		Category c = null;
		Connection conn = DBUtil.getConnection();
		// 在数据库中修改记录
		String sql = "select * from dai_category where category_id = ?";
		// 预编译
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1,category_id);
			ResultSet rst = prep.executeQuery();
			c = new Category();
			if(rst.next()) {
				c.setCategory_id(rst.getInt("category_id"));
				c.setCategory_name(rst.getString("category_name"));
				c.setParent_id(rst.getInt("parent_id"));
				return c.getParent_id();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c.getParent_id();
	}

}
