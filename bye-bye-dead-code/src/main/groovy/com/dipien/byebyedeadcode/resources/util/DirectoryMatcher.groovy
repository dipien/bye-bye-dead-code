package com.dipien.byebyedeadcode.resources.util

class DirectoryMatcher {

	static boolean matchLast(String dirName, String type) {
		return dirName =~ /(\/${type}-.*$)|(\/${type}$)/
	}

}