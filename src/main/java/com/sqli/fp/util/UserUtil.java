package com.sqli.fp.util;

import java.util.ArrayList;
import java.util.List;

import com.sqli.domain.User;

public class UserUtil {

	public static List<User> getHugeListOfUser() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setNom("Bond");
		user.setPrenom("James");
		user.setAge(46);
		for (int i = 0; i < 200000; i++) {
			users.add(user);
		}
		return users;
	}
	
}
