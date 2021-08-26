package robchoco.com.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.util.*
import kotlinx.html.*

fun Application.configureRouting() {

    routing {
        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
        install(Authentication) {
            basic("auth-basic") {
                realm = "Access to the '/' path"
                validate { credentials ->
                    if (credentials.name == "jose" && credentials.password == "hola") {
                        UserIdPrincipal(credentials.name)
                    } else {
                        null
                    }

                }
            }
        }
        authenticate("auth-basic") {
            get("/hola") {
                call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}!")
            }
        }
        get {
            call.respondText("Hello World!")
        }


    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
