package robchoco.com

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import robchoco.com.plugins.*
import java.io.File

fun main() {
    //io.ktor.network.tls.certificates.generateCertificate(File("mycert.jks"))
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {

        configureRouting()
        configureHTTP()
        configureTemplating()
        configureSerialization()
        configureSockets()
        configureAdministration()


    }.start(wait = true)
}
