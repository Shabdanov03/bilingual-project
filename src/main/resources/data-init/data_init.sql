insert into users(id, created_at, first_name, is_active, last_name, modified_at)
values (1, now(), 'Alibek', false, 'Altynbekov', now()),
       (2, now(), 'Ilim', false, 'Shabdanov', now()),
       (3, now(), 'Adil', false, 'Aitbaiev', now()),
       (4, now(), 'Saltanat', false, 'Nematilla kyzy', now()),
       (5, now(), 'Zhazgul', false, 'Zhoroeva', now());

insert into user_infos(id, email, password, role, user_id)
values (1, 'alibek@gmail.com', 'alibek123', 'ADMIN', 1),
       (2, 'ilim@gmail.com', 'ilim123', 'USER', 2),
       (3, 'adil@gmail.com', 'adil123', 'USER', 3),
       (4, 'saltanat@gmail.com', 'saltanat123', 'USER', 4),
       (5, 'zhazgul@gmail.com', 'zhazgul123', 'USER', 5);



insert into tests(id, short_description, title)
values (1, 'test for students ', 'Test 1');

insert into questions(id, audio_text, correct_answer, duration, min_words, number_of_replays, option_type, passage,
                      passage_question, question_order, question_type, statement, title, test_id)
values (1, NULL, 'correct answer1', 30, NULL, NULL, 'MULTIPLE', NULL, NULL, 1,
        'SELECT_ENGLISH_WORD',
        NULL, 'title1', 1),
       (2, 'audio2', 'correct answer2', 30, 3, 2, NULL, NULL, NULL, 2,
        'TYPE_WHAT_YOU_HEAR',
        'statement2', 'title2', 1),
       (3, NULL, 'correct answer3', 30, NULL, NULL, NULL, NULL, NULL,
        3, 'DESCRIBE_IMAGE',
        NULL, 'title3', 1),
       (4, 'audio4', 'correct answer4', 30, NULL, NULL, 'MULTIPLE', NULL, NULL, 4,
        'LISTEN_AND_SELECT_ENGLISH_WORD',
        NULL, 'title4', 1),
       (5, 'audio5', 'correct answer5', 30, 3, NULL, NULL, NULL, NULL, 5,
        'RECORD_SAYING_STATEMENT',
        'statement5', 'title5', 1),
       (6, NULL, NULL, 30, 30, NULL, NULL, NULL, NULL, 6,
        'RESPOND_N_WORDS',
        'statement6', 'title6', 1),
       (7, NULL, 'correct answer7', 30, NULL, NULL, NULL, 'passage7', 'passage_question7', 7,
        'HIGHLIGHT_THE_ANSWER',
        'statement7', 'title7', 1),
       (8, NULL, NULL, 30, NULL, NULL, 'SINGLE', 'passage8', NULL, 8,
        'SELECT_THE_MAIN_IDEA',
        NULL, 'title8', 1),
       (9, NULL, NULL, 30, NULL, NULL, 'SINGLE', 'passage9', NULL, 9,
        'SELECT_BEST_TITLE',
        NULL, 'title9', 1),
       (10, NULL, 'correct answer10', 30, NULL, NULL, 'SINGLE', 'passage10', NULL, 10,
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
       (3, 'AUDIO', 'file_url3', 4);

insert into results(id, date_of_submission, score, status, test_id, user_id)
values (1, now(), 0, 'NOT_EVALUATED', 1, 2),
       (2, now(), 0, 'NOT_EVALUATED', 1, 3),
       (3, now(), 0, 'NOT_EVALUATED', 1, 5),
       (4, now(), 0, 'NOT_EVALUATED', 1, 4);

insert into answers(id, evaluated_score, is_checked, number_of_plays, number_of_words, data, question_id, user_id)
values (1, 0, false, 0, 0, NULL, 1, 2),
       (2, 0, false, 0, 0, 'data', 2, 2),
       (3, 0, false, 0, 0, 'data', 3, 2),
       (4, 0, false, 0, 0, NULL, 4, 2),
       (5, 0, false, 0, 0, 'data', 5, 2),
       (6, 0, false, 0, 0, 'data', 6, 2),
       (7, 0, false, 0, 0, 'data', 7, 2),
       (8, 0, false, 0, 0, NULL, 8, 2),
       (9, 0, false, 0, 0, NULL, 9, 2),
       (10, 0, false, 0, 0, NULL, 10, 2);

insert into answers_options(answer_id, options_id)
values (1, 1),
       (1, 2),
       (4, 10),
       (4, 11),
       (8, 5),
       (9, 13),
       (10, 17);


insert into results_answers(result_id, answers_id)
values (1, 1),
       (1,2),
       (1,3),
       (1,4),
       (1,5),
       (1,6),
       (1,7),
       (1,8),
       (1,9),
       (1,10);

