package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

@Mock([Role])
@TestFor(RoleController)
class RoleControllerSpec extends Specification {

    def "should invoke index method"() {
        given:
            Role role1 = new Role(authority: "ROLE_USER")
            Role role2 = new Role(authority: "ROLE_ADMIN")
            controller.roleService = Mock(RoleServiceImplService) {
                1 * list(_, _) >> [role1, role2]
                1 * count() >> 2
            }
        when:
            controller.index(0, 0)
        then:
            model.roleList
            model.roleCount == 2
            model.roleList.get(0) == role1
            model.roleList.get(1) == role2
    }

    def "should invoke show method"() {
        given:
            Role role = new Role(authority: "ROLE_TEST")
        when:
            controller.show(role)
        then:
            model.role
            model.role == role
    }

    def "should invoke create method"() {
        when:
            controller.create()
        then:
            model.role
    }

    def "should invoke save method"() {
        given:
            Role role = new Role(authority: "ROLE_TEST")
            controller.roleService = Mock(RoleServiceImplService) {
                1 * save(_) >> role
            }
        when:
            controller.save(role)
        then:
            model.role
            model.role == role
            status == 201
            view == "/role/show"
    }

    def "should invoke edit method"() {
        given:
            Role role = new Role(authority: "ROLE_TEST")
        when:
            controller.edit(role)
        then:
            model.role
            model.role == role
    }

    def "should invoke update method"() {
        given:
            Role role = new Role(authority: "ROLE_TEST")
            controller.roleService = Mock(RoleServiceImplService) {
                1 * update(_) >> role
            }
        when:
            controller.update(role)
        then:
            model.role
            model.role == role
            status == 200
            view == "/role/show"
    }

    def "should invoke delete method"() {
        given:
            Role role = new Role(authority: "ROLE_TEST")
            controller.roleService = Mock(RoleServiceImplService)
        when:
            controller.delete(role)
        then:
            1 * controller.roleService.delete(_)
    }
}