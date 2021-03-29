<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
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
    <script src="/resources/js/validation.js"></script>
    <style>
        .input_error {
            background: red;
        }
        .input_error::placeholder {
            color: white;
        }
    </style>
    <title>Fivegram</title>
</head>
<body onload="validation()">
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
            <@spring.bind "userSignUpForm"/>
            <form action="/signup" method="post" class="main__form form">
                <p class="form__title"><@spring.message 'sign_up_page.registration.title'/></p>
                <#--eamil-->
                <#assign emailPlaceholder><@spring.message 'form.email.placeholder'/></#assign>
                <@spring.formInput path="userSignUpForm.email" attributes="class=\"form__input email\" placeholder=\"${emailPlaceholder}\""/>
                <@spring.showErrors separator="" classOrStyle="email_error"/>
                <#--login-->
                <#assign loginPlaceholder><@spring.message 'form.login.placeholder'/></#assign>
                <@spring.formInput path="userSignUpForm.login" attributes="class=\"form__input\" placeholder=\"${loginPlaceholder}\""/>
                <@spring.showErrors separator="" classOrStyle="login_error"/>
                <#--password-->
                <#assign passwordPlaceholder><@spring.message 'form.password.placeholder'/></#assign>
                <@spring.formPasswordInput path="userSignUpForm.password" attributes="class=\"form__input\" placeholder=\"${passwordPlaceholder}\""/>
                <@spring.showErrors separator="" classOrStyle="password_error"/>
                <#--csrf token-->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <a class="form__link" href="/signin"><@spring.message 'sign_up_page.registration.go.login'/></a>
                <button class="form__submit" type="submit">
                    <@spring.message 'sign_up_page.registration.submit'/>
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