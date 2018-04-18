package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([User])
@TestFor(UserServiceImplService)
class UserServiceImplServiceSpec extends Specification {

    def "should return list of users"() {
        given:
            User user1 = new User(username: "test1", email: "test1@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            User user2 = new User(username: "test2", email: "test2@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            User user3 = new User(username: "test3", email: "test3@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            User user4 = new User(username: "test4", email: "test4@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
        when:
            List<User> result = service.list(0, 0)
        then:
            result
            result.size() == 4
            result.get(0) == user1
            result.get(1) == user2
            result.get(2) == user3
            result.get(3) == user4
    }

    def "should return list of users from ten"() {
        given:
            for (int i = 0; i < 10; i++) {
                new User(username: "ignore$i", email: "test$i@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            }
            User user1 = new User(username: "test1", email: "test1@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            User user2 = new User(username: "test2", email: "test2@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            User user3 = new User(username: "test3", email: "test3@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            User user4 = new User(username: "test4", email: "test4@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
        when:
            List<User> result = service.list(1, 0)
        then:
            result
            result.size() == 4
            result.get(0) == user1
            result.get(1) == user2
            result.get(2) == user3
            result.get(3) == user4
    }

    def "should save new object"() {
        given:
            User user = new User(username: "test1", email: "test1@test.com", name: "test", secondName: "test", surname: "test", password: "test")
        when:
            User saved = service.save(user)
        then:
            User.count == 1
            saved == user
    }

    def "should update news"() {
        given:
            User user = new User(username: "test1", email: "test1@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            user.email = "realEmail@real.com"
        when:
            User updated = service.update(user)
        then:
            updated
            updated == user
    }

    def "should throw CantFindException"() {
        when:
            service.update(new User())
        then:
            thrown(CantFindException)
    }

    def "should delete news"() {
        given:
            User user = new User(username: "test1", email: "test1@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            service.userRoleService = Mock(UserRoleServiceImplService)
        when:
            service.delete(user)
        then:
            User.count == 0
    }

    def "should return object count"() {
        given:
            for (int i = 0; i < 10; i++) {
                new User(username: "login$i", email: "test$i@test.com", name: "test", secondName: "test", surname: "test", password: "test").save()
            }
        when:
            Integer count = service.count()
        then:
            count == 10
    }

    def "should return requested user"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com").save()
        when:
            User result = service.get(1)
        then:
            result
            result == user
    }

}
