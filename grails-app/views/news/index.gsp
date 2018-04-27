<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'news.label', default: 'News')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:each in="${newsList}" var="news">
            <div class="row news-item">
                <div class="col-lg-4 col-md-4">
                    <img  class="card-img" src="${createLink(controller: "image", action: "get", params: [id: news.photo.id])}" alt="${message(code:'image.cant.upload',default:"Can't upload an image.")}"/>
                </div>
                <div class="col-lg-offset-1 col-md-offset-1"></div>
                <div class="col-lg-6 col-md-6">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">${news.name}</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12">${news.description}</div>
                    </div>
                </div>
                <div class="col-lg-offset-1 col-md-offset-1"></div>
            </div>
        </g:each>
        <div class="pagination">
            <g:paginate total="${newsCount ?: 0}" max="${maxPerPage}"/>
        </div>
    </body>
</html>