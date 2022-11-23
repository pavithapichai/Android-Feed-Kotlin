package com.example.androidfeed.utils


import org.mockito.ArgumentCaptor

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()