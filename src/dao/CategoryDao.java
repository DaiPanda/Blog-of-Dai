package dao;

import java.util.ArrayList;

import pojo.Category;

public interface CategoryDao {
	//1.增加分类
	public boolean insertCategory(int parent_id,String category_name);
	//2.获取所有的分类
	public ArrayList<Category> getAllCategory();
	//3.根据id到数据库中查询分类
	public  Category getOneCategory(int category_id);
	//4.修改分类
	public boolean update(Category category);
	//5.删除分类
	public boolean delete(int category_id);
	//6.获得一级目录
	public ArrayList<Category> getFirstCategory();
	//7.获得二级目录
	public ArrayList<Category> getSecondCategory(int parent_id);
	//8.获取分页分类
	public ArrayList<Category> getPageCategory(int page,int limit);
	//9.通过子分类查父分类
	public int getParentCategory(int category_id);
}
