
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Customers</h2>
		<p class="lead">흡연 기록!</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>id</th>
					<th>date</th>
					<th>count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${customers}">
					<tr>
						<td>${customer.id}</td>
						<td>${customer.date}</td>
						<td>${customer.count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

