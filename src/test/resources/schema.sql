DROP SCHEMA IF EXISTS cd_db;
CREATE SCHEMA cd_db;
USE cd_db;

CREATE TABLE album
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) DEFAULT NULL,
  year_of_release VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE artist
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY(id),
  UNIQUE KEY (name)
);

CREATE TABLE band
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (name)
);

CREATE TABLE genre
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_003 (name)
);

CREATE TABLE track
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  duration BIGINT(20) DEFAULT NULL,
  name VARCHAR(255) DEFAULT NULL,
  track_number BIGINT(20) DEFAULT NULL,
  album_id BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (album_id) REFERENCES album (id)
);

CREATE TABLE album_artist
(
  album_id BIGINT(20) NOT NULL,
  artist_id BIGINT(20) NOT NULL,
  PRIMARY KEY (album_id,artist_id),
  FOREIGN KEY (artist_id) REFERENCES artist (id),
  FOREIGN KEY (album_id) REFERENCES album (id)
);

CREATE TABLE album_band
(
  band_id BIGINT(20) DEFAULT NULL,
  album_id BIGINT(20) NOT NULL,
  PRIMARY KEY (album_id),
  FOREIGN KEY (band_id) REFERENCES band (id),
  FOREIGN KEY (album_id) REFERENCES album (id)
);

CREATE TABLE album_genre
(
  album_id BIGINT(20) NOT NULL,
  genre_id BIGINT(20) NOT NULL,
  PRIMARY KEY (album_id, genre_id),
  FOREIGN KEY (album_id) REFERENCES album(id),
  FOREIGN KEY (genre_id) REFERENCES genre(id)
);

CREATE TABLE album_track
 (
  album_id BIGINT(20) NOT NULL,
  track_id BIGINT(20) NOT NULL,
  PRIMARY KEY (album_id,track_id),
  UNIQUE KEY (track_id),
  FOREIGN KEY (album_id) REFERENCES album (id),
  FOREIGN KEY (track_id) REFERENCES track (id)
);