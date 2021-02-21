<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- SIDE BAR -->
<!-- <div id="side-bar"> -->

<!-- Import thu vien /static/css/style.css khong hoat dong? Nen copy truc tiep tu file style.css vao html -->
<div
	style="font-weight: bold; color: #fff; font-size: 21px; padding: 12px 15px;">CRM
	WEB</div>
<ul class="list-group rounded-0">
	<li
		style="font-weight: bold; text-transform: uppercase; background: #9368d8; padding: 10px 15px; color: #f2f2f2;">DASHBOARD</li>
	<li><a
		style="background: transparent; padding: 7px 15px; color: #fff; display: block; text-decoration: none;"
		href="<%=request.getContextPath()%>/user"> <i
			class="fa fa-user mr-2"></i> Quản lý thành viên
	</a></li>
	<li><a
		style="background: transparent; padding: 7px 15px; color: #fff; display: block; text-decoration: none;"
		href="<%=request.getContextPath()%>/role"> <i
			class="fa fa-slack mr-2"></i> Quản lý quyền
	</a></li>
	<li><a
		style="background: transparent; padding: 7px 15px; color: #fff; display: block; text-decoration: none;"
		href="<%=request.getContextPath()%>/status"> <i
			class="fa fa-book mr-2"></i> Quản lý trạng thái
	</a></li>
	<li><a
		style="background: transparent; padding: 7px 15px; color: #fff; display: block; text-decoration: none;"
		href="<%=request.getContextPath()%>/project"> <i
			class="fa fa-edit mr-2"></i> Quản lý dự án
	</a></li>
	<li><a
		style="background: transparent; padding: 7px 15px; color: #fff; display: block; text-decoration: none;"
		href="<%=request.getContextPath()%>/task"> <i
			class="fa fa-table mr-2"></i> Quản lý công việc
	</a></li>

	<!-- <li><a href="#"> <i class="fa fa-cogs mr-2"></i> Cấu hình hệ
				thống
		</a></li> -->

</ul>
<!-- </div> -->

