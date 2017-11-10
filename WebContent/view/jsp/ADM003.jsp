<%@page import="common.Constant"%>
<%@ page import="entity.UserInfor"%>
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
	<!-- Begin vung input-->
	<%-- 	<form
		action="${pageContext.request.contextPath}${Constant.URL_ADD_USER_VALIDATE }?type=${Constant.TYPE_ADM003}"
		method="post" name="inputform"> --%>
	<form
		action="${pageContext.request.contextPath}${Constant.URL_ADD_USER_VALIDATE }?type=${Constant.TYPE_ADM003}"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<c:forEach var="item" items="${listError}">
				<tr>
					<td class="errMsg">
						<div style="padding-left: 120px">${item }</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.LOGIN_NAME }"
									value="<c:out value="${userInfor.loginName }" escapeXml="true" />"
									size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';"
									${userId != null ? 'readonly' : ''}/></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="${UserInfor.GROUP_ID }">
										<option value="0">選択してください</option>
										<c:forEach var="group" items="${listGroup}">
											<option value="${group.groupId }"
												${group.groupId == userInfor.groupId ? 'selected' : '' }>${group.groupName }</option>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.FULL_NAME }"
									value="<c:out value="${userInfor.fullName }" escapeXml="true" />"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.FULL_NAME_KANA }"
									value="<c:out value="${userInfor.fullNameKana }" escapeXml="true"/>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="${UserInfor.BIRTHDAY_YEAR }">
										<c:set var="birthdayYear"
											value="${userInfor.birthdayYear != null ? userInfor.birthdayYear : currentYear}"></c:set>
										<c:forEach var="item" items="${listYear}">
											<option value="${item}"
												${item == birthdayYear ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>年 <select name="${UserInfor.BIRTHDAY_MONTH }">
										<c:set var="birthdayMonth"
											value="${userInfor.birthdayMonth != null ? userInfor.birthdayMonth : currentMonth }"></c:set>
										<c:forEach var="item" items="${listMonth }">
											<option value="${item}"
												${item == birthdayMonth ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>月 <select name="${UserInfor.BIRTHDAY_DAY }">
										<c:set var="birthdayDay"
											value="${userInfor.birthdayDay != null ? userInfor.birthdayDay : currentDay}"></c:set>
										<c:forEach var="item" items="${listDay }">
											<option value="${item}"
												${item == birthdayDay ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.EMAIL }"
									value="<c:out value="${userInfor.email }" escapeXml="true"/>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.TEL }"
									value="<c:out value="${userInfor.tel }" escapeXml="true"/>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="${UserInfor.PASSWORD }" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="${UserInfor.CONFIRM_PASSWORD }" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<!-- level japannes -->
							<tr>
								<th align="left" colspan="2"><a href="javascript:void(0)"
									onclick="formLevelJapan()" id="linkLevelJapannes">日本語能力</a></th>
							</tr>
							<c:set var="checkCodeLevel"
								value="${(userInfor.codeLevel != null && userInfor.codeLevel != '0') }"></c:set>
							<tr class="fieldToggle"
								style="display: ${checkCodeLevel ? 'table-row' : 'none'}">
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="${UserInfor.CODE_LEVEL }"
									id="selectCodeLevel">
										<option value="0">選択してください</option>
										<c:forEach var="item" items="${listJapan}">
											<option value="${item.codeLevel}"
												${item.codeLevel == userInfor.codeLevel ? 'selected' : '' }>${item.nameLevel}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr class="fieldToggle"
								style="display: ${checkCodeLevel ? 'table-row' : 'none'}">
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="${UserInfor.START_YEAR }">
										<c:set var="startYear"
											value="${userInfor.startYear != null ? userInfor.startYear : currentYear}"></c:set>
										<c:forEach var="item" items="${listYear}">
											<option value="${item}"
												${item == startYear ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>年 <select name="${UserInfor.START_MONTH }">
										<c:set var="startMonth"
											value="${userInfor.startMonth != null ? userInfor.startMonth : currentMonth}"></c:set>
										<c:forEach var="item" items="${listMonth }">
											<option value="${item}"
												${item == startMonth ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>月 <select name="${UserInfor.START_DAY }">
										<c:set var="startDay"
											value="${userInfor.startDay != null ? userInfor.startDay : currentDay}"></c:set>
										<c:forEach var="item" items="${listDay }">
											<option value="${item}"
												${item == startDay ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr class="fieldToggle"
								style="display: ${checkCodeLevel ? 'table-row' : 'none'}">
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="${UserInfor.END_YEAR }">
										<c:set var="endYear"
											value="${userInfor.endYear != null ? userInfor.endYear : expireYear}"></c:set>
										<c:forEach var="item" items="${listExpireYear}">
											<option value="${item}" ${item == endYear ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>年 <select name="${UserInfor.END_MONTH }">
										<c:set var="endMonth"
											value="${userInfor.endMonth != null ? userInfor.endMonth : expireMonth}"></c:set>
										<c:forEach var="item" items="${listMonth }">
											<option value="${item}"
												${item == endMonth ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>月 <select name="${UserInfor.END_DAY }">
										<c:set var="endDay"
											value="${userInfor.endDay != null ? userInfor.endDay : expireDay}"></c:set>
										<c:forEach var="item" items="${listDay }">
											<option value="${item}" ${item == endDay ? 'selected' : '' }>${item}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr class="fieldToggle"
								style="display: ${checkCodeLevel ? 'table-row' : 'none'}">
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.TOTAL }"
									value="<c:out value="${userInfor.total }" escapeXml="true" />"
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<c:if test="${userId != null }">
			<input type="hidden" value="${userId }" name="userId" />
		</c:if>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="backListUser()" id="btnBackListUser" /></td>
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->
	<script>
		function backListUser() {
			window.location.href = '${urlBack}';
		}
	</script>
	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
	<script type="text/javascript" src="view/js/js.js"></script>
</body>
</html>