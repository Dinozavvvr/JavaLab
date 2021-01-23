<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body style="font-family: 'JetBrains Mono', monospace">
    <h1>Dear, ${user.username}, Welcome to your profile!</h1>
    <h4>csrf token: ${_csrf_token}</h4>

    <form action="/deleteAccount" method="post">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="submit" value="delete account">
    </form>
</body>
</html>