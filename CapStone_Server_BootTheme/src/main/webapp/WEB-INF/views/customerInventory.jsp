<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Customer Inventory Page</h2>
		<p class="lead">고객현황</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>id</th>
					<th>date</th>
					<th>count</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${customers}">
					<tr>
						<td>${customer.id}</td>
						<td>${customer.date}</td>
						<td>${customer.count}</td>
						<td><a
							href="<spring:url value="/admin/customerInventory/deleteCustomer/${customer.id}"/>"><span
								class="glyphicon glyphicon-remove"></span></a> <a
							href="<spring:url value="/admin/customerInventory/editCustomer/${customer.id}"/>"><span
								class="glyphicon glyphicon-pencil"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="<c:url value="/admin/customerInventory/addCustomer"/>"
			class="btn btn-primary">Add Customer</a>
	</div>
</div>