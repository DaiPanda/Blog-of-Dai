package dao;

import java.util.ArrayList;

import pojo.Friend;

public interface FriendDao {
	// 1.增加友情链接
		public boolean insertFriend(String friend_name,String friend_url);
	// 2.删除友情链接
		public boolean delFriend(int friend_id);
	// 3.获得所有的友情链接
		public ArrayList<Friend> getAllFriend();
	// 4.获得所有的友情链接之分页
		public ArrayList<Friend> getPageFriend(int page,int limit);
	// 5.更新友情链接
		public boolean updateFriend(Friend f);
	// 通过id获得友链信息
		public Friend getOneFriend(int friend_id);
}
