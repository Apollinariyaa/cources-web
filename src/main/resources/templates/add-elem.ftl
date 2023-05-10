<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <form method="post" id="in" action="add-elem">
            <strong>Название:</strong><input type="text" required="true" minlength="8" name="name">
            <strong>Дата создания:</strong><input type="date" required="true" name="date">
            <strong>Стоимость:</strong><input type="number" required="true" name="cost" min="1">
            <#if springMacroRequestContext.requestUri?contains("/add-suuply")>
                <strong>Количество задач:</strong><input type="number" required="true" min="1" name="count">
            <#else >
                <input type="hidden" name="count" value="0">
            </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Добавить">
        </form>
    </@k.page_default>
</@c.page>