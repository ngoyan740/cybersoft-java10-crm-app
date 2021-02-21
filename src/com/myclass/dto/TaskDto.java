package com.myclass.dto;

import java.util.Date;

public class TaskDto {
	private int id;
	private String name;
	
	private Date startDate;
	private Date endDate;
	
	private int statusId;
	private int userId;
	private int projectId;
	
	private String statusName;
	private String userName;
	private String projectName;
	
	public TaskDto() {}
	
	public TaskDto(String name, Date startDate, Date endDate, int statusId, int userId, int projectId) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusId = statusId;
		this.userId = userId;
		this.projectId = projectId;
	}

	public TaskDto(int id, String name, Date startDate, Date endDate, int statusId, int userId, int projectId) {
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
