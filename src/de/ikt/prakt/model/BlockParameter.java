package de.ikt.prakt.model;

public class BlockParameter {
	
	public enum Type {
		Unsinged8,
		Unsigned16,
		Unsigned32,
		Float,
		DS101,
		Other,
	}
	
	private String name;
	private int relativeIndex;
	private int size;
	private Type type;
	private boolean optional;
	
	
	public BlockParameter(String name, int relIndex, int size, Type type) {
		this(name, relIndex, size, type, false);
	}
	
	public BlockParameter(String name, int relIndex, int size, Type type, boolean optional) {
		this.name = name;
		this.relativeIndex = relIndex;
		this.size = size;
		this.type = type;
		this.optional = optional;
	}

	public String getName() {
		return name;
	}

	public int getRelativeIndex() {
		return relativeIndex;
	}

	public int getSize() {
		return size;
	}

	public Type getType() {
		return type;
	}
	
	public boolean isOptional() {
		return optional;
	}

}
