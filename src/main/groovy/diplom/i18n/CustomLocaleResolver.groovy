package diplom.i18n

import org.apache.commons.lang.LocaleUtils
import org.grails.web.util.WebUtils
import org.springframework.context.annotation.Scope
import org.springframework.web.servlet.LocaleResolver

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Scope("session")
class CustomLocaleResolver implements LocaleResolver {

    private static final Locale defaultLocale = Locale.US

    private Locale currentLocale = defaultLocale

    @Override
    Locale resolveLocale(HttpServletRequest request) {
        if (currentLocale == defaultLocale) {
            String localeStr = WebUtils.getCookie(request, 'locale')?.value

            if (!localeStr) {
                Locale.setDefault(defaultLocale)
                return defaultLocale
            }
            Locale.setDefault(LocaleUtils.toLocale(localeStr))
        } else {
            Locale.setDefault(currentLocale)
        }
        Locale.getDefault()
    }

    @Override
    void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie("locale", locale.toString())
        cookie.path = request.servletContext.contextPath.empty ? "/" : request.servletContext.contextPath
        response.setCookie(cookie)
        currentLocale = locale
    }
}
