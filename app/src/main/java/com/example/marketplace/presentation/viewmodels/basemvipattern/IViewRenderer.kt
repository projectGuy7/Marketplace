package com.example.marketplace.presentation.viewmodels.basemvipattern

interface IViewRenderer<STATE> {
    fun render(state: STATE)
}