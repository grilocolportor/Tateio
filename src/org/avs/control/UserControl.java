package org.avs.control;

import org.avs.bean.User;
import org.avs.repository.UserRepository;

public class UserControl {
	public static User addUser(User user){
		return UserRepository.addUser(user);
	}
	
}
