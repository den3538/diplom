package diplom

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FaqController {

    FaqService faqService

    def index(Integer page, Integer max) {
        List<Faq> faqList = faqService.list(page, max)
        Long faqCount = faqService.count()
        respond(faqList, model: [faqCount: faqCount])
    }

    def show(Faq faq) {
        respond(faq)
    }

    def create() {
        respond(new Faq(params))
    }

    @Transactional
    def save(Faq faq) {
        Faq savedFaq = faqService.save(faq)

        respond(savedFaq, status: CREATED, view: "/faq/show")
    }

    def edit(Faq faq) {
        respond(faq)
    }

    @Transactional
    def update(Faq faq) {
        Faq updatedFaq = faqService.update(faq)

        respond(updatedFaq, status: OK, view: "/faq/show")
    }

    @Transactional
    def delete(Faq faq) {
        faqService.delete(faq)

//        respond([status: NO_CONTENT, view: "/faq/index"])
        redirect(controller: "faq", action: "index", method: "GET", status: NO_CONTENT)
    }
}
