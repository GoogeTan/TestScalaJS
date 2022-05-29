package me.zahara.moddedminecraft

import cats.effect.IO
import forum.Post
import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Scala.Component
import japgolly.scalajs.react.component.builder.ComponentBuilder
import japgolly.scalajs.react.vdom.HtmlAttrAndStyles.*
import japgolly.scalajs.react.vdom.HtmlTags.*
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.vdom.{HtmlAttrAndStyles, HtmlTags}

import scala.language.implicitConversions
import scala.scalajs.js.annotation.*
import scala.scalajs.js.|

object Components:
  val PostComponent =
    ScalaComponent.builder[Post]
      .stateless
      .noBackend
      .render_P(
        post =>
          div(
            div(
              h3(post.header)(marginWidth := "10px", marginHeight := "5px")
            ),
            div(
              post.post
            )(marginWidth := "10px", marginHeight := "5px")
          )
      )
      .build