<!-- Alexander Hansen -->
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1>Address Book</h1>
				<a href="<%=request.getContextPath()%>/addEditAddress"
					class="btn btn-primary mb-2"> Add Contact </a>
				<jsp:include page="message.jsp" />
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<th>Action</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Address</th>
						<th>City</th>
						<th>State</th>
						<th>Zip</th>
					</tr>

					<c:if test="${empty addressList}">
						<tr>
							<td colspan="7">No Records Found!</td>
						</tr>
					</c:if>

					<c:if test="${not empty addressList }">
					
						<c:forEach var="address" items="${addressList}">
							<tr>
								<td class="text-center action">
								<a href="<%=request.getContextPath()%>/addEditAddress?addressId=${address.addressId}"
									class="mr-3"> <i class="fa fa-pencil text-warning"></i>
								</a> <a href="<%=request.getContextPath()%>/deleteAddress?addressId=${address.addressId}"> <i class="fa fa-trash text-danger"></i>
								</a></td>
								<td class="text-center action">${address.firstName}</td>
								<td class="text-center action">${address.lastName}</td>
								<td class="text-center action">${address.addressOne}<c:if
										test="${not empty addressTwo}">
										<br />${address.addressTwo}
									</c:if>
								</td>
								<td class="text-center action">${address.city}</td>
								<td class="text-center action">${address.state}</td>
								<td class="text-center action">${address.zip}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
