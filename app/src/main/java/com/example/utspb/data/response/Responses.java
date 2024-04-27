package com.example.utspb.data.response;

import com.google.gson.annotations.SerializedName;

public class Responses {

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("twitter_username")
	private String twitterUsername;

	@SerializedName("bio")
	private String bio;

	@SerializedName("login")
	private String login;

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public String getName(){
		return name;
	}

	public String getTwitterUsername(){
		return twitterUsername;
	}

	public String getBio(){
		return bio;
	}

	public String getLogin(){
		return login;
	}
}