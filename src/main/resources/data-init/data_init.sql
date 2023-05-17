insert into users(id, created_at, first_name, is_active, last_name, modified_at)
values (1, now(), 'Alibek', false, 'Altynbekov', now()),
       (2, now(), 'Ilim', false, 'Shabdanov', now()),
       (3, now(), 'Adil', false, 'Aitbaev', now()),
       (4, now(), 'Saltanat', false, 'Nematilla kyzy', now()),
       (5, now(), 'Zhazgul', false, 'Zhoroeva', now());

insert into user_infos(id, email, password, role, user_id)
values (1, 'alibek@gmail.com', '$2a$04$m6SmfKTWVoq3UqY.TSfFhOhff9PJR9mIP9W4qxbDap3/36ucDdmVq', 'ADMIN', 1),
       (2, 'ilim@gmail.com', '$2a$04$HxEwmj1058t4CHWAAlawEediNwxxoyexbBkBZrw6RYU/WIbetD07u', 'USER', 2),
       (3, 'adil@gmail.com', '$2a$04$9AbdDhS0Se77peV1Vol79uTD1ndTja8WFDCcS7Bu0tZA8RdRogXNy', 'USER', 3),
       (4, 'saltanat@gmail.com', '$2a$04$fMcU5JEqZ5da60kbT77tOeDkXVLGA3xcJ43fDpUM6Rq2c/a2OXRzS', 'USER', 4),
       (5, 'zhazgul@gmail.com', '$2a$04$zsU2raL6oyLYLJQQgPpN9.2JFonBtos3Y9PlqvE5hZFUWp8qwZ3re', 'USER', 5);



insert into tests(id, short_description, title)
values (1, 'test for students ', 'Test 1');

insert into questions( id, audio_text, correct_answer, duration, min_words, number_of_replays, option_type, passage
                     , question_order, question_type, statement, title, test_id)
values (1, NULL, 'correct answer1', 30, NULL, NULL, 'MULTIPLE', NULL, 1,
        'SELECT_ENGLISH_WORD',
        NULL, 'title1', 1),
       (2, 'audio2', 'correct answer2', 30, 3, 2, NULL, NULL, 2,
        'TYPE_WHAT_YOU_HEAR',
        'statement2', 'title2', 1),
       (3, NULL, 'correct answer3', 30, NULL, NULL, NULL, NULL,
        3, 'DESCRIBE_IMAGE',
        NULL, 'title3', 1),
       (4, 'audio4', 'correct answer4', 30, NULL, NULL, 'MULTIPLE', NULL, 4,
        'LISTEN_AND_SELECT_ENGLISH_WORD',
        NULL, 'title4', 1),
       (5, 'audio5', 'correct answer5', 30, 3, NULL, NULL, NULL, 5,
        'RECORD_SAYING_STATEMENT',
        'statement5', 'title5', 1),
       (6, NULL, NULL, 30, 30, NULL, NULL, NULL, 6,
        'RESPOND_N_WORDS',
        'statement6', 'title6', 1),
       (7, NULL, 'correct answer7', 30, NULL, NULL, NULL, 'passage7', 7,
        'HIGHLIGHT_THE_ANSWER',
        'statement7', 'title7', 1),
       (8, NULL, NULL, 30, NULL, NULL, 'SINGLE', 'passage8', 8,
        'SELECT_THE_MAIN_IDEA',
        NULL, 'title8', 1),
       (9, NULL, NULL, 30, NULL, NULL, 'SINGLE', 'passage9', 9,
        'SELECT_BEST_TITLE',
        NULL, 'title9', 1),
       (10, NULL, 'correct answer10', 30, NULL, NULL, 'SINGLE', 'passage10', 10,
        'SELECT_THE_MAIN_IDEA',
        NULL, 'title10', 1);

insert into options(id, file_url, is_correct, title, question_id)
values (1, NULL, false, 'option1', 1),
       (2, NULL, false, 'option2', 1),
       (3, NULL, false, 'option3', 1),
       (4, NULL, false, 'option4', 1),
       (5, NULL, false, 'option5', 8),
       (6, NULL, false, 'option6', 8),
       (7, NULL, false, 'option7', 8),
       (8, NULL, false, 'option8', 8),
       (9, 'file url9', false, 'option9', 4),
       (10, 'file url10', false, 'option10', 4),
       (11, 'file url11', false, 'option11', 4),
       (12, 'file url12', false, 'option12', 4),
       (13, NULL, false, 'option13', 9),
       (14, NULL, false, 'option14', 9),
       (15, NULL, false, 'option15', 9),
       (16, NULL, false, 'option16', 9),
       (17, NULL, false, 'option17', 10),
       (18, NULL, false, 'option18', 10),
       (19, NULL, false, 'option19', 10),
       (20, NULL, false, 'option20', 10);

insert into files(id, file_type, file_url, question_id)
values (1, 'AUDIO', 'file_url1', 2),
       (2, 'IMAGE', 'file_url2', 3),
       (3, 'AUDIO', 'file_url3', 4),
       (4,'AUDIO','file_url4',5);

insert into results(id, date_of_submission, score, status, test_id, user_id)
values (1, now(), 0, 'NOT_EVALUATED', 1, 2),
       (2, now(), 0, 'NOT_EVALUATED', 1, 3),
       (3, now(), 0, 'NOT_EVALUATED', 1, 5),
       (4, now(), 0, 'NOT_EVALUATED', 1, 4);
--
-- insert into answers(id,answer_status, evaluated_score, number_of_plays, number_of_words, data, question_id, user_id)
-- values (1,'NOT_EVALUATED',0.0, 0, 0, NULL, 1, 2),
--        (2, 'NOT_EVALUATED',0.0, 0, 0, 'data', 2, 2),
--        (3, 'NOT_EVALUATED',0.0, 0, 0, 'data', 3, 2),
--        (4, 'NOT_EVALUATED',0.0, 0, 0, NULL, 4, 2),
--        (5, 'NOT_EVALUATED',0.0, 0, 0, 'data', 5, 2),
--        (6, 'NOT_EVALUATED',0.0, 0, 0, 'data', 6, 2),
--        (7, 'NOT_EVALUATED',0.0, 0, 0, 'data', 7, 2),
--        (8, 'NOT_EVALUATED',0.0, 0, 0, NULL, 8, 2),
--        (9, 'NOT_EVALUATED',0.0, 0, 0, NULL, 9, 2),
--        (10, 'NOT_EVALUATED',0.0, 0, 0, NULL, 10, 2);
--
-- insert into answers_files(answer_id, files_id)
-- values (5,4);
--
-- insert into answers_options(answer_id, options_id)
-- values (1, 1),
--        (1, 2),
--        (4, 10),
--        (4, 11),
--        (8, 5),
--        (9, 13),
--        (10, 17);
--
--
-- insert into results_answers(result_id, answers_id)
-- values (1, 1),
--        (1, 2),
--        (1, 3),
--        (1, 4),
--        (1, 5),
--        (1, 6),
--        (1, 7),
--        (1, 8),
--        (1, 9),
--        (1, 10);


