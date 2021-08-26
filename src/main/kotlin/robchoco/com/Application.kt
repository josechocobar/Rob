package robchoco.com

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import robchoco.com.plugins.*
import java.io.File

fun main() {
    //io.ktor.network.tls.certificates.generateCertificate(File("mycert.jks"))
    val port = System.getenv("PORT")?.toInt() ?: 23567
    embeddedServer(CIO, port=port) {

        configureRouting()
        configureHTTP()
        configureTemplating()
        configureSerialization()
        configureSockets()
        configureAdministration()


    }.start(wait = true)
}
