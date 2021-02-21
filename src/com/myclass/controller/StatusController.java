package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.StatusDto;
import com.myclass.dto.UserDto;
import com.myclass.service.StatusService;
import com.myclass.utility.DomainConstant;
import com.myclass.utility.PathConstant;
import com.myclass.utility.UrlConstant;

@WebServlet(urlPatterns = { PathConstant.PATH_STATUS, PathConstant.PATH_STATUS_ADD, PathConstant.PATH_STATUS_EDIT,
		PathConstant.PATH_STATUS_DELETE })
public class StatusController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5580300491730904939L;
	private StatusService statusService;

	public StatusController() {
		statusService = new StatusService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String message = "";

		//Check user right
		HttpSession session = req.getSession();
		UserDto userDtoLogin = (UserDto) session.getAttribute(DomainConstant.USER_LOGIN);
		if (!userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_ADMIN)
				&& !userDtoLogin.getRoleName().contentEquals(DomainConstant.ROLE_LEADER)) {
			req.getRequestDispatcher(UrlConstant.URL_ERROR_403).forward(req, resp);
			return;
		}

		switch (action) {
		case PathConstant.PATH_STATUS:
			List<StatusDto> statuss = statusService.getAll();
			req.setAttribute("listStatus", statuss);
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_STATUS_HOME).forward(req, resp);

			break;
		case PathConstant.PATH_STATUS_ADD:
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_STATUS_ADD).forward(req, resp);

			break;
		case PathConstant.PATH_STATUS_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			StatusDto dto = statusService.getById(id);
			req.setAttribute("status", dto);
			req.setAttribute("message", message);
			req.getRequestDispatcher(UrlConstant.URL_STATUS_EDIT).forward(req, resp);

			break;
		case PathConstant.PATH_STATUS_DELETE:
			int idDelete = Integer.valueOf(req.getParameter("id"));

			if (statusService.deleteById(idDelete) <= 0) {
				message = "Xóa thất bại!";
			}

			req.setAttribute("message", message);
			List<StatusDto> statusList = statusService.getAll();
			req.setAttribute("listStatus", statusList);
			req.getRequestDispatcher(UrlConstant.URL_STATUS_HOME).forward(req, resp);

			break;
		default:

			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String name = req.getParameter("name");

		StatusDto statusDto = new StatusDto(name);

		switch (action) {
		case PathConstant.PATH_STATUS_ADD:
			if (statusService.insert(statusDto) == -1) {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_STATUS_ADD).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/status");
			}

			break;
		case PathConstant.PATH_STATUS_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			statusDto.setId(id);
			if (statusService.update(statusDto) == -1) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.URL_STATUS_EDIT).forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/status");
			}

			break;
		default:

			break;
		}
	}
}
