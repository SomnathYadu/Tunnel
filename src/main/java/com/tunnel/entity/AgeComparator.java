package com.tunnel.entity;

import java.util.Comparator;

public class AgeComparator implements Comparator<User>{

	@Override
	public int compare(User user1, User user2) {
		if(user1.getAge() == user2.getAge())
			return 0;
		else if(user1.getAge() > user2.getAge())
			return 1;
		else 
			return -1;
	}


}