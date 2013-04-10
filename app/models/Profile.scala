package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Profile(
  id: Pk[Long],
  firstName: String,
  lastName: String,
  summary: String,
  experience: String
)

object Profile {

  val simple = {
    get[Pk[Long]]("Profile.id") ~
    get[String]("Profile.first_name") ~
    get[String]("Profile.last_name") ~
    get[String]("Profile.summary") ~
    get[String]("Profile.experience") map {
      case id ~ firstName ~ lastName ~ summary ~ experience =>
        Profile(id, firstName, lastName, summary, experience)
    }
  }

  def findAll: Seq[Profile] = {
    DB.withConnection { implicit connection =>
        SQL("select * from Profile").as(Profile.simple *)
    }
  }

  def findById(id: Long): Option[Profile] = {
    DB.withConnection { implicit connection =>
        SQL("select * from Profile where id = {id}").on('id -> id).as(Profile.simple.singleOpt)
    }
  }

  def insert(profile: Profile) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into Profile values (
            null, {first_name}, {last_name}, {summary}, {experience}
          )
        """
      ).on(
        'first_name -> profile.firstName,
        'last_name -> profile.lastName,
        'summary -> profile.summary,
        'experience -> profile.experience
      ).executeUpdate()
    }
  }
}