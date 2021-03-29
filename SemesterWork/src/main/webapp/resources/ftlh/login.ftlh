<!doctype html>
<html lang="en">
<#import 'spring.ftl' as spring/>
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
    <script src="../js/newpost.js"></script>
    <title>Fivegram</title>
</head>
<body>
<div id="wrapper">
    <nav class="navigation">
        <ul>
            <li class="current"><a href="#" class="navigation__link"><@spring.message 'common.nav.account'/></a></li>
            <li><a href="#" class="navigation__link"><@spring.message 'common.nav.posts'/></a></li>
            <li><a href="#" class="navigation__link"><@spring.message 'common.nav.logout'/></a></li>
            <li><a href="#" class="navigation__link logo">Fivegram</a></li>
        </ul>
    </nav>
    <div class="main">
        <div class="main__content">
            <form action="/signin" method="post" class="main__form form">
                <p class="form__title"><@spring.message 'login_page.login.title'/></p>
                <input type="text" class="form__input" name="login" placeholder="<@spring.message 'form.login.placeholder'/>">
                <input type="password" class="form__input" name="password" placeholder="<@spring.message 'form.password.placeholder'/>">
                <input type="checkbox" class="form__checkbox" name="remember" id="remember">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <label id="label" for="remember"><@spring.message 'form.remember.placeholder'/></label>
                <a class="form__link" href="/signup"><@spring.message 'login_page.login.go.sign_up'/></a>
                <button class="form__submit" type="submit">
                    <@spring.message 'login_page.login.submit'/>
                </button>
            </form>
        </div>
    </div>
    <div class="footer">
        <ul>
            <li><a href="?lang=ru" class="footer_link">ru</a></li>
            <li><a href="?lang=en" class="footer_link">en</a></li>
            <li><a href="#" class="footer_link"><@spring.message 'common.footer.about_us'/></a></li>
            <li><a href="#" class="footer_link">facebook</a></li>
            <li><a href="#" class="footer_link">vk</a></li>
            <li><a href="#" class="footer_link">FG company</a></li>
            <li><a href="#" class="footer_link">since 2020</a></li>
        </ul>
    </div>
</div>
</body>
</html>