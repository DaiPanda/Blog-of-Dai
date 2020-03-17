package dao;

import java.util.ArrayList;

import pojo.Adm;

public interface AdmDao {
	//1.查询管理员,返回一个管理员类型  这是login里头的方法
	public Adm getAdm(String username,String password);
	//2查询所有的管理员
	public ArrayList<Adm> getAllAdm();
	//3.添加管理员
	public boolean insertAdm(String username,String pwd);
	//4.根据id删除管理员
	public boolean deleteAdm(int id);
	//5.修改管理员信息
	public boolean updateAdm(Adm a);
	//6.根据adm_name查询是否存在该用户，即id唯一，用户名唯一
	public boolean queryIsExist(String username);
	//7.根据id，查询一个管理员
	public Adm getOneAdm(int adm_id);
	//8 分页查询所有的管理员
	public ArrayList<Adm> getPageAdm(int page,int limit);
	
}
