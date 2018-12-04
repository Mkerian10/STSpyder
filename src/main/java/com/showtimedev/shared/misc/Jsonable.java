package com.showtimedev.shared.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Jsonable{
	
	default String toJson(){
		return toJson(false);
	}
	
	default String toJson(boolean prettyPrint){
		GsonBuilder builder = new GsonBuilder();
		if(prettyPrint) builder.setPrettyPrinting();
		Gson gson = builder.create();
		return gson.toJson(this);
	}
}
