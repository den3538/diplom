<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Welcome to Grails</title>
    </head>

    <body>
        <div class="row main-page-news-title">
            <div class="col text-center">
                <g:message code="index.news.header" default="Last posted news"/>
            </div>
        </div>
        <hr/>
        <g:each in="${this.newsList}" var="news" status="i">
            <g:if test="${i % 3 == 0}">
                <div class="row main-page-news-offset-top">%{--opens row--}%
            </g:if>
            <div class="col-lg-4 col-md-4">
                ${news.name}
            </div>
            <g:if test="${i % 3 == 2}">
                </div>%{--closes row--}%
            </g:if>
        </g:each>
    </body>
</html>
