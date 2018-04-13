package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([News])
@TestFor(NewsServiceImplService)
class NewsServiceImplServiceSpec extends Specification {

    def "should return list of news"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            News news1 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            News news2 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            News news3 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            News news4 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
        when:
            List<News> result = service.list(0, 0)
        then:
            result
            result.size() == 4
            result.get(0) == news1
            result.get(1) == news2
            result.get(2) == news3
            result.get(3) == news4
    }

    def "should return list of news from ten"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            for (int i = 0; i < 10; i++) {
                new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            }
            News news1 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            News news2 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            News news3 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            News news4 = new News(name: "test name", description: "test desc", content: "test content", author: user).save()
        when:
            List<News> result = service.list(1, 0)
        then:
            result
            result.size() == 4
            result.get(0) == news1
            result.get(1) == news2
            result.get(2) == news3
            result.get(3) == news4
    }

    def "should save new object"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test")
            service.securityService = Mock(SecurityServiceImplService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            News saved = service.save(news)
        then:
            News.count == 1
            saved == news
    }

    def "should update news"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: user).save()
            news.name = "new name"
            service.securityService = Mock(SecurityServiceImplService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            News updated = service.update(news)
        then:
            updated
            updated == news
    }

    def "should delete news"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: user).save()
            service.securityService = Mock(SecurityServiceImplService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            service.delete(news)
        then:
            News.count == 0
    }

    def "should return object count"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            for (int i = 0; i < 4; i++) {
                new News(name: "test name", description: "test desc", content: "test content", author: user).save()
            }
        when:
            Integer count = service.count()
        then:
            count == 4
    }
}
