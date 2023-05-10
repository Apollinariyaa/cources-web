<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h4>Информация</h4>
        <#list boughts as card>
            <strong>Название:</strong> ${card.card.shopElement.name}<p/>
            <#if card.card.shopElement.countInComplect!=0>
                <strong>Количество задач:</strong> ${card.card.shopElement.countInComplect}<p/>
            </#if>
            <strong>Стоимость:</strong> ${card.card.shopElement.cost} руб.<p/>
            <strong>Дата создания:</strong> ${card.card.shopElement.date}<p/>
            <strong>На имя:</strong> ${card.credentials}<p/>
            <strong>Куплен:</strong> ${card.buyDate}<p/>
            <a href="/history">Назад</a>
        </#list>

    </@k.page_default>
</@c.page>