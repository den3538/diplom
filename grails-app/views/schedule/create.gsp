<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-schedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-schedule" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.schedule}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.schedule}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm action="save" name="save">
                <div class="fieldcontain required">
                    <label for="tetrameter" >Tetrameter</label>
                    <g:textField name="tetrameter" value="${tetrameter}" required="true"/>
                </div>
                <div class="fieldcontain required">
                    <label for="year" >Year</label>
                    <g:textField name="year" value="${year}" required="true"/>
                </div>
                <div class="fieldcontain required">
                    <label for="uploadedFile" >Schedule file</label>
                    <input id="uploadedFile" type="file" name="uploadedFile" accept=".xls,.xlsx,.csv"/>
                </div>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
