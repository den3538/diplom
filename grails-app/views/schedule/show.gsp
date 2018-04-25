<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-schedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-schedule" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list schedule">
                <li class="fieldcontain">
                    <span class="property-label">Tetrameter</span>
                    <div class="property-value">${this.schedule.tetrameter}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Year</span>
                    <div class="property-value">${this.schedule.year}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Attached file</span>
                    <div class="property-value"><g:link action="loadFile" resource="${this.schedule}">${this.schedule.fileName}</g:link></div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Creation date</span>
                    <div class="property-value">${this.schedule.dateCreated}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Last update</span>
                    <div class="property-value">${this.schedule.lastUpdated}</div>
                </li>
            </ol>
            <g:form resource="${this.schedule}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.schedule}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    %{--<g:link class="edit" action="editFile" resource="${this.schedule}"><g:message code="schedule.file.edit.label" default="Edit Featured Image" /></g:link>--}%
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
