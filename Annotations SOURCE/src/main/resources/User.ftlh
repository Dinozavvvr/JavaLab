<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="${method}" action="${action}">
    <#list inputs as inp>
        <input type="${inp.type}" name="${inp.name}" placeholder="${inp.placeholder}">
    </#list>
</form>
</body>
</html>