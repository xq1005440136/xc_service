<!DOCTYPE html>
<html>
<head>
        <meta charset=utf‐8">
        <title>Hello World!</title>
</head>
<body>
Hello ${name}
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index+1}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
        </tr>
    </#list>
</table>
</body>
</html>