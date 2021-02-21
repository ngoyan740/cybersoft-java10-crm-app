<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-4">Thêm mới trạng thái công việc</h3>
<form method="post" action="<%=request.getContextPath()%>/status/add">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label>Tên</label> <input type="text" name="name" required="required"
					class="form-control" placeholder="name" />
			</div>
		</div>
		<div class="col-md-6">
			
		</div>
		<div class="col-12 mt-3">
			<button type="submit" class="btn btn-success">Lưu lại</button>
			<a class="btn btn-secondary" href="<%=request.getContextPath()%>/status">Quay lại</a>
		</div>
	</div>
</form>