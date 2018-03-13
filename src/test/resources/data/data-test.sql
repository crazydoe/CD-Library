INSERT INTO genre (name) VALUES ('genre1');
INSERT INTO genre (name) VALUES ('genre2');
INSERT INTO genre (name) VALUES ('genre3');
INSERT INTO genre (name) VALUES ('genre4');

INSERT INTO band (name) VALUES ('band1');
INSERT INTO band (name) VALUES ('band2');

INSERT INTO artist (name) VALUES ('artist1');
INSERT INTO artist (name) VALUES ('artist2');
INSERT INTO artist (name) VALUES ('artist3');
INSERT INTO artist (name) VALUES ('artist4');
INSERT INTO artist (name) VALUES ('artist5');
INSERT INTO artist (name) VALUES ('artist6');
INSERT INTO artist (name) VALUES ('artist8');
INSERT INTO artist (name) VALUES ('artist7');

INSERT INTO track (duration, name, track_number) VALUES (366, 'track1', 1);
INSERT INTO track (duration, name, track_number) VALUES (291, 'track2', 2);
INSERT INTO track (duration, name, track_number) VALUES (304, 'track3', 3);
INSERT INTO track (duration, name, track_number) VALUES (238, 'track4', 4);
INSERT INTO track (duration, name, track_number) VALUES (341, 'track5', 5);
INSERT INTO track (duration, name, track_number) VALUES (513, 'track6', 6);
INSERT INTO track (duration, name, track_number) VALUES (273, 'track7', 7);
INSERT INTO track (duration, name, track_number) VALUES (209, 'track8', 8);
INSERT INTO track (duration, name, track_number) VALUES (983, 'track9', 1);
INSERT INTO track (duration, name, track_number) VALUES (117, 'track10', 2);
INSERT INTO track (duration, name, track_number) VALUES (652, 'track11', 3);

INSERT INTO album (title, year_of_release) VALUES ('test1','2000');
INSERT INTO album (title, year_of_release) VALUES ('test2','2012');

INSERT INTO album_band (album_id, band_id) VALUES (1,1);
INSERT INTO album_band (album_id, band_id) VALUES (2,2);

INSERT INTO album_artist (album_id, artist_id) VALUES (1, 1);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 2);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 3);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 4);
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 5);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 5);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 7);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 8);

INSERT INTO album_genre (album_id, genre_id) VALUES (1, 1);
INSERT INTO album_genre (album_id, genre_id) VALUES (1, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (1, 3);
INSERT INTO album_genre (album_id, genre_id) VALUES (2, 1);
INSERT INTO album_genre (album_id, genre_id) VALUES (2, 4);

INSERT INTO album_track (album_id, track_id) VALUES (1, 1);
INSERT INTO album_track (album_id, track_id) VALUES (1, 2);
INSERT INTO album_track (album_id, track_id) VALUES (1, 3);
INSERT INTO album_track (album_id, track_id) VALUES (1, 4);
INSERT INTO album_track (album_id, track_id) VALUES (1, 5);
INSERT INTO album_track (album_id, track_id) VALUES (1, 6);
INSERT INTO album_track (album_id, track_id) VALUES (1, 7);
INSERT INTO album_track (album_id, track_id) VALUES (1, 8);
INSERT INTO album_track (album_id, track_id) VALUES (2, 9);
INSERT INTO album_track (album_id, track_id) VALUES (2, 10);
INSERT INTO album_track (album_id, track_id) VALUES (2, 11);