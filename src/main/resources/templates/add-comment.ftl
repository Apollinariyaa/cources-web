<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <form method="post" action="/comments/add">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="text" name="text" required="true" placeholder="Введите ваш отзыв...">
            <input type="submit" value="Добавить">
        </form>
    </@k.page_default>
</@c.page>