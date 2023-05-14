package com.nt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyData {

	private String name;
	private int age;
	private String jobProfile;
	private long curentBalance;
	
	private long futureBalance;


	@Override
	public String toString() {
		return "MyData [name=" + name + ", age=" + age + ", jobProfile=" + jobProfile + ", curentBalance="
				+ curentBalance + ", futureBalance=" + futureBalance + "]";
	}
	
}
