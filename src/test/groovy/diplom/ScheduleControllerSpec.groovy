package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([Schedule])
@TestFor(ScheduleController)
class ScheduleControllerSpec extends Specification {

    def "should invoke index method"() {
        given:
            Schedule schedule1 = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
            Schedule schedule2 = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
            controller.scheduleService = Mock(ScheduleServiceImplService) {
                1 * list(_, _) >> [schedule1, schedule2]
                1 * count() >> 2
            }
        when:
            controller.index(0, 0)
        then:
            model.scheduleList
            model.scheduleCount == 2
            model.scheduleList.get(0) == schedule1
            model.scheduleList.get(1) == schedule2
    }

    def "should invoke show method"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
        when:
            controller.show(schedule)
        then:
            model.schedule
            model.schedule == schedule
    }

    def "should invoke create method"() {
        when:
            controller.create()
        then:
            model.schedule
    }

    def "should invoke save method"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
            controller.scheduleService = Mock(ScheduleServiceImplService) {
                1 * save(_) >> schedule
            }
        when:
            controller.save(schedule)
        then:
            model.schedule
            model.schedule == schedule
            status == 201
            view == "/schedule/show"
    }

    def "should invoke edit method"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
        when:
            controller.edit(schedule)
        then:
            model.schedule
            model.schedule == schedule
    }

    def "should invoke update method"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
            controller.scheduleService = Mock(ScheduleServiceImplService) {
                1 * update(_) >> schedule
            }
        when:
            controller.update(schedule)
        then:
            model.schedule
            model.schedule == schedule
            status == 200
            view == "/schedule/show"
    }

    def "should invoke delete method"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2020, fileName: "someFile.csv")
            controller.scheduleService = Mock(ScheduleServiceImplService)
        when:
            controller.delete(schedule)
        then:
            1 * controller.scheduleService.delete(_)
    }
}