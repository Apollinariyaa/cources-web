<#include "security.ftl">
<#import "login.ftl" as l>

<#macro navbar>
    <nav class="blue darken-4">
        <div class="nav-wrapper">
            <a style="visibility:collapse" id="errorMessage"></a>
            <a href="/" class="brand-logo">Skill UP</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <#if known>
                    <li><a href="/elems">Учебные пособия</a></li>
                    <li><a href="/supplies">Экзамены</a></li>
                    <#if isAdmin>
                        <li><a href="/users">Пользователи</a></li>
                    <#else>
                        <li><a href="/cards">Корзина</a></li>
                        <li><a href="/history">История покупок</a></li>
                    </#if>
                    <li><a href="/comments">Отзывы</a></li>
                    <li><@l.logout></@l.logout></li>
                <#else>
                    <li><a href="/comments">Отзывы</a></li>
                    <li><a href="/login">Вход</a></li>
                    <li><a href="/registration">Регистрация</a></li>
                </#if>
            </ul>
        </div>
    </nav>
</#macro>