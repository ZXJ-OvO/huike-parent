<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
    ${message} <br>

    对象 : ${user.id} --- ${user.name} --- ${user.age}


    if指令(条件判断):
    <#if name == 'Rose2'>
        name的值是Rose2
    <#else>
        name的值不是Rose2
    </#if>

    <br>

    list循环指令(循环遍历):

    <table>
        <tr>
            <th>编号</th>
            <th>ID</th>
            <th>姓名</th>
            <th>年龄</th>
        </tr>

        <#list dataList as u>
            <tr>
                <td>${u_index + 1}</td>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.age}</td>
            </tr>
        </#list>
    </table>

    注意事项: null处理.
    ${count!}
    ${count!"默认值"}

</body>
</html>
