package com.mvc.controller;

import java.util.LinkedList;
import java.util.List;

import com.mvc.model.User;

public class UserDataUtil {
public static List<User> getUsers(){
	List<User> list=new LinkedList<>();
	list.add(new User("Hithesh","Raj","Hiraj@example.com","Howareyou",true));
	list.add(new User("Darshan","Raj","Daraj@example.com","Whereareyou",true));
	list.add(new User("Harsha","Ms","Hams@example.com","groot",true));
	list.add(new User("Anil","Kumar","ankur@example.com","Ankumar",false));
	return list;
}
}
