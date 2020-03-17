package dao;

import java.util.ArrayList;

import pojo.Article;
import pojo.Article_Tag;

public interface Article_Tag_Dao {
	//1.插入数据到数据库
	public boolean insertArticle_Tag(int article_id,int tag_id); 
	//2.修改数据到数据库
	public boolean updateArticle_Tag(int article_id,int tag_id);
	//3.删除数据到数据库
	public boolean deleteArticle_Tag(int article_id,int tag_id);
	//4.通过article_id查询tag_id
	public ArrayList<Article_Tag> searchArticle_Tag(int article_id);
	//5.通过tag_id查询article_id 分页
	public ArrayList<Article> searchTag_Article(int tag_id,int page,int limit);
	//6.通过tag_id查询article_id 总数
	public int searchAllTag_Article(int tag_id);
}
