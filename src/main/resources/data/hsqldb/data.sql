INSERT INTO genre (name) VALUES ('rock');
INSERT INTO genre (name) VALUES ('hard rock');
INSERT INTO genre (name) VALUES ('heavy metal');
INSERT INTO genre (name) VALUES ('symphonic metal');

INSERT INTO artist (name) VALUES ('Ian Gillan');
INSERT INTO artist (name) VALUES ('Ritchie Blackmore');
INSERT INTO artist (name) VALUES ('Roger Glover');
INSERT INTO artist (name) VALUES ('Ian Paice');
INSERT INTO artist (name) VALUES ('Jon Lord');
INSERT INTO artist (name) VALUES ('Joe Bonamassa');
INSERT INTO artist (name) VALUES ('Steve Morse');
INSERT INTO artist (name) VALUES ('Darin Vasilev');

INSERT INTO band (name) VALUES ('Deep Purple');
INSERT INTO band (name) VALUES ('Jon Lord');

INSERT INTO album (title, year_of_release) VALUES ('Machine Head','1972');

INSERT INTO album_band (album_id, band_id) VALUES (1,1);


INSERT INTO track (duration, name, track_number) VALUES (366, 'Highway Star', 1);
INSERT INTO track (duration, name, track_number) VALUES (291, 'Maybe I\'m a Leo', 2);
INSERT INTO track (duration, name, track_number) VALUES (304, 'Pictures of home', 3);
INSERT INTO track (duration, name, track_number) VALUES (238, 'Never before', 4);
INSERT INTO track (duration, name, track_number) VALUES (341, 'Smoke on the water', 5);
INSERT INTO track (duration, name, track_number) VALUES (513, 'Lazy', 6);
INSERT INTO track (duration, name, track_number) VALUES (273, 'Space Truckin\'', 7);
INSERT INTO track (duration, name, track_number) VALUES (209, 'When a blind man cries', 8);

INSERT INTO album_artist (album_id, artist_id) VALUES (1, 1);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 2);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 3);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 4);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 5);

INSERT INTO album_genre (album_id, genre_id) VALUES (1, 1);
INSERT INTO album_genre (album_id, genre_id) VALUES (1, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (1, 3);

INSERT INTO album_track (album_id, track_id) VALUES (1, 1);
INSERT INTO album_track (album_id, track_id) VALUES (1, 2);
INSERT INTO album_track (album_id, track_id) VALUES (1, 3);
INSERT INTO album_track (album_id, track_id) VALUES (1, 4);
INSERT INTO album_track (album_id, track_id) VALUES (1, 5);
INSERT INTO album_track (album_id, track_id) VALUES (1, 6);
INSERT INTO album_track (album_id, track_id) VALUES (1, 7);
INSERT INTO album_track (album_id, track_id) VALUES (1, 8);


INSERT INTO album (title, year_of_release) VALUES ('Concerto For Group And Orchestra','2012');
INSERT INTO album_band (album_id, band_id) VALUES (2,2);


INSERT INTO track (duration, name, track_number) VALUES (983, 'Moderato-Allegro', 1);
INSERT INTO track (duration, name, track_number) VALUES (1177, 'Andante', 2);
INSERT INTO track (duration, name, track_number) VALUES (652, 'Vivace - Presto', 3);

INSERT INTO album_artist (album_id, artist_id) VALUES (2, 5);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 7);

INSERT INTO album_genre (album_id, genre_id) VALUES (2, 1);
INSERT INTO album_genre (album_id, genre_id) VALUES (2, 4);

INSERT INTO album_track (album_id, track_id) VALUES (2, 9);
INSERT INTO album_track (album_id, track_id) VALUES (2, 10);
INSERT INTO album_track (album_id, track_id) VALUES (2, 11);