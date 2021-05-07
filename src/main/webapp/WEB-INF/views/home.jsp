<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
</head>
<body>

<P>  로그인에 성공하였습니다. ${serverTime}. </P>
</body>
</html>
<script type="text/javascript">
 self.location = "/board/list";
</script>