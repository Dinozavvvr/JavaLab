<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>
<h1>${_csrf_token}</h1>
    <form action="/login" method="POST">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <label for="username">Name</label><br>
        <input type="text" name="username" id="username"><br>
        <label for="password">Password</label><br>
        <input type="password" name="password" id="password"><br>
        <input type="submit" value="Go!"><br>
    </form>
</body>
</html>