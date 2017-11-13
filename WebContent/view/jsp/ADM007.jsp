<%@page import="common.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="view/css/style.css" rel="stylesheet" type="text/css" />
<title>ユーザ管理</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- Begin vung input-->
	<form
		action="${pageContext.request.contextPath}${Constant.URL_CHANGE_PASSWORD}"
		method="post">
		<center>
			<table class="tbl_input" border="0" style="padding-top: 50px">
				<c:forEach var="item" items="${listMessage}">
					<tr>
						<td class="errMsg" colspan="2"><c:out value="${item}"></c:out></td>
					</tr>
				</c:forEach>
				<tr align="left">
					<td class="lbl_left">New Password:</td>
					<td align="left"><input class="txBox" type="password"
						name="newPassword"
						value="<c:out value="${loginName}" escapeXml="true" />" size="22"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td class="lbl_left">Confirm Password:</td>
					<td align="left"><input class="txBox" type="password"
						name="confirmPassword" value="" size="22"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><input class="btn btn_wider" type="submit"
						value="Change Pass" /> <input class="btn" type="button"
						value="戻る" onclick="btnBack()" /></td>
				</tr>
			</table>
		</center>
		<input type="hidden" value="${userId }" name="userId" />
	</form>
	<script>
		function btnBack() {
			window.location.href = '${pageContext.request.contextPath}${Constant.URL_SHOW_DETAIL_USER}?userId=${userId}';
		}
	</script>
	<!-- End vung input -->
	<jsp:include page="footer.jsp" />
</body>
</html>