package dao;

import java.util.ArrayList;

import pojo.Tag;

public interface TagDao {
	//1.增加标签
	public boolean insertTag(String tag_name);
	//2.删除标签
	public boolean deleteTag(int tag_id);
	//3.获得所有的标签
	public ArrayList<Tag> getAllTag();
	//4.获得一个标签
	public Tag getOneTag(int tag_id);
	//5.更新标签
	public boolean updataTag(Tag t);
	//6.获得所有的标签之分类
	public ArrayList<Tag> getPageTag(int page,int limit);
	//7.通过标签名判断该标签是否存在，存在则给提示
	public boolean queryIsExist(String tag_name);
}
