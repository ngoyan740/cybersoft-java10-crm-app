package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;
import com.myclass.utility.DomainConstant;
import com.myclass.utility.PathConstant;
import com.myclass.utility.UrlConstant;

@WebServlet(urlPatterns = { PathConstant.PATH_USER, PathConstant.PATH_USER_ADD, PathConstant.PATH_USER_EDIT,
		PathConstant.PATH_USER_DELETE })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2871949045319772484L;
	private RoleService roleService;
	private UserService userService;

	public UserController() {
		roleService = new RoleService();
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String message = "";

		// Get current user login
		HttpSession session = req.getSession();
		UserDto userDtoLogin = (UserDto) session.getAttribute(DomainConstant.USER_LOGIN);
		UserDto userDtoRight;

		switch (action) {
		case PathConstant.PATH_USER:
			// Check user right
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)
					&& !userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_LEADER)) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}

			req.setAttribute("users", userService.getAll());
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_USER_HOME).forward(req, resp);

			break;
		case PathConstant.PATH_USER_ADD:
			// Check user right
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}

			req.setAttribute("roles", roleService.getAll());
			req.getRequestDispatcher(UrlConstant.URL_USER_ADD).forward(req, resp);

			break;
		case PathConstant.PATH_USER_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));

			// Check user right
			userDtoRight = userService.getById(id);
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)
					&& !userDtoLogin.getFullname().equals(userDtoRight.getFullname())) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}

			req.setAttribute("user", userService.getById(id));
			req.setAttribute("roles", roleService.getAll());
			req.getRequestDispatcher(UrlConstant.URL_USER_EDIT).forward(req, resp);

			break;
		case PathConstant.PATH_USER_DELETE:
			int idDelete = Integer.valueOf(req.getParameter("id"));

			// Check user right
			if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)) {
				req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
				break;
			}

			if (userService.deleteById(idDelete) <= 0) {
				message = "Xóa thất bại!";
			}

			req.setAttribute("message", message);
			List<UserDto> userList = userService.getAll();
			req.setAttribute("users", userList);
			req.getRequestDispatcher(UrlConstant.URL_USER_HOME).forward(req, resp);

			break;
		default:

			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.valueOf(req.getParameter("roleId"));
		UserDto userDto = new UserDto(email, pass, fullname, avatar, roleId);
		
		// Get current user login
		HttpSession session = req.getSession();
		UserDto userDtoLogin = (UserDto) session.getAttribute(DomainConstant.USER_LOGIN);

		switch (action) {
		case PathConstant.PATH_USER_ADD:
			if (userService.insert(userDto) == -1) {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_USER_ADD).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/user");
			}

			break;
		case PathConstant.PATH_USER_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			userDto.setId(id);
			if (userService.update(userDto) == -1) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_USER_EDIT).forward(req, resp);
			} else {
				if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_USER)) {
					resp.sendRedirect(req.getContextPath() + "/user");
				} else {
					resp.sendRedirect(req.getContextPath() + "/home");
				}
			}

			break;
		default:

			break;
		}

	}
}
