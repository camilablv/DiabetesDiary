package com.ca.diabetesdiary.navigation


internal sealed class Route(val route: String) {
    object Auth : Route("auth_graph")
    object Home : Route("home")
}
