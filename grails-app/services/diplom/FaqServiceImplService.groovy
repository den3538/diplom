package diplom

import grails.transaction.Transactional

@Transactional
class FaqServiceImplService implements FaqService {

    @Override
    List<Faq> list(final Integer page, final Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = Math.min(max ?: 10, 100)
        Faq.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    Faq save(Faq faq) {
        faq.save()
    }

    @Override
    Faq update(Faq faq) {
        checkThatFaqExists(faq.id)
        faq.save()
    }

    private void checkThatFaqExists(Long id) {
        if (!Faq.exists(id)) {
            //todo throw exception
        }
    }

    @Override
    void delete(Faq faq) {
        checkThatFaqExists(faq.id)
        faq.delete()
    }

    @Override
    Long count() {
        Faq.count
    }
}
