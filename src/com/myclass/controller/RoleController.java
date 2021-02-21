package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.utility.DomainConstant;
import com.myclass.utility.PathConstant;
import com.myclass.utility.UrlConstant;

@WebServlet(urlPatterns = { PathConstant.PATH_ROLE, PathConstant.PATH_ROLE_ADD, PathConstant.PATH_ROLE_EDIT,
		PathConstant.PATH_ROLE_DELETE })
public class RoleController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5580300491730904939L;
	private RoleService roleService;

	public RoleController() {
		roleService = new RoleService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String message = "";

		// Check user right
		HttpSession session = req.getSession();
		UserDto userDtoLogin = (UserDto) session.getAttribute(DomainConstant.USER_LOGIN);
		if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)) {
			req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
			return;
		}

		switch (action) {
		case PathConstant.PATH_ROLE:
			List<RoleDto> roles = roleService.getAll();
			req.setAttribute("listRole", roles);
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_ROLE_HOME).forward(req, resp);

			break;
		case PathConstant.PATH_ROLE_ADD:
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_ROLE_ADD).forward(req, resp);

			break;
		case PathConstant.PATH_ROLE_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			RoleDto dto = roleService.getById(id);
			req.setAttribute("role", dto);
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_ROLE_EDIT).forward(req, resp);

			break;
		case PathConstant.PATH_ROLE_DELETE:
			int idDelete = Integer.valueOf(req.getParameter("id"));

			if (roleService.deleteById(idDelete) <= 0) {
				message = "Xóa thất bại!";
			}

			req.setAttribute("message", message);
			List<RoleDto> roleList = roleService.getAll();
			req.setAttribute("listRole", roleList);
			req.getRequestDispatcher(UrlConstant.URL_ROLE_HOME).forward(req, resp);

			break;
		default:

			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String name = req.getParameter("name");
		String desc = req.getParameter("desc");
		RoleDto roleDto = new RoleDto(name, desc);

		switch (action) {
		case PathConstant.PATH_ROLE_ADD:
			if (roleService.insert(roleDto) == -1) {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_ROLE_ADD).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/role");
			}

			break;
		case PathConstant.PATH_ROLE_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			roleDto.setId(id);
			if (roleService.update(roleDto) == -1) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_ROLE_EDIT).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/role");
			}

			break;
		default:

			break;
		}
	}
}
