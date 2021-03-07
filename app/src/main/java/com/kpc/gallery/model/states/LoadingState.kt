package com.kpc.gallery.model.states

data class LoadingState(
  val isLoading: Boolean,
  val labels: List<String> = listOf()
)
