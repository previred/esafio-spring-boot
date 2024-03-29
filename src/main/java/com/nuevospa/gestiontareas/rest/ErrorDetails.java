package com.nuevospa.gestiontareas.rest;

public record ErrorDetails(int statusCode, String message, String details) { }