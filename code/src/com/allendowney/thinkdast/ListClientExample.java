package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ListClientExample {

	private List list;

	public ListClientExample() {
		list = new ArrayList();
	}

	public List getList() {
		return list;
	}

	public static void main(String[] args) {
		ListClientExample lce = new ListClientExample();
		List list = lce.getList();
		System.out.println(list);
	}
}
