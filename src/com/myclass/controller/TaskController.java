package com.myclass.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.service.ProjectService;
import com.myclass.service.StatusService;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;
import com.myclass.utility.DomainConstant;
import com.myclass.utility.PathConstant;
import com.myclass.utility.UrlConstant;


@WebServlet(urlPatterns = { PathConstant.PATH_TASK, PathConstant.PATH_TASK_ADD, 
		PathConstant.PATH_TASK_EDIT, PathConstant.PATH_TASK_DELETE })
public class TaskController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2871949045319772484L;
	private StatusService statusService;
	private ProjectService projectService;
	private UserService userService;
	private TaskService taskService;

	public TaskController() {
		userService = new UserService();
		statusService = new StatusService();
		projectService = new ProjectService();
		taskService = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String message = "";
		
		//Get current user login
		HttpSession session = req.getSession();
		UserDto userDtoLogin = (UserDto)session.getAttribute(DomainConstant.USER_LOGIN);
		UserDto userDtoRight;
		
		switch (action) {
		case PathConstant.PATH_TASK:
			req.setAttribute("tasks", taskService.getAll());
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_TASK_HOME).forward(req, resp);
			
			break;
		case PathConstant.PATH_TASK_ADD:
			//Check user right
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)
					&& !userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_LEADER)) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}
			
			req.setAttribute("users", userService.getAll());
			req.setAttribute("status", statusService.getAll());
			req.setAttribute("projects", projectService.getAll());
			req.getRequestDispatcher(UrlConstant.URL_TASK_ADD).forward(req, resp);
			
			break;
		case PathConstant.PATH_TASK_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			
			//Check user right
			userDtoRight = userService.getById(taskService.getById(id).getUserId());
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN) 
					&& !userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_LEADER)
					&& !userDtoLogin.getFullname().equals(userDtoRight.getFullname())) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}
			
			req.setAttribute("task", taskService.getById(id));
			req.setAttribute("users", userService.getAll());
			req.setAttribute("status", statusService.getAll());
			req.setAttribute("projects", projectService.getAll());
			req.getRequestDispatcher(UrlConstant.URL_TASK_EDIT).forward(req, resp);
			
			break;
		case PathConstant.PATH_TASK_DELETE:
			int idDelete = Integer.valueOf(req.getParameter("id"));
			
			//Check user right for delete task
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN) 
					&& !userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_LEADER)) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}

			if (taskService.deleteById(idDelete) <= 0) {
				message = "Xóa thất bại!";
			}
			
			req.setAttribute("message", message);
			List<TaskDto> taskList = taskService.getAll();
			req.setAttribute("tasks", taskList);
			req.getRequestDispatcher(UrlConstant.URL_TASK_HOME).forward(req, resp);
			
			break;
		default:
			
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date startDate = null, endDate = null;
		
		String action = req.getServletPath();
		
		String name = req.getParameter("name");
		
		try {
			startDate = df.parse(req.getParameter("startDate"));
			endDate = df.parse(req.getParameter("endDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int statusId = Integer.valueOf(req.getParameter("statusId"));
		int userId = Integer.valueOf(req.getParameter("userId"));
		int projectId = Integer.valueOf(req.getParameter("projectId"));
		
		TaskDto taskDto = new TaskDto(name, startDate, endDate, statusId, userId, projectId);

		switch (action) {
		case PathConstant.PATH_TASK_ADD:
			if (taskService.insert(taskDto) == -1) {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_TASK_ADD).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/task");
			}
			
			break;
		case PathConstant.PATH_TASK_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			taskDto.setId(id);
			if (taskService.update(taskDto) == -1) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_TASK_EDIT).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/task");
			}
			
			break;
		default:
			
			break;
		}	
		
		
	}
}
