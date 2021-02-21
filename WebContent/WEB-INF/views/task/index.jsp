<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-3">Danh sách công việc</h3>
<div class="row">
	<div class="col-md-8">
		<a href="<%=request.getContextPath()%>/task/add"
			class="btn btn-primary">Thêm mới</a>
	</div>
	<div class="col-md-4">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Tìm kiếm... (not use)" readonly>
			<div class="input-group-append">
				<span class="input-group-text" id="basic-addon2"><i
					class="fa fa-search"></i></span>
			</div>
		</div>
	</div>
</div>
<p class="text-center text-danger">${ message }</p>
<table class="table table-bordered table-hover mt-3">
	<thead>
		<tr>
			<th>STT</th>
			<th>Tên công việc</th>

			<th>Ngày bắt đầu</th>
			<th>Ngày kết thúc</th>

			<th>Trạng thái</th>
			<th>Người làm</th>
			<th>Dự án</th>
			<th>#</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ tasks }" var="item">
			<tr
				<c:if test="${( (USER_LOGIN.fullname != item.userName) 
				and (USER_LOGIN.roleName != 'ROLE_ADMIN') 
				and (USER_LOGIN.roleName != 'ROLE_LEADER') )}">style="display:none"</c:if>>
				<td>${ item.id }</td>
				<td>${ item.name }</td>

				<td>${ item.startDate }</td>
				<td>${ item.endDate }</td>

				<td>${ item.statusName }</td>
				<td>${ item.userName }</td>
				<td>${ item.projectName }</td>
				<td><a
					href="<%=request.getContextPath()%>/task/edit?id=${item.id}"
					class="btn btn-sm btn-info"> <i class="fa fa-pencil-square-o"></i>
				</a> <a onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
					href="<%=request.getContextPath()%>/task/delete?id=${item.id}"
					class="btn btn-sm btn-danger"> <i class="fa fa-trash-o"></i>
				</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>