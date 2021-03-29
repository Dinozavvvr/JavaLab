<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>

        /*Обнуление*/
        * {
            padding: 0;
            margin: 0;
            border: 0;
        }

        *, *:before, *:after {
            box-sizing: border-box;
        }

        :focus, :active {
            outline: none;
        }

        a:focus, a:active {
            outline: none;
        }

        nav, footer, header, aside {
            display: block;
        }

        html, body {
            height: 100%;
            width: 100%;
            line-height: 1;
            font-size: 14px;
            -ms-text-size-adjust: 100%;
            -moz-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
        }

        input, button, textarea {
            font-family: inherit;
        }

        input::-ms-clear {
            display: none;
        }

        button {
            cursor: pointer;
        }

        button::-moz-focus-inner {
            padding: 0;
            border: 0;
        }

        a, a:visited {
            text-decoration: none;
        }

        a:hover {
            text-decoration: none;
        }

        ul li {
            list-style: none;
        }

        img {
            vertical-align: top;
        }

        h1, h2, h3, h4, h5, h6 {
            font-size: inherit;
            font-weight: 400;
        }

        /*--------------------*/


        body {
            width: 100%;
            margin: 0;
            padding: 0;
            font-family: 'Montserrat', sans-serif;
        }

        .main {
            background: aliceblue;
            width: 100%;
            height: 400px;
        }

        .container {
            margin: auto;
        }

        .container__title {
            padding-top: 100px;
            text-align: center;
            font-size: 26px;
            font-weight: 700;
            color: #191970;
        }

        .container__text {
            margin: 14px 0;
            text-align: center;
            font-size: 16px;
            font-weight: 500;
        }

        .wrapper {
            width: 100%;
            margin: 0 auto;
            text-align: center;
        }

        .container__link {
            display: inline-block;
            font-size: 20px;
            text-decoration: none;
            color: black;
        }

        .container__link {
            position: relative;
            background: #F8E359;
            font-weight: normal;
            font-size: 24px;

            padding: 20px 50px;
            margin-top: 15px;

            transition: 0.3s all ease;
        }
        .container__link:link {
            color: black;
        }

        .container__link:hover {
            transform: translate(6px, 6px);
            box-shadow: -6px -6px 0 #191970;
        }

        .container__conclusion {
            color: #919090;
            font-size: 13px;
            text-align: center;
            margin-top: 24px;
        }

    </style>
    <title>Confirm Mail</title>
</head>
<body>
<div class="main">
    <div class="main__container  container">
        <h2 style=" font-family: 'Montserrat', sans-serif;" class="container__title">
            Thank you for registration!
        </h2>
        <p style=" font-family: 'Montserrat', sans-serif;" class="container__text">
            Follow the link to complete registration
        </p>
        <div class="wrapper">
            <a style=" font-family: 'Montserrat', sans-serif;" href="${server_url}/confirm?confirm_code=${confirm_code}" class="container__link">
                Finish
            </a>
        </div>
        <p style=" font-family: 'Montserrat', sans-serif;" class="container__conclusion">
            Fivegram company. Since 2020
        </p>
    </div>
</div>
</body>
</html>