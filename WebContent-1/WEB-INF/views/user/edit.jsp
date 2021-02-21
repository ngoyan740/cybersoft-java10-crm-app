<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-4">Cập nhật thành viên</h3>
<form method="post" action="<%=request.getContextPath()%>/user/edit">
	<div class="row">
		<div class="col-md-6">
			<input type="hidden" value="${ user.id }" name="id" />
			<div class="form-group">
				<label>Email</label> <input type="text" value="${ user.email }"
					name="email" class="form-control" />
			</div>
			<div class="form-group">
				<label>Mật khẩu</label> <input type="password" name="password"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Họ tên</label> <input type="text" name="fullname"
					value="${ user.fullname }" class="form-control" />
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group"
				<c:if test="${( (USER_LOGIN.fullname != item.userName) 
				and (USER_LOGIN.roleName != 'ROLE_ADMIN') )}">style="display:none"</c:if>>
				<label>Loại người dùng</label> <select class="form-control"
					name="roleId">
					<c:forEach items="${ roles }" var="item">
						<option value="${ item.id }"
							${ user.roleId == item.id ? 'selected' : '' }>${ item.desc }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Avatar</label> <input type="text" value="${ user.avatar }"
					name="avatar" class="form-control" />
			</div>
		</div>
		<div class="col-12 mt-3">
			<button type="submit" class="btn btn-success">Lưu lại</button>
			<a class="btn btn-secondary"
				href="<%=request.getContextPath()%>/user">Quay lại</a>
		</div>
	</div>
</form>