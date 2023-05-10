<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h3>${message?ifExists}</h3>
        <table>
            <thead>
            <th>Название</th>
            <th>Дата создания</th>
            <th>Дата покупки</th>
            <th></th>
            </thead>
            <tbody>
            <#list boughts as card>
                <tr>
                    <td>${card.card.shopElement.name}</td>
                    <td>${card.card.shopElement.date}</td>
                    <td>${card.buyDate }</td>
                    <td>
                        <form method="get" action="/history/${card.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Подробнее">
                        </form>
                        <form method="get" action="/history/download/${card.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Скачать">
                        </form>
                        <form method="post" action="/history/send/${card.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Отправить на email">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </@k.page_default>
</@c.page>