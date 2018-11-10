<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
    <link rel='stylesheet' href='style.css'>
</head>
<body>

<h1>Are you sure ?</h1>

<table>
    <tr>
        <td>
        <a href="delete?id=<%= request.getParameter("id")%>">Delete</a></td>
        <td>
        <a href="servlet?id=0">Cancel</a></td>
    </tr>
</table>
</body>
</html>