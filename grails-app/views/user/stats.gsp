<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Stats - Hubbub</title>
</head>

<body>

<div>
    <g:pieChart type="3d"
                title='Posts Per Day'
                size="${[600, 200]}"
                labels="${postsOnDay.keySet()}"
                dataType='text'
                data='${postsOnDay.values().asList()}' />
</div>

</body>
</html>