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
	<form
		action="${pageContext.request.contextPath}${Constant.URL_ADD_USER_INPUT }"
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
									name="${UserInfor.LOGIN_NAME }" value="${userInfor.loginName }"
									size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="${UserInfor.GROUP_ID }">
										<option value="0">選択してくださいグループ</option>
										<c:forEach var="group" items="${listGroup}">
											<option value="${group.groupId }"
												${group.groupId == userInfor.groupId ? 'selected' : '' }>${group.groupName }</option>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.FULL_NAME }" value="${userInfor.fullName }"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.FULL_NAME_KANA }"
									value="${userInfor.fullNameKana }" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="${UserInfor.BIRTHDAY_YEAR }">
										<c:forEach var="item" items="${listYear}">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>年 <select name="${UserInfor.BIRTHDAY_MONTH }">
										<c:forEach var="item" items="${listMonth }">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>月 <select name="${UserInfor.BIRTHDAY_DAY }">
										<c:forEach var="item" items="${listDay }">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.EMAIL }" value="${userInfor.email }"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.TEL }" value="${userInfor.tel }" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="${UserInfor.PASSWORD }" value="${userInfor.password }"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="${UserInfor.CONFIRM_PASSWORD }"
									value="${userInfor.confirmPassword }" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="javascript:void(0)">日本語能力</a></th>
							</tr>
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="${UserInfor.CODE_LEVEL }">
										<option value="0">選択してください資格</option>
										<c:forEach var="item" items="${listJapan}">
											<option value="${item.codeLevel}">${item.nameLevel}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="${UserInfor.START_YEAR }">
										<c:forEach var="item" items="${listYear}">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>年 <select name="${UserInfor.START_MONTH }">
										<c:forEach var="item" items="${listMonth }">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>月 <select name="${UserInfor.START_DAY }">
										<c:forEach var="item" items="${listDay }">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="${UserInfor.END_YEAR }">
										<c:forEach var="item" items="${listYear}">
											<option value="${item}">${item}</option>
										</c:forEach>
										<option value="${listYear.get(listYear.size() - 1) + 1}">${listYear.get(listYear.size() - 1) + 1}</option>
								</select>年 <select name="${UserInfor.END_MONTH }">
										<c:forEach var="item" items="${listMonth }">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>月 <select name="${UserInfor.END_DAY }">
										<c:forEach var="item" items="${listDay }">
											<option value="${item}">${item}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="${UserInfor.TOTAL }" value="${userInfor.total }" size="5"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
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
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button" value="戻る"
						id="btnBackListUser" /></td>
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->
	<script>
		var btnBackListUser = document.getElementById('btnBackListUser');
		btnBackListUser.addEventListener('click', redirectListUser);

		function redirectListUser() {
			window.location
					.replace('${pageContext.request.contextPath}${Constant.URL_LIST_USER}');
		}
	</script>
	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>
</html>