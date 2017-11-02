<%@page import="properties.ConfigProperties"%>
<%@page import="properties.MessageProperties"%>
<%@page import="common.ConstantProperties"%>
<%@page import="common.Constant"%>
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
	<!-- Begin vung dieu kien tim kiem -->
	<form action="listUser.do?type=search" method="post" name="mainform">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="full_name"
								value="<c:out value="${sessionScope.CONDITION_STORE.fullName}" escapeXml="true" />"
								size="20" onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:forEach var="group" items="${listGroup}">
										<c:choose>
											<c:when
												test="${group.groupId == sessionScope.CONDITION_STORE.groupId}">
												<option value="${group.groupId }" selected="selected">${group.groupName }</option>
											</c:when>
											<c:otherwise>
												<option value="${group.groupId }">${group.groupName }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加"
								id="btnRedirectAddUser" /></td>
						</tr>
						<script>
							var btnRedirectAddUser = document
									.getElementById('btnRedirectAddUser');
							btnRedirectAddUser.addEventListener('click',
									redirectAddUser);

							function redirectAddUser() {
								/* window.location
										.replace('${pageContext.request.contextPath}${Constant.URL_ADD_USER_INPUT}'); */
								window.location = '${pageContext.request.contextPath}${Constant.URL_ADD_USER_INPUT}';
							}
						</script>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>

	<c:choose>
		<c:when test="${not empty listUser}">
			<!-- Begin vung hien thi danh sach user -->
			<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
				width="80%">
				<c:set var="currentPage"
					value="${sessionScope.CONDITION_STORE.page}"></c:set>
				<c:set var="sortType"
					value="${sessionScope.CONDITION_STORE.sortType}"></c:set>
				<c:set var="sortByFullName"
					value="${sessionScope.CONDITION_STORE.sortByFullName}"></c:set>
				<c:set var="sortByCodeLevel"
					value="${sessionScope.CONDITION_STORE.sortByCodeLevel }"></c:set>
				<c:set var="sortByEndDate"
					value="${sessionScope.CONDITION_STORE.sortByEndDate}"></c:set>
				<c:set var="paramSort" value="page=1&type=sort"></c:set>
				<tr class="tr2">
					<th align="center" width="20px">ID</th>
					<th align="left">氏名 <a
						href="listUser.do?${paramSort}
				&sortByFullName=${sessionScope.CONDITION_STORE.sortByFullName == Constant.ASC ? Constant.DESC : Constant.ASC}
				&sortByCodeLevel=${sortByCodeLevel}
				&sortByEndDate=${sortByEndDate}&sortType=${Constant.SORT_BY_FULL_NAME}">
							${sessionScope.CONDITION_STORE.sortByFullName == Constant.ASC ? '▲▽' : '△▼'}
					</a>
					</th>
					<th align="left">生年月日</th>
					<th align="left">グループ</th>
					<th align="left">メールアドレス</th>
					<th align="left" width="70px">電話番号</th>
					<th align="left">日本語能力 <a
						href="listUser.do?${paramSort}
				&sortByFullName=${sortByFullName}
				&sortByCodeLevel=${sessionScope.CONDITION_STORE.sortByCodeLevel == Constant.ASC ? Constant.DESC : Constant.ASC}
				&sortByEndDate=${sortByEndDate}
				&sortType=${Constant.SORT_BY_CODE_LEVEL}">
							${sessionScope.CONDITION_STORE.sortByCodeLevel == Constant.ASC ? '▲▽' : '△▼'}
					</a>
					</th>
					<th align="left">失効日 <a
						href="listUser.do?${paramSort}
				&sortByFullName=${sortByFullName}
				&sortByCodeLevel=${sortByCodeLevel}
				&sortByEndDate=${sessionScope.CONDITION_STORE.sortByEndDate == Constant.ASC ? Constant.DESC : Constant.ASC}
				&sortType=${Constant.SORT_BY_END_DATE}">
							${sessionScope.CONDITION_STORE.sortByEndDate == Constant.ASC ? '▲▽' : '△▼'}
					</a>
					</th>
					<th align="left">点数</th>
				</tr>

				<c:forEach var="item" items="${listUser}">
					<tr>
						<td align="right"><a href="ADM005.html">${item.userId}</a></td>
						<td>${item.fullName}</td>
						<td align="center"><fmt:formatDate pattern="yyyy/MM/dd"
								value="${item.birthday}" /></td>
						<td>${item.groupName}</td>
						<td>${item.email}</td>
						<td>${item.tel}</td>
						<td>${item.nameLevel }</td>
						<td align="center"><fmt:formatDate pattern="yyyy/MM/dd"
								value="${item.endDate}" /></td>
						<td align="right">${item.total}</td>
					</tr>
				</c:forEach>
			</table>
			<!-- End vung hien thi danh sach user -->

			<!-- Begin vung paging -->
			<c:set var="paramPaging"
				value="type=paging
			&sortByFullName=${sortByFullName}
			&sortByCodeLevel=${sortByCodeLevel}
			&sortByEndDate=${sortByEndDate}
			&sortType=${sortType}"></c:set>
			<c:set var="totalPaging"
				value="${ConfigProperties.getValue(ConstantProperties.NUMBER_PAGE_IN_PAGE) }"></c:set>
			<c:set var="limit"
				value="${ConfigProperties.getValue(ConstantProperties.LIMIT_RECORD) }"></c:set>
			<c:if test="${not empty listPaging}">
				<table>
					<tr>
						<td class="lbl_paging"><c:if
								test="${currentPage > totalPaging}">
								<a
									href="listUser.do?page=${listPaging.get(0) - 1}&${paramPaging}"><<</a>&nbsp;
			</c:if> <c:forEach var="item" items="${listPaging}">
								<c:choose>
									<c:when test="${item == currentPage }">
							${item} &nbsp;
						</c:when>
									<c:otherwise>
										<a href="listUser.do?page=${item}&${paramPaging}">${item}</a> &nbsp;
						</c:otherwise>
								</c:choose>

							</c:forEach> <c:if
								test="${listPaging.get(listPaging.size() - 1) % totalPaging == 0 &&  totalUser > listPaging.get(listPaging.size() - 1) * limit}">
								<a
									href="listUser.do?page=${listPaging.get(listPaging.size() - 1) + 1}&${paramPaging}">>></a>

							</c:if></td>
					</tr>
				</table>
			</c:if>
			<!-- End vung paging -->
		</c:when>
		<c:otherwise>
			<div align="center">
				${MessageProperties.getValue(ConstantProperties.MSG005)}</div>
		</c:otherwise>
	</c:choose>
	<jsp:include page="footer.jsp" />
</body>
</html>
