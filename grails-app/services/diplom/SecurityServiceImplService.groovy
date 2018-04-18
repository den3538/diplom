package diplom

import grails.transaction.Transactional

@Transactional
class SecurityServiceImplService implements SecurityService {

    def springSecurityService

    @Override
    User getAuthorizedUser() {
        springSecurityService.currentUser as User
    }
}
