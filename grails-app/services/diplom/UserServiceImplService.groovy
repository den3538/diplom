package diplom

import grails.transaction.Transactional

@Transactional
class UserServiceImplService implements UserService {

    @Override
    List<User> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = Math.min(max ?: 10, 100)
        User.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    User save(User user) {
        user.save()
    }

    @Override
    User update(User user) {
        checkIfExists(user.id)
        user.save()
    }

    private void checkIfExists(Long id) {
        if (!User.exists(id)) {
            //todo throw exception
        }
    }

    @Override
    void delete(User user) {
        checkIfExists(user.id)
        user.delete()
    }

    @Override
    Integer count() {
        User.count
    }
}
