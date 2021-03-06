package spring

import diplom.*
import diplom.i18n.CustomLocaleResolver
import org.grails.web.i18n.ParamsAwareLocaleChangeInterceptor
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder

// Place your Spring DSL code here
beans = {
    faqService(FaqServiceImplService) {
        ref('faqService')
        securityService = ref('securityService')
    }
    newsService(NewsServiceImplService) {
        ref('newsService')
        securityService = ref('securityService')
    }
    roleService(RoleServiceImplService) {
        ref('roleService')
    }
    scheduleService(ScheduleServiceImplService) {
        ref('scheduleService')
    }
    userService(UserServiceImplService) {
        ref('userService')
    }
    securityService(SecurityServiceImplService) {
        ref('securityService')
        springSecurityService = ref('springSecurityService')
    }
    userRoleService(UserRoleServiceImplService) {
        ref('userRoleService')
    }
    uploadScheduleFileService(UploadScheduleFileServiceImplService) {
        ref('uploadScheduleFileService')
        scheduleService = ref('scheduleService')
    }
    imageService(ImageServiceImplService){
        ref('imageService')
    }
    localeResolver(CustomLocaleResolver)
    //disables password encoder
    passwordEncoder(PlaintextPasswordEncoder)
}
