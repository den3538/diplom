package diplom

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NewsController {

    NewsService newsService

    @Secured('permitAll')
    def index(Integer page, Integer max) {
        List<News> newsList = newsService.list(page, max)
        Long newsCount = newsService.count()
        respond newsList, model: [newsCount: newsCount]
    }

    @Secured('permitAll')
    def show(News news) {
        respond(news)
    }

    @Secured("ROLE_USER")
    def create() {
        respond(new News(params))
    }

    @Secured("ROLE_USER")
    @Transactional
    def save(News news) {
        News savedNews = newsService.save(news)

        respond(savedNews, status: CREATED, view: "/news/show")
    }

    @Secured("ROLE_USER")
    def edit(News news) {
        respond(news)
    }

    @Secured("ROLE_USER")
    @Transactional
    def update(News news) {
        News updatedNews = newsService.update(news)

        respond(updatedNews, status: OK, view: "/news/show")
    }

    @Secured("ROLE_USER")
    @Transactional
    def delete(News news) {
        newsService.delete(news)

        redirect(controller: "news", action: "index", method: "GET", status: NO_CONTENT)
    }
}
