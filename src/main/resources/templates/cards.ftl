<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h5>Ваша персональная скидка - ${discount} %</h5>
        <table>
            <thead>
            <th>Название</th>
            <th>Дата создания</th>
            <th>Стоимость</th>
            <th></th>
            </thead>
            <tbody>
            <#list cards as card>
                <tr>
                    <td>${card.shopElement.name}</td>
                    <td>${card.shopElement.date}</td>
                    <td>${card.shopElement.cost* (100.0 - discount)/100.0 } руб.</td>
                    <td>
                        <#if card.shopElement.countInComplect==0>
                            <form method="get" action="/elems/${card.shopElement.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Подробнее">
                            </form>
                        <#else>
                            <form method="get" action="/supplies/${card.shopElement.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Подробнее">
                            </form>
                        </#if>
                        <form method="get" action="/cards/buy/${card.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Купить">
                        </form>
                        <form method="post" action="/cards/delete/${card.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Удалить">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </@k.page_default>
</@c.page>