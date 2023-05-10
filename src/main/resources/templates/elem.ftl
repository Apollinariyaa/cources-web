<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h4>Информация</h4>
        <strong>Название:</strong> ${elem.name}<p/>
        <strong>Стоимость:</strong> ${elem.cost} руб.<p/>
        <strong>Дата создания:</strong> ${elem.date}<p/>
        <#if springMacroRequestContext.requestUri?contains("/supplies")>
            <strong>Количество задач:</strong> ${elem.countInComplect} шт.<p/>
            <a href="/supplies">Назад</a>
        <#else>
            <a href="/elems">Назад</a>
        </#if>

    </@k.page_default>
</@c.page>