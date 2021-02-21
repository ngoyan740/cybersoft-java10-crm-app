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

import com.myclass.dto.ProjectDto;
import com.myclass.dto.UserDto;
import com.myclass.service.ProjectService;
import com.myclass.utility.DomainConstant;
import com.myclass.utility.PathConstant;
import com.myclass.utility.UrlConstant;

@WebServlet(urlPatterns = { PathConstant.PATH_PROJECT, PathConstant.PATH_PROJECT_ADD, PathConstant.PATH_PROJECT_EDIT,
		PathConstant.PATH_PROJECT_DELETE })
public class ProjectController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5580300491730904939L;
	private ProjectService projectService;

	public ProjectController() {
		projectService = new ProjectService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String message = "";

		// Check user right
		HttpSession session = req.getSession();
		UserDto userDtoLogin = (UserDto) session.getAttribute(DomainConstant.USER_LOGIN);
		if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)
				&& !userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_LEADER)) {
			req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
			return;
		}

		switch (action) {
		case PathConstant.PATH_PROJECT:
			List<ProjectDto> projects = projectService.getAll();
			req.setAttribute("listProject", projects);
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_PROJECT_HOME).forward(req, resp);

			break;
		case PathConstant.PATH_PROJECT_ADD:
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_PROJECT_ADD).forward(req, resp);

			break;
		case PathConstant.PATH_PROJECT_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			ProjectDto dto = projectService.getById(id);
			req.setAttribute("project", dto);
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_PROJECT_EDIT).forward(req, resp);

			break;
		case PathConstant.PATH_PROJECT_DELETE:
			int idDelete = Integer.valueOf(req.getParameter("id"));

			if (projectService.deleteById(idDelete) <= 0) {
				message = "Xóa thất bại!";
			}

			req.setAttribute("message", message);
			List<ProjectDto> projectList = projectService.getAll();
			req.setAttribute("listProject", projectList);
			req.getRequestDispatcher(UrlConstant.URL_PROJECT_HOME).forward(req, resp);

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

		ProjectDto projectDto = new ProjectDto(name, startDate, endDate);

		switch (action) {
		case PathConstant.PATH_PROJECT_ADD:
			if (projectService.insert(projectDto) == -1) {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_PROJECT_ADD).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/project");
			}

			break;
		case PathConstant.PATH_PROJECT_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			projectDto.setId(id);
			if (projectService.update(projectDto) == -1) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_PROJECT_EDIT).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/project");
			}

			break;
		default:

			break;
		}
	}
}
