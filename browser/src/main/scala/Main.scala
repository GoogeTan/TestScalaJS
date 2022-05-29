package me.zahara.moddedminecraft

import scala.scalajs.js.annotation.*
import org.scalajs.dom.{Node, html}
import forum.Post
import Components.*

import japgolly.scalajs.react.ReactDOM
import japgolly.scalajs.react.component.Js.UnmountedWithRoot
import japgolly.scalajs.react.component.Scala.{JsMounted, MountedImpure}
import japgolly.scalajs.react.internal.Box

import org.scalajs.dom

@JSExportTopLevel("main")
object Main:
  @JSExport
  def main(): Unit =
    body.renderIntoDOM(dom.document.getElementById("playground"))

  val body : UnmountedWithRoot[forum.Post, MountedImpure[forum.Post, Unit, Unit], Box[forum.Post], JsMounted[forum.Post, Unit, Unit] ] =
    PostComponent(Post("Alina is nice", "Alina likes minecraft. Alina is mice. Aline is something special"))
    