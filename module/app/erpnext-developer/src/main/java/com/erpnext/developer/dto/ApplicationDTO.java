package com.erpnext.developer.dto;

public class ApplicationDTO implements Comparable<ApplicationDTO>{
	
	private String id;
	
	private String name;
	
	private String icon;
	
	private Long sequence;
	
	private String perm;
	
	public ApplicationDTO() {
		super();
	}

	public ApplicationDTO(String id, String name, String icon, Long sequence, String perm) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.sequence = sequence;
		this.perm = perm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	@Override
	public int compareTo(ApplicationDTO o) {
		return this.sequence.compareTo(o.sequence);
	}
	
}
