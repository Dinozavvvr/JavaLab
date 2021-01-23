<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--links -->
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/footer.css">
    <link rel="stylesheet" href="../css/newpost.css">
    <!--script-->
    <script src="../js/newpost.js"></script>
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
        <div class="content">
            <div class="content__card card">
                <div class="card__body">
                    <span class="person">
                        ${examplePost.people}
                    </span>
                    <img src="/media?path=${examplePost.fileName}" alt="image" class="card__image">
                    <ul class="card__info">
                        <li>${examplePost.place}</li>
                        <li>${examplePost.description}</li>
                        <li>Show comments</li>
                    </ul>
                </div>
            </div>
            <form action="/posting" method="post" class="content__form form card" id="form" enctype="multipart/form-data">
                <div class="card__body">
                    <img src="../images/Rectangle.jpg" alt="image" class="card__image" id="preview">
                    <label class="file-input-label" for="image" id="file-label">add photo</label>
                    <input type="file" class="file-input" id="image" name="image" onchange="loadFile(event)" accept="image/png, image/jpeg">
                    <ul class="form__inputs">
                        <li><input class="form__input" type="text" placeholder="Place" name="place" id="place"></li>
                        <li><input class="form__input" type="text" placeholder="Description" name="description" id="description"></li>
                        <li><input class="form__input" type="text" placeholder="People" name="people" id="people"></li>
                    </ul>
                </div>
            </form>
            <span class="button-shadow"></span>
            <button class="form__submit" type="button" onclick="submitForm()">
                Post
            </button>
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