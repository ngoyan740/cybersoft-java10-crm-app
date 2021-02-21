package com.myclass.entity;

import java.util.Date;

public class Task {
	private int id;
	private String name;
	
	private Date startDate;
	private Date endDate;
	
	private int statusId;
	private int userId;
	private int projectId;
	
	public Task() {}
	
	public Task(String name, Date startDate, Date endDate, int statusId, int userId, int projectId) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusId = statusId;
		this.userId = userId;
		this.projectId = projectId;
	}

	public Task(int id, String name, Date startDate, Date endDate, int statusId, int userId, int projectId) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusId = statusId;
		this.userId = userId;
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	
}
