package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/tree")
	public FolderTree folderTree() {
		FolderTree tree = new FolderTree(0, "Root");
		return getFolderTreeRecursive(tree, tree);
	}

	FolderTree getFolderTreeRecursive(FolderTree tree, FolderTree currentNode) {
		List<FolderTree> children = getChildren(currentNode.getId());
		currentNode.setChildren(children);
		for (FolderTree nextNode : children) {
			getFolderTreeRecursive(tree, nextNode);
		}
		return tree;
	}

	private List<FolderTree> getChildren(long id) {
		if (id == 0) {
			return Arrays.asList(
					new FolderTree(1, "folder1"),
					new FolderTree(2, "folder2"),
					new FolderTree(3, "folder3"));
		}
		if (id == 1) {
			return Arrays.asList(
					new FolderTree(4, "folder4"));
		}
		if (id == 4) {
			return Arrays.asList(
					new FolderTree(5, "folder5"),
					new FolderTree(6, "folder6"));
		}
		if (id == 2) {
			return Arrays.asList(
					new FolderTree(7, "folder7"));
		}
		return Collections.emptyList();
	}

}
