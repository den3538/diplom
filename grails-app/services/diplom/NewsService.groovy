package diplom

interface NewsService {
    List<News> list(Integer page, Integer max)

    News save(News news)

    News update(News news)

    void delete(News news)

    Integer count()
}