<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
    <link rel='stylesheet' href='style.css'>
</head>
<body>
<form action="edit?id=<%= request.getParameter("id")%>&tabl=<%= request.getParameter("tabl")%>" method="POST">
    Name: <input type="text" name="name">
    <br/>
    Progress: <input type="text" name="progress"/>
    <br/>
    Team lider id: <input type="text" name="lider"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>