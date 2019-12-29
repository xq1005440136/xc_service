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
            <td <#if stu.name="小明">style="background-color: aqua"</#if> >${stu.name}</td>
            <td>${stu.age}</td>
        </tr>
    </#list>
</table>
<#--遍历数据模型中的map-->
<#--第一种方法-->
<span>姓名：${stuMap['stu1'].name}</span><br>
<span>年龄：${stuMap['stu1'].age}</span><br>
<#--第二种方法-->
<span>姓名：${stuMap.stu1.name}</span><br>
<span>年龄：${stuMap.stu1.age}</span><br>
<#--第三种方法-->
<#list stuMap?keys as key>
    姓名:<span>${stuMap[key].name}</span><br>
    年龄:<span>${stuMap[key].age}</span><br>
</#list>
</body>
</html>