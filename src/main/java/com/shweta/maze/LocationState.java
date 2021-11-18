package com.shweta.maze;

public enum LocationState {
	OPEN(" "), VISITED("V"), WALLED("X"), START("S"), EXIT("F");
	private String value;

	private LocationState(String value) {
		this.value = value;
	}

	public static LocationState getValue(String value) {
		for (LocationState locationState : LocationState.values()) {
			if (locationState.value.equals(value)) {
				return locationState;
			}
		}
		return null;
	}
}