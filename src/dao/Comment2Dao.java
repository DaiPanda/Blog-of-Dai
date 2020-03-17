package dao;

import java.util.ArrayList;

import pojo.Comment2;

public interface Comment2Dao {
	// 1.遍历评论
	public ArrayList<Comment2> getAllComment(int article_id);

	// 2.遍历评论 有分页效果
	public ArrayList<Comment2> getPageComment(int article_id,int page, int limit);

	// 3.删除评论
	public boolean delComment(int comment_id);

	// 4.增加评论
	public boolean insertComment(Comment2 c);
	
	// 5.搜索评论 按照评论内容搜索
	public int getSearchComment2(String comment_content);
	
	//6.搜索评论 按照评论内容搜索 分页
	public ArrayList<Comment2> getSearchComment2(String comment_content,int page,int limit);
}
