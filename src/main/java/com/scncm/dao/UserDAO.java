package com.scncm.dao;

import com.scncm.model.User;

public interface UserDAO {

	public User getUser(String login);
}
