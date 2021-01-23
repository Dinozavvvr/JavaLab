<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
    <h1>Hello user! Welcome to your profile</h1>
    <h2>${_csrf_token}</h2>

    <form action="/deleteAccount" method="post">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="submit" value="Delete account">
    </form>

</body>
</html>