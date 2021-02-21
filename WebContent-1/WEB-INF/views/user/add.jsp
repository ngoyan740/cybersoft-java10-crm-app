<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-4">Thêm mới thành viên</h3>
<form method="post" action="<%=request.getContextPath()%>/user/add">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label>Email</label> <input type="text" name="email" required="required"
					class="form-control" placeholder="email" />
			</div>
			<div class="form-group">
				<label>Mật khẩu</label> <input type="password" name="password" required="required"
					class="form-control" placeholder="password" />
			</div>
			<div class="form-group">
				<label>Họ tên</label> <input type="text" name="fullname" required="required"
					class="form-control" placeholder="fullname" />
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label>Loại người dùng</label> <select class="form-control"
					name="roleId">
					<c:forEach items="${ roles }" var="item">
						<option value="${ item.id }">${ item.desc }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Avatar</label> <input type="text" name="avatar"
					class="form-control" placeholder="avatar" />
			</div>
		</div>
		<div class="col-12 mt-3">
			<button type="submit" class="btn btn-success">Lưu lại</button>
			<a class="btn btn-secondary" href="<%=request.getContextPath()%>/user">Quay lại</a>
		</div>
	</div>
</form>