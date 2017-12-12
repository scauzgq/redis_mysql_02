<%--
  Created by IntelliJ IDEA.
  User: scauzgq
  Date: 2017/12/7
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Redis Test</title>
  </head>
  <body>
  查询:
            <form action="<%=request.getContextPath() %>/user/selectUserByUserName" method="post">

              <input type="text" name="userName" />
              <input type="submit" value="Submit"/>
            </form>
 新增:
            <form action="<%=request.getContextPath() %>/user/updateUser" method="post">
              <input type="text" name="userName" />
              <input type="text" name="password" />
              <input type="submit" value="Submit"/>
            </form>
  </body>
</html>
