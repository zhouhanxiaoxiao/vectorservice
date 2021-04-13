<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utif-8"/>
    <style>
        @page {
            size: 297mm 210mm;
        }
        @page {
            @top-center {
                content: element(header)
            }      /* Header */
            @bottom-center {
                content: element(footer)
            }   /* Enpied */
            margin-top: 120px;
            margin-bottom: 80px;
        }

        #header {
            position: running(header);
        }

        #footer {
            position: running(footer);
            font-size: 10px;
            height: 80px
        }

        #pagenumber:before {
            content: counter(page);
        }

        #pagecount:before {
            content: counter(pages);
        }

        table {
            margin: auto;
            border: 1px solid #333;
            border-bottom: none;
            border-left: none;
            page-break-inside: auto;
            -fs-table-paginate: paginate;
            border-spacing: 0;
            table-layout: fixed;
            word-break: break-all;
        }

        td {
            height: 30px;
            border: 1px solid #333;
            /*border-top: none;*/
            position: relative;
            padding-left: 10px;
        }

        tr {
            page-break-inside: avoid;
            page-break-after: auto;
        }

        tr.title {
            font-weight: bold;

        }

        td.title {
            height: 50px;
            font-weight: bold;
        }
    </style>
</head>
<body style="font-family:SimSun;font-size: 16px">
<div id="header">
    <img src="title.png"
         style="display: inline-block;width: 15%;height: 40px;margin-top: 15px;vertical-align: top;"></img>
    <h2 style="display: inline-block;width: 80%;text-align: center;vertical-align: top;font-weight: bold">Vector Core 载体工程中心 Service Request Form 服务申请表</h2>
    <hr/>
</div>
<div id="footer">
    <hr/>
    Note 注意：1. The default rAAV packaging quantity is 3×15cm dishes (6 dishes for low yield serotypes). Please make comment if a larger scale is required. 默认rAAV包装量为3个15cm培养皿（低得率血清型为6皿），如果需要更大包装量，请注明。2. Please confirm the provided construct, as well as the expression cassette, is correct. 请务必确认所提供质粒和表达框的正确性。3. It is strongly recommended to aliquot the rAAV to appropriate volume and stored at -80oC immediately after receiving it, and avoid repeatedly free/thawing. 强烈建议在收到rAAV后尽快分装为合适的体积并储存于-80oC，并避免反复冻融。4. Please strictly follow the biosafety regulation because rAAVs are still infectious material. rAAV仍属于感染性材料，操作过程中请务必严格遵守生物安全制度。
</div>
<div style="text-align: center;display: inline-block;width: 100%;font-weight: bolder;font-size: 25px;margin-top: -20px">rAAV Packageing rAAV包装</div>
<div style="width: 100%;border: 2px solid darkgrey;height: 122px">
    <table style="width: 100%;margin-top: 20px">
        <tr>
            <td rowspan="3" style="text-align: center;font-weight: bolder">
                Principal Investigator<br/>实验室负责人
            </td>
            <td>${groupAdmin.name}</td>

            <td rowspan="3"  style="text-align: center;font-weight: bolder">
                Service Requester<br/>服务申请人
            </td>
            <td>${creater.name}</td>
        </tr>
        <tr>
            <td>${groupAdmin.email}</td>
            <td>${creater.email}</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
    </table>

    <table style="width: 100%;text-align: center;margin-top: 5px">
        <tr style="background-color: darkgray;font-weight: bolder">
            <td colspan="7">Project Information 详细信息</td>
            <td colspan="2" style="background-color: darkgray">Filled by VC staff 载体中心填写</td>
        </tr>
        <tr>
            <td width="20">#</td>
            <td style="">Construct name/ plasmid #<br/> 载体名称 或 质粒编号</td>
            <td style="width: 15%;">Material provided<br/> 材料提供</td>
            <td style="width: 10%;">Gene risk<br/> 表达基因风险性</td>
            <td style="width: 10%;">Serotype<br/> 血清型</td>
            <td style="width: 10%;">Sharing<br/> 共享</td>
            <td style="width: 10%;">Sharing<br/> 数量</td>
            <td style="background-color: darkgray">Vol(μL)</td>
            <td style="background-color: darkgray">Titer(x10¹² gc/ml)</td>
        </tr>
        <#list raavs as tr>
            <tr>
                <td>${tr_index + 1}</td>
                <td>${tr.constructname}</td>
                <td>${tr.materialprovided}</td>
                <td>${tr.generisk}</td>
                <td>${tr.serotype}</td>
                <td>${tr.sharing?string('yes', 'no')}</td>
                <td>${tr.requestnum}</td>
                <td style="background-color: darkgray">${tr.vol}</td>
                <td style="background-color: darkgray">${tr.titer}</td>
            </tr>
        </#list>
        <#if liftSize gt 0>
            <#list 1..liftSize as i>
                <tr>
                    <td>${raavs?size + i}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="background-color: darkgray"></td>
                    <td style="background-color: darkgray"></td>
                </tr>
            </#list>
        </#if>
    </table>

    <table style="width: 100%;text-align: center;margin-top: 10px">
        <tr style="background-color: darkgray; height: 50px;font-size: 30px;color: white">
            <td>Requester Signature 申请人签字</td>
            <td>PI signature 实验室负责人签字</td>
            <td>Resciver signature 领取人签字</td>
        </tr>
        <tr>
            <td colspan="2">Requesting date 申请日期: ${creatTime}</td>
            <td style="text-align: left">Delivering date 领取日期</td>
        </tr>
    </table>
</div>
</body>
</html>