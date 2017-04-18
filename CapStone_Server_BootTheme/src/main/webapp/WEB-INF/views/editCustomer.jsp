<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 객체와 form 의 데이터를 자동으로 맵핑 시켜주는 taglib --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Edit Product</h2>
		<p class="lead">Fill the below information to add a product.</p>
		
		<%-- modelAttribute 의 이름과 controller 에서 넘겨준 model 로 넘기는 객체의 이름을 동일하게 해야 한다. --%>
		<sf:form action="${pageContext.request.contextPath}/admin/customerInventory/editCustomer" method="post" modelAttribute="customer"
		enctype="multipart/form-data">
			
			<!-- edit 버튼을 눌렀을 때는 id가 넘어오지만 submit 할때 넘어오는 id 값이 없어서 0이 들어가게 되므로 hidden 으로 id 값을 받아서 넘겨준다!!! -->
			<sf:hidden path="id"/>
			
			<div class="form-group">
				<label for="date">Date</label> 
				<sf:input path="date" id="date" class="form-control" />
			</div>

			<div class="form-group">
				<label for="count">Count</label> 
				<sf:input path="count" id="count" class="form-control" />
				<sf:errors path="count" cssStyle="color:#ff0000"/>
				
			</div>
			
			
			
			<input type="submit" class="btn btn-default" value="Submit">
			<a href="<c:url value="/admin/customerInventory"/>" class="btn btn-default">Cancel</a>


		</sf:form>
	</div>
</div>