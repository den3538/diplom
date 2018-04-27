<!doctype html>
<html lang="ru" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>
        <g:layoutHead/>
    </head>
    <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light navigation-shadow">
            <a class="navbar-brand" href="#"><g:message code="navbar" default="qwerty"/> </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><g:message code="header.news" default="News"/><span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="enrolleeNavbar" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <g:message code="header.enrollee" default="For enrollee"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="enrolleeNavbar">
                            <a class="dropdown-item" href="#"><g:message code="header.enrollee.university" default="About university"/></a>
                            <a class="dropdown-item" href="#"><g:message code="header.enrollee.cathedra" default="About cathedra"/></a>
                            <a class="dropdown-item" href="#"><g:message code="header.enrollee.speciality"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="studentNavbar" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <g:message code="header.student" default="For students"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="studentNavbar">
                            <a class="dropdown-item" href="#"><g:message code="header.student.schedule" default="Schedule"/></a>
                            <a class="dropdown-item" href="#"><g:message code="header.student.consultation" default="Schedule of consultations"/></a>
                            <a class="dropdown-item" href="#"><g:message code="header.student.sections" default="Sections"/></a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="languageNavbar" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <g:message code="language.choose" default="Choose language"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="languageNavbar">
                            <a class="dropdown-item" href="?lang=en"><g:message code="language.en" default="English"/></a>
                            <a class="dropdown-item" href="?lang=ru"><g:message code="language.ru" default="Русский"/></a>
                            <a class="dropdown-item" href="?lang=uk"><g:message code="language.ua" default="Українська"/></a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container content">
            <g:layoutBody/>
        </div> </div>
        <div class="footer" role="contentinfo"></div>
        <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
    </body>
</html>
