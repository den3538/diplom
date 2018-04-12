package diplom

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NewsController {

    NewsService newsService

    def index(Integer page, Integer max) {
        List<News> newsList = newsService.list(page, max)
        Long newsCount = newsService.count()
        respond newsList, model: [newsCount: newsCount]
    }

    def show(News news) {
        respond(news)
    }

    def create() {
        respond(new News(params))
    }

    @Transactional
    def save(News news) {
        News savedNews = newsService.save(news)

        respond(savedNews, status: CREATED, view: "/news/show")
    }

    def edit(News news) {
        respond(news)
    }

    @Transactional
    def update(News news) {
        News updatedNews = newsService.update(news)

        respond(updatedNews, status: OK, view: "/news/show")
    }

    @Transactional
    def delete(News news) {
        newsService.delete(news)

        redirect(controller: "news", action: "index", method: "GET", status: NO_CONTENT)
    }
}
