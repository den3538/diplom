package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.http.HttpStatus
import spock.lang.Specification

@Mock([Faq])
@TestFor(FaqController)
class FaqControllerSpec extends Specification {

    def "should invoke index method"() {
        given:
            Faq faq1 = new Faq(question: "test1", answer: "test1")
            Faq faq2 = new Faq(question: "test1", answer: "test1")
            Faq faq3 = new Faq(question: "test1", answer: "test1")
            controller.faqService = Mock(FaqServiceImplService) {
                1 * list(_, _) >> [faq1, faq2, faq3]
                1 * count() >> 3
            }
        when:
            controller.index(0, 0)
        then:
            model.faqList
            model.faqCount == 3
            model.faqList.size() == 3
            model.faqList.get(0) == faq1
            model.faqList.get(1) == faq2
            model.faqList.get(2) == faq3
    }

    def "should invoke show method"() {
        given:
            Faq faq = new Faq(question: "test", answer: "test")
        when:
            controller.show(faq)
        then:
            model.faq
            model.faq == faq
    }

    def "should invoke create method"() {
        when:
            controller.create()
        then:
            model.faq
    }

    def "should invoke save method"() {
        given:
            Faq faq = new Faq(question: "test", answer: "test", id: 1)
            controller.faqService = Mock(FaqServiceImplService) {
                1 * save(_) >> faq
            }
        when:
            controller.save(faq)
        then:
            model.faq
            view == "/faq/show"
            status == 201
    }

    def "should invoke edit method"() {
        given:
            Faq faq = new Faq(question: "test", answer: "test")
        when:
            controller.edit(faq)
        then:
            model.faq
            model.faq == faq
    }

    def "should invoke update method"() {
        given:
            Faq faq = new Faq(question: "test", answer: "test", id: 1)
            controller.faqService = Mock(FaqServiceImplService) {
                1 * update(_) >> faq
            }
        when:
            controller.update(faq)
        then:
            model.faq
            model.faq == faq
            view == "/faq/show"
            status == 200
    }

    def "should invoke delete method"() {
        given:
            Faq faq = new Faq(question: "test", answer: "test", id: 1)
            controller.faqService = Mock(FaqServiceImplService)
        when:
            controller.delete(faq)
        then:
            1 * controller.faqService.delete(_)

    }
}