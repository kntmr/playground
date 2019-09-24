package com.example.demo;

import java.util.List;

public class FolderTree {

	private long id;
	private String name;
	private List<FolderTree> children;

	public FolderTree(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<FolderTree> getChildren() {
		return children;
	}

	public void setChildren(List<FolderTree> children) {
		this.children = children;
	}

}
