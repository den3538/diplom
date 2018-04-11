package diplom

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([Schedule])
@TestFor(ScheduleServiceImplService)
class ScheduleServiceImplServiceSpec extends Specification {

    def "should return list of schedules"() {
        given:
            Schedule schedule1 = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
            Schedule schedule2 = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
            Schedule schedule3 = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
        when:
            List<Schedule> result = service.list(0, 0)
        then:
            result
            result.size() == 3
            result.get(0) == schedule1
            result.get(1) == schedule2
            result.get(2) == schedule3
    }

    def "should return list of schedules from ten"() {
        given:
            for (int i = 0; i < 10; i++) {
                new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
            }
            Schedule schedule1 = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
            Schedule schedule2 = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
            Schedule schedule3 = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
        when:
            List<Schedule> result = service.list(1, 0)
        then:
            result
            result.size() == 3
            result.get(0) == schedule1
            result.get(1) == schedule2
            result.get(2) == schedule3
    }

    def "should save new object"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule")
        when:
            Schedule saved = service.save(schedule)
        then:
            Schedule.count == 1
            saved == schedule
    }

    def "should update news"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule")
            schedule.fileName = "new filename"
        when:
            Schedule updated = service.update(schedule)
        then:
            updated
            updated == schedule
    }

    def "should delete news"() {
        given:
            Schedule schedule = new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
        when:
            service.delete(schedule)
        then:
            Schedule.count == 0
    }

    def "should return object count"() {
        given:
            for (int i = 0; i < 4; i++) {
                new Schedule(tetrameter: 1, year: 2019, fileName: "schedule").save()
            }
        when:
            Integer count = service.count()
        then:
            count == 4
    }
}
