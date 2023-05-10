<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        <h2>Отзывы:</h2>
        <h3>${message?ifExists}</h3>
        <#if known&&!isAdmin>
            <a href="/comments/add">Напишите свой отзыв!</a>
        </#if>
        <#list comments as comment>
            <br style="border: grey; border-width: 3px; min-height: 15px; margin-top: 10px">
                <div>
                    <h5>${comment.clientInfo.username}:</h5>
                    <p/>
                    ${comment.text}
                    <p style="align-self: flex-end">${comment.creationDate}</p>
                </div>
            </br>
        </#list>
    </@k.page_default>
</@c.page>