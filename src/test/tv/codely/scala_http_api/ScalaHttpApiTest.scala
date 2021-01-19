package tv.codely.scala_http_api

import tv.codely.scala_http_api.entry_point.{CourseSpec, StatusSpec, UserSpec, VideoSpec}

final class ScalaHttpApiTest  {
    new StatusSpec
    new UserSpec
    new VideoSpec
    new CourseSpec
}
