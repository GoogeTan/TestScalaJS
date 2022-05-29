package me.zahara.moddedminecraft

import cats.effect.IO
import japgolly.scalajs.react.vdom.HtmlAttrAndStyles.*
import japgolly.scalajs.react.vdom.TagMod

object Styles:
  inline val defaultPadding = 5;
  
  val marginTB: TagMod =
    marginHeight := s"${defaultPadding}px"
  
  val marginLR: TagMod =
    marginHeight := s"${2 * defaultPadding}px"