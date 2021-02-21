<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-3">Danh sách thành viên</h3>
<div class="row">
	<div class="col-md-8">
		<a href="<%=request.getContextPath()%>/user/add"
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
			<th>Email</th>
			<th>Họ Tên</th>
			<th>Loại người dùng</th>
			<th>#</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ users }" var="item">
			<tr>
				<td>${ item.id }</td>
				<td>${ item.email }</td>
				<td>${ item.fullname }</td>
				<%-- <td>
                            	<c:forEach items="${ roles }" var="role">
                            		<c:if test="${ item.roleId == role.id }">${ role.desc }</c:if>
                            	</c:forEach>
                            </td> --%>
				<td>${ item.roleDesc }</td>
				<%-- <td>${ item.roleName }</td> --%>
				<td><a
					href="<%=request.getContextPath()%>/user/edit?id=${item.id}"
					class="btn btn-sm btn-info"> <i class="fa fa-pencil-square-o"></i>
				</a> <a onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
					href="<%=request.getContextPath()%>/user/delete?id=${item.id}"
					class="btn btn-sm btn-danger"> <i class="fa fa-trash-o"></i>
				</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>