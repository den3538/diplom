package diplom


interface FaqService {
    List<Faq> list(final Integer page, final Integer max)

    Faq save(final Faq faq)

    Faq update(final Faq faq)

    void delete(final Faq faq)

    Long count()
}
