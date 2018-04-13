package diplom

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(SecurityServiceImplService)
class SecurityServiceImplServiceSpec extends Specification {

    def "should return authorized user"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            service.springSecurityService = Mock(SpringSecurityService) {
                1 * getCurrentUser() >> user
            }
        when:
            User result = service.getAuthorizedUser()
        then:
            result
            result == user
    }
}
