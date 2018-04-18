package diplom

class PageUtil {

    private static final Integer MIN_ITEMS_PER_PAGE = 10
    private static final Integer MAX_ITEMS_PER_PAGE = 100

    static Integer getMaxValue(Integer candidateMax) {
        Integer minResult = Math.min(candidateMax ?: MIN_ITEMS_PER_PAGE, MAX_ITEMS_PER_PAGE)
        Math.max(minResult, MIN_ITEMS_PER_PAGE)
    }

}
