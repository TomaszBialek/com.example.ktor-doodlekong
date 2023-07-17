package com.example

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.routes.createRoomRoute
import com.example.session.DrawingSession
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.util.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

val server = DrawingServer()

fun Application.module() {
    install(Sessions) {
        cookie<DrawingSession>("SESSION")
    }
    intercept(ApplicationCallPipeline.Features) {
        if (call.sessions.get<DrawingSession>() == null) {
            val clientId = call.parameters["client_id"] ?: ""
            call.sessions.set(DrawingSession(clientId, generateNonce()))
        }
    }

    install(Routing) {
        createRoomRoute()
    }


    configureSerialization()
    configureSockets()
    configureMonitoring()
    configureRouting()
}
