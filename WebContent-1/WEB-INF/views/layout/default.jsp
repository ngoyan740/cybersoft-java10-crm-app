<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title><dec:title /></title>
<jsp:include page="/WEB-INF/views/decorators/header.jsp" />
<dec:head />
</head>

<body>
<!-- Import thu vien /static/css/style.css khong hoat dong? Nen copy truc tiep tu file style.css vao html -->
	<div class="d-flex justify-content-between">
		<!-- Navigation -->
		<div style="width: 250px; min-height: 100vh; background: #446084;">
			<jsp:include page="/WEB-INF/views/decorators/leftbar.jsp" />
		</div>
		<!-- Left navbar-header -->
		<!-- <div class="admin-wrapper"> -->
		<div style="width: calc(100% - 250px);">

			<jsp:include page="/WEB-INF/views/decorators/topbar.jsp" />

			<!-- Left navbar-header end -->

			<!-- Page Content -->
			<section id="admin-content" class="p-3">
				<dec:body />
			</section>

		</div>


	</div>
	<!-- /.container-fluid -->
	<jsp:include page="/WEB-INF/views/decorators/footer.jsp" />

	<%-- <dec:getProperty property="page.scripts" /> --%>
</body>

</html>