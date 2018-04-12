package diplom

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    UserService userService

    def index(Integer page, Integer max) {
        List<User> users = userService.list(page, max)
        Integer userCount = userService.count()
        respond users, model:[userCount: userCount]
    }

    def show(User user) {
        respond(user)
    }

    def create() {
        respond(new User(params))
    }

    @Transactional
    def save(User user) {
        User savedUser = userService.save(user)

        respond(savedUser, status: CREATED, view: "/user/show")
    }

    def edit(User user) {
        respond(user)
    }

    @Transactional
    def update(User user) {
        User updatedUser = userService.update(user)

        respond(updatedUser, status: OK, view: "/user/show")
    }

    @Transactional
    def delete(User user) {
        userService.delete(user)

        redirect(controller: "user", action: "index", method: "GET", status: NO_CONTENT)
    }

}
