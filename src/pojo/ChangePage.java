package pojo;

public class ChangePage {
	private int page; //默认第一页
	private int limit; //默认一页10条数据
	private int startRow = (page-1)*limit;//mysql里语句随之发生改变
	private String sort;//排序字段
	private String order;//排序关键字
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStartRow() {
		return (page-1)*limit;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
