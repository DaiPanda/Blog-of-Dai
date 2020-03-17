package dao;

import java.util.ArrayList;

import pojo.Comment;

public interface CommentDao {
	//1.遍历评论
	public ArrayList<Comment> getAllComment();
	//2.遍历评论 有分页效果
	public ArrayList<Comment> getPageComment(int page,int limit);
	//3.删除评论
	public boolean delComment(int comment_id);
	//4.增加评论
	public boolean insertComment(Comment c);
	//5.根据类型和条件遍历评论
	// 5.1 根据类型遍历评论
	public ArrayList<Comment> getPageTypeComment(int comment_type,int page,int limit);
	public ArrayList<Comment> getTypeComment(int comment_type);
	// 5.2 根据内容遍历评论
	public ArrayList<Comment> getPageContentComment(String comment_content,int page,int limit);
	public ArrayList<Comment> getContentComment(String comment_content);
	// 5.3 根据内容和类型遍历评论
	public ArrayList<Comment> getPageSpecificComment(int comment_type,String comment_content,int page,int limit);
	public ArrayList<Comment> getSpecificComment(int comment_type,String comment_content);
	

}
