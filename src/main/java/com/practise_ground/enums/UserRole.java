package com.practise_ground.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
public enum UserRole {

	ADMIN("admin"), MAINTAINER("maintainer"), USER("User");

	private String displayValue;

	private UserRole(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDislayValue() {
		return displayValue;
	}

	public static UserRole getValueOf(String value) {
		Optional<UserRole> filteredItem = Arrays.asList(UserRole.values()).stream()
				.filter(item -> item.name().toLowerCase().equals(value.toLowerCase())).findFirst();
		if (filteredItem.isPresent())
			return filteredItem.get();
		return null;
	}

	public static List<String> getAllDisplayValues() {
		return Arrays.asList(UserRole.values()).stream().map(item -> item.displayValue).collect(Collectors.toList());
	}

}
