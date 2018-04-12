package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([User])
@TestFor(UserController)
class UserControllerSpec extends Specification {

    def "should invoke index method"() {
        given:
            User user1 = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            User user2 = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            User user3 = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            controller.userService = Mock(UserServiceImplService) {
                1 * list(_, _) >> [user1, user2, user3]
                1 * count() >> 3
            }
        when:
            controller.index(0, 0)
        then:
            model.userList
            model.userCount == 3
            model.userList.get(0) == user1
            model.userList.get(1) == user2
            model.userList.get(2) == user3
    }

    def "should invoke show method"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
        when:
            controller.show(user)
        then:
            model.user
            model.user == user
    }

    def "should invoke create method"() {
        when:
            controller.create()
        then:
            model.user
    }

    def "should invoke save method"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            controller.userService = Mock(UserServiceImplService) {
                1 * save(_) >> user
            }
        when:
            controller.save(user)
        then:
            model.user
            model.user == user
            status == 201
            view == "/user/show"
    }

    def "should invoke edit method"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
        when:
            controller.edit(user)
        then:
            model.user
            model.user == user
    }

    def "should invoke update method"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            controller.userService = Mock(UserServiceImplService) {
                1 * update(_) >> user
            }
        when:
            controller.update(user)
        then:
            model.user
            model.user == user
            status == 200
            view == "/user/show"
    }

    def "should invoke delete method"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            controller.userService = Mock(UserServiceImplService)
        when:
            controller.delete(user)
        then:
            1 * controller.userService.delete(_)
    }
}