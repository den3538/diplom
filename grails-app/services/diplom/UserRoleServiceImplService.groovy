package diplom

import grails.transaction.Transactional

@Transactional
class UserRoleServiceImplService implements UserRoleService {


    @Override
    UserRole save(User user, Role role) {
        UserRole.create(user, role, true)
    }

    @Override
    boolean delete(User user, Role role) {
        UserRole.remove(user, role)
    }

    @Override
    boolean deleteAll(User user) {
        UserRole.removeAll(user)
    }

    @Override
    boolean deleteAll(Role role) {
        UserRole.removeAll(role)
    }
}
