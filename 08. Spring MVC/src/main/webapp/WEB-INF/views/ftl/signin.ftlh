<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--links -->
    <link rel="stylesheet" href="/resources/css/nav.css">
    <link rel="stylesheet" href="/resources/css/footer.css">
    <link rel="stylesheet" href="/resources/css/signup.css">
    <!--script-->
    <script src="/resources/js/newpost.js"></script>
    <title>Fivegram</title>
</head>
<body>
<div id="wrapper">
    <nav class="navigation">
        <ul>
            <li class="current"><a href="#" class="navigation__link">account</a></li>
            <li><a href="#" class="navigation__link">posts</a></li>
            <li><a href="#" class="navigation__link">logout</a></li>
            <li><a href="#" class="navigation__link logo">Fivegram</a></li>
        </ul>
    </nav>
    <div class="main">
        <div class="main__content">
            <form action="/signin" method="post" class="main__form form">
                <p class="form__title">Login</p>
                <input type="text" class="form__input" name="login" placeholder="login">
                <input type="password" class="form__input" name="password" placeholder="password">
                <input type="checkbox" class="form__checkbox" name="remember" id="remember">
                <label id="label" for="remember">remember me</label>
                <a class="form__link" href="/signup">I haven't got an account</a>
                <button class="form__submit" type="submit">
                    sign up
                </button>
            </form>
        </div>
    </div>
    <div class="footer">
        <ul>
            <li><a href="#" class="footer_link">about us</a></li>
            <li><a href="#" class="footer_link">facebook</a></li>
            <li><a href="#" class="footer_link">vk</a></li>
            <li><a href="#" class="footer_link">FG company</a></li>
            <li><a href="#" class="footer_link">since 2020</a></li>
        </ul>
    </div>
</div>
</body>
</html>