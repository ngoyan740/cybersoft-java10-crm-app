<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-4">Cập nhật trạng thái công việc</h3>
<form method="post" action="<%=request.getContextPath()%>/status/edit">
	<div class="row">
		<div class="col-md-6">
			<input type="hidden" value="${ status.id }" name="id" />
			<div class="form-group">
				<label>Tên</label> <input type="text" value="${ status.name }"
					name="name" class="form-control" />
			</div>
		</div>
		<div class="col-md-6">
			
		</div>
		<div class="col-12 mt-3">
			<button type="submit" class="btn btn-success">Lưu lại</button>
			<a class="btn btn-secondary"
				href="<%=request.getContextPath()%>/status">Quay lại</a>
		</div>
	</div>
</form>