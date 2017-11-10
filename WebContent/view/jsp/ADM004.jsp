<%@page import="common.Constant"%>
<%@page import="common.Common"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="view/css/style.css" rel="stylesheet" type="text/css" />
<title>ユーザ管理</title>
</head>
<body>


	<form onsubmit="return submitForm();">
		<input type="text" border="0" name="submit" />
		<button value="submit">submit</button>
	</form>
	<jsp:include page="header.jsp" />
	<c:if test="${userInfor != null }">
		<%-- <form
			action="${pageContext.request.contextPath}${Constant.URL_ADD_USER_OK }"
			method="post" name="inputform"> --%>
		<form action="${urlSubmit}" method="${method }" name="inputform">
			<table class="tbl_input" border="0" width="75%" cellpadding="0"
				cellspacing="0">
				<tr>
					<th align="left">
						<div style="padding-left: 100px;">
							情報確認 <br>入力された情報をＯＫボタンクリックでＤＢへ保存してください 
						</div>
						<div style="padding-left: 100px;">&nbsp;</div>
					</th>
				</tr>
				<tr>
					<td align="left">
						<div style="padding-left: 100px;">
							<table border="1" width="70%" class="tbl_input" cellpadding="4"
								cellspacing="0">
								<tr>
									<td class="lbl_left">アカウント名:</td>
									<td align="left"><c:out value="${userInfor.loginName }"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">グループ:</td>
									<td align="left"><c:out value="${userInfor.groupName }"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">氏名:</td>
									<td align="left"><c:out value="${userInfor.fullName }"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">カタカナ氏名:</td>
									<td align="left"><c:out value="${userInfor.fullNameKana }"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">生年月日:</td>
									<td align="left"><fmt:formatDate pattern="yyyy/MM/dd"
											value="${userInfor.birthday}" /></td>
								</tr>
								<tr>
									<td class="lbl_left">メールアドレス:</td>
									<td align="left"><c:out value="${userInfor.email }"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">電話番号:</td>
									<td align="left"><c:out value="${userInfor.tel }"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<th colspan="2"><a href="javascript:void(0)"
										onclick="formLevelJapan()">日本語能力</a></th>
								</tr>
								<c:set var="checkCodeLevel"
									value="${(userInfor.codeLevel != null && userInfor.codeLevel != '0') }"></c:set>
								<tr class="fieldToggle" style="display: none">
									<td class="lbl_left">資格:</td>
									<td align="left"><c:out value="${userInfor.nameLevel}"
											escapeXml="true"></c:out></td>
								</tr>
								<tr class="fieldToggle" style="display: none">
									<td class="lbl_left">資格交付日:</td>
									<td align="left"><fmt:formatDate pattern="yyyy/MM/dd"
											value="${userInfor.startDate}" /></td>
								</tr>
								<tr class="fieldToggle" style="display: none">
									<td class="lbl_left">失効日:</td>
									<td align="left"><fmt:formatDate pattern="yyyy/MM/dd"
											value="${userInfor.endDate}" /></td>
								</tr>
								<tr class="fieldToggle" style="display: none">
									<td class="lbl_left">点数:</td>
									<td align="left"><c:out value="${userInfor.total}"
											escapeXml="true"></c:out></td>
								</tr>
							</table>

						</div>
					</td>
				</tr>
			</table>
			<div style="padding-left: 100px;">&nbsp;</div>
			<div style="padding-left: ${userId != null ? '100px' : '45px'}">
				<table border="0" cellpadding="4" cellspacing="0" width="300px">
					<tr>
						<th width="200px" align="center">&nbsp;</th>
						<c:choose>
							<c:when test="${userId != null}">
								<input type="hidden" value="${Constant.TYPE_ADM005 }"
									name="type" />
								<input type="hidden" value="${userId}" name="userId" />
								<td><input class="btn" type="submit" value="編集" /></td>
								<td><input class="btn" type="button" value="削除"
									onclick="redirectEditUer()" /></td>

							</c:when>
							<c:otherwise>
								<input type="hidden" value="${keySession}"
									name="${Constant.KEY_SESSION }" />
								<td><input class="btn" type="submit" value="OK" /></td>
							</c:otherwise>
						</c:choose>
						<td><input class="btn" type="button" value="戻る"
							onclick="btnBack()" /></td>
					</tr>
				</table>
			</div>

			<!-- Begin vung button -->

			<!-- End vung button -->
		</form>
	</c:if>
	<!-- End vung input -->
	<jsp:include page="footer.jsp" />
	<script>
		/* function backListUser() {
			window.location.href = '${pageContext.request.contextPath}${Constant.URL_LIST_USER}?type=back';
		}

		function backAddUser() {
			window.location.href = '${pageContext.request.contextPath}${Constant.URL_ADD_USER_INPUT}'
					+ '?type=${Constant.TYPE_ADM004}&key=${keySession}';
		} */

		function redirectEditUer() {
			window.location.href = '${pageContext.request.contextPath}${Constant.URL_EDIT_USER_INPUT}'
					+ '?type=${Constant.TYPE_ADM005}&userId=${userId}';
		}

		function btnBack() {

			window.location.href = '${urlBack}';
		}

		function confirm() {
			return confirm("sdsd");
		}
	</script>
	<script>
		function submit() {
			return confirm('Do you really want to submit the form?');
		}

		function submitForm() {
			return confirm('Do you really want to submit the form?');
		}
	</script>
	<script type="text/javascript" src="view/js/js.js"></script>
</body>
</html>