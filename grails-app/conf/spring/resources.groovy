package spring

import diplom.FaqServiceImplService
import diplom.NewsServiceImplService
import diplom.RoleServiceImplService
import diplom.ScheduleServiceImplService
import diplom.SecurityServiceImplService
import diplom.UserRoleServiceImplService
import diplom.UserServiceImplService
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
    //disables password encoder
    passwordEncoder(PlaintextPasswordEncoder)
}
