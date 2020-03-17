package dao;

import java.util.ArrayList;

import pojo.Article;

// 博文Dao
public interface ArticleDao {
	//1.添加文章
	public boolean insertArticle(Article a);
	//2.修改文章
	public boolean updateArticle(Article a);
	//3.删除文章
	public boolean deleteArticle(int article_id);
	//4.获取全部的文章
	public ArrayList<Article> getAllArticle();
	//6.通过分类id查找文章
	public ArrayList<Article> getCategoryArticle(int category_id,int page,int limit);
	//6- 通过分类id查找文章返回总数
	public int getAllCategroyArticle(int category_id);	
	//7.查询分页的数据
	public ArrayList<Article> getPageArticle(int page,int limit);
	//8.根据id获取一篇文章
	public Article getOneArticle(int article_id);
	//9.查询置顶文章
	public Article getTopArticle(int article_isTop);
	//10.模糊查询 搜索文章标题
	public ArrayList<Article> getSearchArticle(String article_title);
	//10.模糊查询 搜索文章标题
	public ArrayList<Article> getSearchPageArticle(String article_title,int page,int limit);
	//11.通过创建时间查找id
	public int getArticleId(long article_createtime);
	//12.随机查询数据 暂时设置为 6
	public ArrayList<Article> getRandomArticle(int limit);
	//13.通过article_id 查找到总共有多少条评论 建立了一个视图来做
	public int getCountComment(int article_id);
	//14.获得点赞量最多的五篇文章
	public ArrayList<Article> getHotArticle(int limit);
	//15.获得最近文章
	public ArrayList<Article> getNearArticle(int limit);
}
