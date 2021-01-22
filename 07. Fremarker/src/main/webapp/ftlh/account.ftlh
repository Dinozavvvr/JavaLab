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
    <link rel="stylesheet" href="../css/account.css">
    <!--script-->
    <script src="../js/newpost.js"></script>
    <script>
        function show(id) {
            document.getElementById(id).classList.toggle('active');
        }
    </script>
    <title>Fivegram</title>
</head>
<body>
<div id="wrapper">
    <a href="top"></a>
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
            <div class="main__profile profile">
                <img src="../images/justin.jpg" alt="profile-image" class="profile__image">
                <div class="profile__info">
                    <div class="profile__main-info">
                        <p class="profile__login">${login}</p>
                        <p class="profile__email">${email}</p>
                    </div>
                    <div class="profile__edit">
                        <p class="profile__edit-text">edit profile</p>
                    </div>
                    <ul class="profile__statistic">
                        <li><a class="profile__link" href="#"><span>9</span>posts</a></li>
                        <li><a class="profile__link" href="#"><span>4k</span>subscribers</a></li>
                        <li><a class="profile__link" href="#"><span>256</span>subscriptions</a></li>
                    </ul>
                </div>
            </div>
            <div class="main__posts posts">
                <p class="posts__title">My posts</p>
                <div class="posts__grid">
                    <#assign k = 0>
                    <#list posts as post>
                        <#assign k += 1>
                        <div class="posts__card-image card-image" onclick="show(${k})">
                            <img src="/media?path=${post.fileName}" alt="image" class="card-image__image">
                        </div>
                        <div class="item-wrapper" id="${k}">
                            <div class="posts__card card">
                                <div class="close" onclick="show(${k})">
                                </div>
                                <div class="card__body">
                                    <span class="person">
                                        ${post.people}
                                    </span>
                                    <img src="/media?path=${post.fileName}" alt="image" class="card__image">
                                    <ul class="card__info">
                                        <li>${post.place}</li>
                                        <li>${post.description}</li>
                                        <li>Show comments</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
                <div class="posts__to-top">
                    <a href="#top">up</a>
                </div>
            </div>
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
        <a href="/posting" class="footer_link new-post">new post</a>
    </div>
</div>
</body>
</html>