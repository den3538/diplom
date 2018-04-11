package diplom

import grails.transaction.Transactional

@Transactional
class NewsServiceImplService implements NewsService {

    @Override
    List<News> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = Math.min(max ?: 10, 100)
        News.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    News save(News news) {
        news.save()
    }

    @Override
    News update(News news) {
        checkIfExists(news.id)
        news.save()
    }

    private void checkIfExists(Long id){
        if(!News.exists(id)){
            //todo throw exception
        }
    }

    @Override
    void delete(News news) {
        checkIfExists(news.id)
        news.delete()
    }

    @Override
    Integer count() {
        News.count
    }
}