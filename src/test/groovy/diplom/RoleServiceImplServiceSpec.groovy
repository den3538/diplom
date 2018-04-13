package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([Role])
@TestFor(RoleServiceImplService)
class RoleServiceImplServiceSpec extends Specification {

    def "should return list of roles"() {
        given:
            Role role1 = new Role(authority: "ROLE_USER").save()
            Role role2 = new Role(authority: "ROLE_ADMIN").save()
        when:
            List<Role> result = service.list(0, 0)
        then:
            result
            result.size() == 2
            result.get(0) == role1
            result.get(1) == role2
    }

    def "should return list of roles from ten"() {
        given:
            for (int i = 0; i < 10; i++) {
                new Role(authority: "SOMETHING$i").save()
            }
            Role role1 = new Role(authority: "ROLE_USER").save()
            Role role2 = new Role(authority: "ROLE_ADMIN").save()
        when:
            List<Role> result = service.list(1, 0)
        then:
            result
            result.size() == 2
            result.get(0) == role1
            result.get(1) == role2
    }

    def "should save new object"() {
        given:
            Role role = new Role(authority: "ROLE_TEST")
        when:
            Role saved = service.save(role)
        then:
            Role.count == 1
            saved == role
    }

    def "should update role"() {
        given:
            Role role = new Role(authority: "ROLE_TEST").save()
            role.authority = "ROLE_NEW"
        when:
            Role updated = service.update(role)
        then:
            updated
            updated == role
    }

    def "should throw CantFindException"() {
        when:
            service.update(new Role())
        then:
            thrown(CantFindException)
    }

    def "should delete news"() {
        given:
            Role role = new Role(authority: "ROLE_TEST").save()
        when:
            service.delete(role)
        then:
            Role.count == 0
    }

    def "should return object count"(){
        given:
            for (int i = 0; i < 5; i++) {
                new Role(authority: "SOMETHING$i").save()
            }
        when:
            Integer count = service.count()
        then:
            count == 5
    }
}
