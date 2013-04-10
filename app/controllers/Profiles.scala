package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import anorm._

import views._
import models._


object Profiles extends Controller {

  val profileForm = Form(
    mapping(
      "id" -> ignored(NotAssigned:Pk[Long]),
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "summary" -> text,
      "experience" -> text
    )(Profile.apply)(Profile.unapply)
  )

  def list = Action {
    Ok(html.profiles.list(Profile.findAll))
  }

  def view(id: Long) = Action {
    Ok(html.profiles.view(Profile.findById(id)))
  }

  def create = Action {
    Ok(html.profiles.create(profileForm))
  }

  def save = Action { implicit request =>
    profileForm.bindFromRequest.fold(
      formWithErrors =>
        BadRequest(html.profiles.create(formWithErrors)),
      profile => {
        Profile.insert(profile)
        Redirect(routes.Profiles.list())
      }
    )
  }

}
