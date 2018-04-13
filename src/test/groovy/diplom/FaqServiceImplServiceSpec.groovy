package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

@Mock([Faq])
@TestFor(FaqServiceImplService)
class FaqServiceImplServiceSpec extends Specification {

    def "should return list of faqs"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            Faq faq1 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            Faq faq2 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            Faq faq3 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            Faq faq4 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
        when:
            List<Faq> faqList = service.list(0, 0)
        then:
            faqList
            faqList.size() == 4
            faqList.get(0) == faq1
            faqList.get(1) == faq2
            faqList.get(2) == faq3
            faqList.get(3) == faq4
    }

    def "should return list of faqs with ten offset"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            for (int i = 0; i < 10; i++) {
                new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            }
            Faq faq1 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            Faq faq2 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            Faq faq3 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            Faq faq4 = new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
        when:
            List<Faq> faqList = service.list(1, 0)
        then:
            faqList
            faqList.size() == 4
            faqList.get(0) == faq1
            faqList.get(1) == faq2
            faqList.get(2) == faq3
            faqList.get(3) == faq4

    }

    def "should save faq"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            Faq faq = new Faq(question: "test", answer: "test")
            service.securityService = Mock(SecurityServiceImplService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            Faq saved = service.save(faq)
        then:
            saved
            Faq.count == 1
            saved == faq
            saved.dateCreated
    }

    def "should update faq"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            Faq faq = new Faq(question: "test", answer: "test", author: user).save()
            faq.question = "new question"
            service.securityService = Mock(SecurityServiceImplService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            Faq updated = service.update(faq)
        then:
            updated
            Faq.count == 1
            updated == faq
            updated.lastUpdated
    }

    def "should delete faq"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            Faq faq = new Faq(question: "test", answer: "test", author: user).save()
            service.securityService = Mock(SecurityServiceImplService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            service.delete(faq)
        then:
            Faq.count == 0
    }

    def "should return count of faqs"() {
        given:
            User user = new User(name: "test", secondName: "test", surname: "test", username: "test", password: "test", email: "test@test.com")
            for (int i = 0; i < 10; i++) {
                new Faq(question: "testQuestion", answer: "testAnswer", author: user).save()
            }
        when:
            Long count = service.count()
        then:
            count == 10
    }
}