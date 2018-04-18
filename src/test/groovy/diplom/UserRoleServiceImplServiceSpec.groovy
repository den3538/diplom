package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([UserRole, User, Role])
@TestFor(UserRoleServiceImplService)
class UserRoleServiceImplServiceSpec extends Specification {

    def "should save user role"() {
        given:
            User user = new User(id: 5)
            Role role = new Role(id: 3)
        when:
            UserRole result = service.save(user, role)
        then:
            result
            UserRole.count == 1
    }

    def "should delete user role"() {
        given:
            new UserRole(user: new User(id: 1), role: new Role(id: 1)).save()
            new UserRole(user: new User(id: 2), role: new Role(id: 2)).save()
            new UserRole(user: new User(id: 3), role: new Role(id: 3)).save()
        when:
            boolean result = service.delete(new User(id: 1), new Role(id: 1))
        then:
            result
            UserRole.count == 2
    }

    def "should delete all records for given user"() {
        given:
            new UserRole(user: new User(id: 1), role: new Role(id: 1)).save()
            new UserRole(user: new User(id: 2), role: new Role(id: 2)).save()
            new UserRole(user: new User(id: 2), role: new Role(id: 3)).save()
        when:
            boolean result = service.deleteAll(new User(id: 2))
        then:
            result
            UserRole.count == 1
    }

    def "should delete all records for given role"() {
        given:
            new UserRole(user: new User(id: 1), role: new Role(id: 1)).save()
            new UserRole(user: new User(id: 2), role: new Role(id: 1)).save()
            new UserRole(user: new User(id: 2), role: new Role(id: 2)).save()
        when:
            boolean result = service.deleteAll(new Role(id: 1))
        then:
            result
            UserRole.count == 1
    }
}
