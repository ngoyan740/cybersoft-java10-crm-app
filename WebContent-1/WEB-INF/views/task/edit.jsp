<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 class="mb-4">Cập nhật công việc</h3>
<form method="post" action="<%=request.getContextPath()%>/task/edit">
	<div class="row">
		<div class="col-md-6">
			<input type="hidden" value="${ task.id }" name="id" />
			
			<div class="form-group">
				<label>Tên</label> <input type="text" value="${ task.name }"
					name="name" class="form-control" />
			</div>
			<div class="form-group">
				<label>Ngày bắt đầu</label> <input type="date" name="startDate"
					value="${ task.startDate }" class="form-control" />
			</div>
			<div class="form-group">
				<label>Ngày kết thúc</label> <input type="date" name="endDate"
					value="${ task.endDate }" class="form-control" />
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label>Trạng thái</label> <select class="form-control"
					name="statusId">
					<c:forEach items="${ status }" var="item">
						<option value="${ item.id }"
							${ task.statusId == item.id ? 'selected' : '' }>${ item.name }</option>
					</c:forEach>
				</select>
				<label>Người làm</label> <select class="form-control"
					name="userId">
					<c:forEach items="${ users }" var="item">
						<option value="${ item.id }"
							${ task.userId == item.id ? 'selected' : '' }>${ item.fullname }</option>
					</c:forEach>
				</select>
				<label>Dự án</label> <select class="form-control"
					name="projectId">
					<c:forEach items="${ projects }" var="item">
						<option value="${ item.id }"
							${ task.projectId == item.id ? 'selected' : '' }>${ item.name }</option>
					</c:forEach>
				</select>
			</div>

		</div>
		<div class="col-12 mt-3">
			<button type="submit" class="btn btn-success">Lưu lại</button>
			<a class="btn btn-secondary"
				href="<%=request.getContextPath()%>/task">Quay lại</a>
		</div>
	</div>
</form>