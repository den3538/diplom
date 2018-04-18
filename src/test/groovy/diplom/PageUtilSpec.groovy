package diplom

import spock.lang.Specification
import spock.lang.Unroll

class PageUtilSpec extends Specification {

    @Unroll
    def "should return #expected if #given given"() {
        when:
            Integer result = PageUtil.getMaxValue(given)
        then:
            result == expected
        where:
            given || expected
            null  || 10
            5     || 10
            25    || 25
            250   || 100
    }

}
