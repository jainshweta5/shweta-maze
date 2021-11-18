package com.shweta.maze;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class MazeFileUtil {
	
	static String[] convertFileToArrayString(File file){
		try {
			List<String> list = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]); 
			return a;
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid file found");
		}
	}
}