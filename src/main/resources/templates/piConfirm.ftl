<style>
    table.gridtable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
    }
    table.gridtable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #dedede;
    }
    table.gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #ffffff;
    }
</style>
<div style="text-align: center;width: 100%">
    <h3>项目名称: ${projectName}</h3>
    <table class="gridtable">
        <tr>
            <td>Construct name / plasmid #</td>
            <td>Material provided</td>
            <td>Gene risk</td>
            <td>Serotype</td>
            <td>Sharing</td>
            <td>number</td>
            <td>remark</td>
        </tr>
        <#list list as tr>
            <tr>
                <td>${tr.constructname}</td>
                <td>${tr.materialprovided}</td>
                <td>${tr.generisk}</td>
                <td>${tr.serotype}</td>
                <td>${tr.sharing}</td>
                <td>${tr.requestnum}</td>
                <td>${tr.remark}</td>
            </tr>
        </#list>
    </table>
    <div>
        <a href="${confirmUrl}">confirm</a>
        &nbsp;
        <a href="${refuseUrl}">refuse</a>
    </div>
</div>
