
CREATE TABLE courses (
  id         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  course_id  CHAR(36)            NOT NULL,
  teacher    VARCHAR(255)        NOT NULL,
  updated_at TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  UNIQUE KEY u_course_id (course_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE=utf8mb4_unicode_ci;
