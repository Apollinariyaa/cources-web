<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        Пользователи:
        <table>
            <thead>
            <th>Логин</th>
            <th>Роль</th>
            <th>Статус</th>
            </thead>
            <tbody>
            <#list usrs as usr>
                <tr>
                    <td>${usr.username}</td>
                    <td>
                        <#if usr.isAdmin()>
                            Администратор
                        <#else>
                            Пользователь
                        </#if>
                    </td>
                    <td>
                        <form method="post" action="/users/${usr.id}">
                            <#if usr.active>
                                <input type="submit" value="Активен">
                            <#else>
                                <input type="submit" value="Заблокирован">
                            </#if>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </@k.page_default>
</@c.page>