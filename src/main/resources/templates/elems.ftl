<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <#if known&&isAdmin>
            <#if springMacroRequestContext.requestUri?contains("/elems")>
                <a href="/add-elem">Добавить</a>
            <#else>
                <a href="/add-supply">Добавить</a>
            </#if>

        </#if>
        <table>
            <thead>
            <th>Название</th>
            <th>Дата создания</th>
            <th>Стоимость</th>
            <th></th>
            </thead>
            <tbody>
            <#list elems as shopElement>
                <tr>
                    <td>${shopElement.name}</td>
                    <td>${shopElement.date}</td>
                    <td>${shopElement.cost} руб.</td>
                    <td>
                        <#if shopElement.countInComplect==0>
                            <form method="get" action="/elems/${shopElement.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Подробнее">
                            </form>
                        <#else>
                            <form method="get" action="/supplies/${shopElement.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Подробнее">
                            </form>
                        </#if>
                        <#if known&&!isAdmin>
                            <form method="post" action="/add-to-list/${shopElement.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="В корзину">
                            </form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </@k.page_default>
</@c.page>