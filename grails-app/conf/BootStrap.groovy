import Widgets.Role
import Widgets.User
import Widgets.UserRole
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.request.RequestContextHolder

class BootStrap {
    def springSecurityService
    def init = { servletContext ->
        Role.withNewSession {
            ['ROLE_USER'].each {
                if (Role.countByAuthority(it)==0) {
                    new Role(authority: it).save(failOnError: true)
                }
            }
        }
        RequestContextHolder.requestAttributes = new GrailsWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse(), servletContext)
        String password = springSecurityService.encodePassword('lutefisk')

        if (!User.findByUsername("david")) {
            User david = new User(username:  'david',
                enabled: true,
                password: password,
                emailaddress: 'david@davidwbrown.name',
                firstname: 'David',
                lastname: 'Brown'
            )
            david.save(failOnError: true);
            ["ROLE_USER"].each {
                UserRole.create(david, Role.findByAuthority(it), true)
            }
        }
    }
    def destroy = {
    }
}
