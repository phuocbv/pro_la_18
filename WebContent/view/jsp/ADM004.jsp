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
	<jsp:include page="header.jsp" />
	<c:if test="${userInfor != null }">
		<form action="ADM006.html" method="post" name="inputform">
			<table class="tbl_input" border="0" width="75%" cellpadding="0"
				cellspacing="0">
				<tr>
					<th align="left">
						<div style="padding-left: 100px;">
							情報確認<br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください 
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
									<td align="left"><c:out value="${mstGroup.groupName }"
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
									<td align="left"><c:out
											value="${Common.convertToString(userInfor.birthdayYear, userInfor.birthdayMonth, userInfor.birthdayDay)}"
											escapeXml="true" /></td>
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
									<th colspan="2"><a href="javascript:void(0)">日本語能力</a></th>
								</tr>
								<tr>
									<td class="lbl_left">資格:</td>
									<td align="left"><c:out
											value="${mstJapan != null ? mstJapan.nameLevel : ''}"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">資格交付日:</td>
									<td align="left"><c:out
											value="${mstJapan != null ? Common.convertToString(userInfor.startYear, userInfor.startMonth, userInfor.startDay) : ''}"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">失効日:</td>
									<td align="left"><c:out
											value="${mstJapan != null ? Common.convertToString(userInfor.endYear, userInfor.endMonth, userInfor.endDay) : ''}"
											escapeXml="true"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">点数:</td>
									<td align="left"><c:out
											value="${mstJapan != null ? userInfor.total : ''}"
											escapeXml="true"></c:out></td>
								</tr>
							</table>

						</div>
					</td>
				</tr>
			</table>
			<div style="padding-left: 100px;">&nbsp;</div>
			<!-- Begin vung button -->
			<div style="padding-left: 45px;">
				<table border="0" cellpadding="4" cellspacing="0" width="300px">
					<tr>
						<th width="200px" align="center">&nbsp;</th>
						<td><input class="btn" type="submit" value="OK" /></td>
						<td><input class="btn" type="button" value="戻る"
							id="btnBackADM003" /></td>
					</tr>
				</table>
				<!-- End vung button -->
		</form>
	</c:if>
	<!-- End vung input -->
	<jsp:include page="footer.jsp" />
	<script>
		var btnBackADM003 = document.getElementById('btnBackADM003');
		btnBackADM003.addEventListener('click', redirectListUser);

		function redirectListUser() {
			window.location = '${pageContext.request.contextPath}${Constant.URL_ADD_USER_INPUT}';
		}
	</script>
</body>
</html>