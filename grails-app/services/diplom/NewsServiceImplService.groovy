package diplom

import grails.transaction.Transactional

@Transactional
class NewsServiceImplService implements NewsService {

    SecurityService securityService

    private checkIfExists = { Long id ->
        if (!News.exists(id)) {
            throw new CantFindException("Can't find requested news!")
        }
    }

    @Override
    List<News> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = PageUtil.getMaxValue(max)
        News.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    News save(News news) {
        news.author = securityService.getAuthorizedUser()

        news.save()
    }

    @Override
    News update(News news) {
        checkIfExists(news.id)
        checkIfAuthor(news)
        news.save()
    }

    private void checkIfAuthor(News news) {
        if (news.author.id != securityService.getAuthorizedUser().id) {
            throw new CantUpdateException("You can't delete or update this news")
        }
    }

    @Override
    void delete(News news) {
        checkIfExists(news.id)
        checkIfAuthor(news)
        news.delete()
    }

    @Override
    Integer count() {
        News.count
    }
}
