package pojo;

public class Comment {
	private int comment_id;
	private String comment_name;
	private int parent_id;
	private String comment_content;
	private int comment_likenum;
	private long comment_createtime;
	private int comment_type;
	private String comment_email;//新增加的
	public String getComment_email() {
		return comment_email;
	}
	public void setComment_email(String comment_email) {
		this.comment_email = comment_email;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_likenum() {
		return comment_likenum;
	}
	public void setComment_likenum(int comment_likenum) {
		this.comment_likenum = comment_likenum;
	}
	public long getComment_createtime() {
		return comment_createtime;
	}
	public void setComment_createtime(long comment_createtime) {
		this.comment_createtime = comment_createtime;
	}
	public int getComment_type() {
		return comment_type;
	}
	public void setComment_type(int comment_type) {
		this.comment_type = comment_type;
	}
	
	
}
