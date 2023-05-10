<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h4>Регистрация</h4>
        <h3>${message?ifExists}</h3>
        <@l.login "/registration" />
    </@k.page_default>
</@c.page>