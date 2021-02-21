<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h3 class="mb-4 text-center">Thêm mới quyền</h3>
<p class="text-center text-danger">${ message }</p>
<form method="post" action="<%=request.getContextPath()%>/role/add">
	<div class="row">
		<div class="col-md-6 m-auto">
			<div class="form-group">
				<label>Tên quyền</label> <input type="text" name="name" required="required"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Mô tả</label> <input type="text" name="desc" required="required"
					class="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">Lưu lại</button>
				<a class="btn btn-secondary" href="<%=request.getContextPath()%>/role">Quay lại</a>
			</div>
		</div>
	</div>
</form>