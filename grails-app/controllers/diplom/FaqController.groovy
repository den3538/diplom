package diplom

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FaqController {

    FaqService faqService

    @Secured('permitAll')
    def index(Integer page, Integer max) {
        List<Faq> faqList = faqService.list(page, max)
        Long faqCount = faqService.count()
        respond(faqList, model: [faqCount: faqCount])
    }

    @Secured('permitAll')
    def show(Faq faq) {
        respond(faq)
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def create() {
        respond(new Faq(params))
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    @Transactional
    def save(Faq faq) {
        Faq savedFaq = faqService.save(faq)

        respond(savedFaq, status: CREATED, view: "/faq/show")
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit(Faq faq) {
        respond(faq)
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    @Transactional
    def update(Faq faq) {
        Faq updatedFaq = faqService.update(faq)

        respond(updatedFaq, status: OK, view: "/faq/show")
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    @Transactional
    def delete(Faq faq) {
        faqService.delete(faq)

        redirect(controller: "faq", action: "index", method: "GET", status: NO_CONTENT)
    }
}
