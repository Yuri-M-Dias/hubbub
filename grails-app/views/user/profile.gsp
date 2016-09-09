<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Profile - Hubbub</title>
</head>

<body>

<div class="profilePic">
    <g:if test="${profile.photo}">
        <img src="<g:createLink controller='image' action='renderImage' id='${userId}'/>"/>
    </g:if>
    <p>Profile for <strong>${profile.fullName}</strong></p>
    <p>Bio: ${profile.bio}</p>
</div>

</body>
</html>