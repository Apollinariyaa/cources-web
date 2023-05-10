<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <form method="post" action="/cards/buy/${card.id}">
            <strong>Эл.почта:</strong><input type="text" required="true" name="adress" minlength="11" maxlength="42">
            <strong>ФИО:</strong><input type="text" required="true" name="credentials">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Купить">
        </form>
    </@k.page_default>
</@c.page>