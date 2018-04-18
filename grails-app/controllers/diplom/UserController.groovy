package diplom

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    UserService userService

    @Secured("ROLE_ADMIN")
    def index(Integer page, Integer max) {
        List<User> users = userService.list(page, max)
        Integer userCount = userService.count()
        respond(users, model: [userCount: userCount])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def show(User user) {
        respond(user)
    }

    @Secured("ROLE_ADMIN")
    def create() {
        respond(new User(params))
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    def save(User user) {
        User savedUser = userService.save(user)

        respond(savedUser, status: CREATED, view: "/user/show")
    }

    @Secured("ROLE_ADMIN")
    def edit(User user) {
        respond(user)
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    def update(User user) {
        User updatedUser = userService.update(user)

        respond(updatedUser, status: OK, view: "/user/show")
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    def delete(User user) {
        userService.delete(user)

        redirect(controller: "user", action: "index", method: "GET", status: NO_CONTENT)
    }

}
