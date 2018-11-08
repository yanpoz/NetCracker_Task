<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
</head>
<body>
<form action="servlet?id=<%= request.getParameter("id")%>" method="POST">
    Name: <input type="text" name="name">
    <br/>
    Ade: <input type="text" name="age"/>
    <br/>
    Email: <input type="text" name="email"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>