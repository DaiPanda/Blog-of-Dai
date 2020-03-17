package pojo;
// 博文

public class Article {
	
	
	private Integer article_id;//博文id
	private String article_content;//博文内容
	private Integer article_likenum;//博文爱心数
	private Integer article_views;//博文浏览量
	private String article_image;//博文封面
	private String article_title;//博文标题
	private Long article_createtime;//博文创建时间
	private int article_isTop;//博文是否置顶
	private int category_id;//分类id
	private String article_desc;//博文简介
	private int count_comment;//评论数
	
	
	
	public int getCount_comment() {
		return count_comment;
	}
	public void setCount_comment(int count_comment) {
		this.count_comment = count_comment;
	}
	public String getArticle_desc() {
		return article_desc;
	}
	public void setArticle_desc(String article_desc) {
		this.article_desc = article_desc;
	}
	public Integer getArticle_views() {
		return article_views;
	}
	public void setArticle_views(Integer article_views) {
		this.article_views = article_views;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public Integer getArticle_likenum() {
		return article_likenum;
	}
	public void setArticle_likenum(Integer article_likenum) {
		this.article_likenum = article_likenum;
	}

	public String getArticle_image() {
		return article_image;
	}
	public void setArticle_image(String article_image) {
		this.article_image = article_image;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public Long getArticle_createtime() {
		return article_createtime;
	}
	public void setArticle_createtime(Long article_createtime) {
		this.article_createtime = article_createtime;
	}
	public int getArticle_isTop() {
		return article_isTop;
	}
	public void setArticle_isTop(int article_isTop) {
		this.article_isTop = article_isTop;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	
	
}
