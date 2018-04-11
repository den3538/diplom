import diplom.FaqServiceImplService
import diplom.NewsServiceImplService
import diplom.RoleServiceImplService
import diplom.ScheduleServiceImplService
import diplom.UserServiceImplService

// Place your Spring DSL code here
beans = {
    faqService(FaqServiceImplService) {
        ref('faqService')
    }
    newsService(NewsServiceImplService) {
        ref('newsService')
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
}
