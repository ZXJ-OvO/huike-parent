<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style>
        body {
            text-align: center;
        }

        table {
            margin: 0 auto; /* 居中 */
            width: 70%; /* 宽度为70% */
            border-collapse: collapse; /* 边框合并 */
        }

        th, td {
            border: 1px solid #000; /* 边框为单实线边框 */
            padding: 5px; /* 内边距 */
        }

        th {
            background-color: #CCCCCC; /* 表头背景色 */
            color: #000; /* 表头文字颜色 */
            padding-top: 10px; /* 上边距增加 */
            padding-bottom: 10px; /* 下边距增加 */
        }

        h4 {
            text-align: center; /* 文本居中 */
        }
    </style>
</head>
<body>
<h4>你好，${adminName}，以下是本月公司运营数据，请及时查收，数据有任何问题，请联系开发人员</h4>

<p>&nbsp;</p> <!-- 空行 -->

<table>
    <thead>
    <tr>
        <th>日期</th>
        <th>线索新增</th>
        <th>商机新增</th>
        <th>合同新增</th>
        <th>销售额</th>
    </tr>
    </thead>
    <tbody>
        <#list dataRows as row>
            <tr>
                <td>${row.date}</td>
                <td>${row.newClueCount}</td>
                <td>${row.newBusinessCount}</td>
                <td>${row.newContractCount}</td>
                <td>${row.salesCount}</td>
            </tr>
        </#list>
    </tbody>
</table>
</body>
</html>
