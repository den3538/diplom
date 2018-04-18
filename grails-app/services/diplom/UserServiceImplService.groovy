package diplom

import grails.transaction.Transactional

@Transactional
class UserServiceImplService implements UserService {

    UserRoleService userRoleService

    private checkIfExists = { Long id ->
        if (!User.exists(id)) {
            throw new CantFindException("Can't find requested user!")
        }
    }

    @Override
    List<User> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = Math.min(max ?: 10, 100)
        User.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    User save(User user) {
        user.save()
        //todo save userRoles too
    }

    @Override
    User update(User user) {
        checkIfExists(user.id)
        //todo update userRoles
        user.save()
    }

    @Override
    void delete(User user) {
        checkIfExists(user.id)
        userRoleService.deleteAll(user)
        user.delete()
    }

    @Override
    Integer count() {
        User.count
    }

    @Override
    User get(Long id) {
        User.get(id)
    }
}
