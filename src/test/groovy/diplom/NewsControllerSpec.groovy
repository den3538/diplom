package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

@Mock([News])
@TestFor(NewsController)
class NewsControllerSpec extends Specification {

    def "should invoke index method"() {
        given:
            User author = new User(name: "test", secondName: "test", surname: "test", username: "test1", password: "test", email: "test@test.com")
            News news1 = new News(name: "test", description: "test", content: "test", author: author)
            News news2 = new News(name: "test", description: "test", content: "test", author: author)
            News news3 = new News(name: "test", description: "test", content: "test", author: author)
            controller.newsService = Mock(NewsServiceImplService) {
                1 * list(_, _) >> [news1, news2, news3]
                count() >> 3
            }
        when:
            controller.index(0, 0)
        then:
            model.newsList
            model.newsCount == 3
            model.newsList.get(0) == news1
            model.newsList.get(1) == news2
            model.newsList.get(2) == news3
    }

    def "should invoke show method"() {
        given:
            User author = new User(name: "test", secondName: "test", surname: "test", username: "test1", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: author)
        when:
            controller.show(news)
        then:
            model.news
            model.news == news
    }

    def "should invoke create method"() {
        when:
            controller.create()
        then:
            model.news
    }

    def "should invoke save method"() {
        given:
            User author = new User(name: "test", secondName: "test", surname: "test", username: "test1", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: author)
            controller.newsService = Mock(NewsServiceImplService) {
                1 * save(_) >> news
            }
        when:
            controller.save(news)
        then:
            model.news
            model.news == news
            status == 201
            view == "/news/show"
    }

    def "should invoke edit method"() {
        given:
            User author = new User(name: "test", secondName: "test", surname: "test", username: "test1", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: author)
        when:
            controller.edit(news)
        then:
            model.news
            model.news == news
    }

    def "should invoke update method"() {
        given:
            User author = new User(name: "test", secondName: "test", surname: "test", username: "test1", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: author)
            controller.newsService = Mock(NewsServiceImplService) {
                1 * update(_) >> news
            }
        when:
            controller.update(news)
        then:
            model.news
            model.news == news
            status == 200
            view == "/news/show"
    }

    def "should invoke delete method"() {
        given:
            User author = new User(name: "test", secondName: "test", surname: "test", username: "test1", password: "test", email: "test@test.com")
            News news = new News(name: "test", description: "test", content: "test", author: author)
            controller.newsService = Mock(NewsServiceImplService)
        when:
            controller.delete(news)
        then:
            1 * controller.newsService.delete(_)
    }
}