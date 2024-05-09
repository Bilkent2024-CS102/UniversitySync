DROP DATABASE IF EXISTS university_sync;
CREATE DATABASE IF NOT EXISTS university_sync;


CREATE TABLE IF NOT EXISTS university_sync.campus(
	campus_id int AUTO_INCREMENT,
    campus_name varchar(50),
    CONSTRAINT pk_campus_id PRIMARY KEY (campus_id)
);

CREATE TABLE IF NOT EXISTS university_sync.major(
	major_id int AUTO_INCREMENT,
    major_code varchar(5),
    major_full_name varchar(50),
    CONSTRAINT pk_major PRIMARY KEY (major_id)
);

CREATE TABLE IF NOT EXISTS university_sync.dormitory(
	dormitory_id int AUTO_INCREMENT,
    dorm_name varchar(50),
    dorm_description varchar(255),
    link_to_dormitory_picture varchar(50),
    dormitory_in_campus_id int,
    CONSTRAINT pk_dormitory_id PRIMARY KEY (dormitory_id),
    CONSTRAINT fk_dormitory_campus_id FOREIGN KEY (dormitory_in_campus_id) REFERENCES campus(campus_id)
);

CREATE TABLE IF NOT EXISTS university_sync.room_type(
	room_type_id int AUTO_INCREMENT,
    room_type_description varchar(255),
    capacity int,
    is_bunk bool,
    has_private_bathroom bool,
    room_in_dormitory_id int,
    CONSTRAINT pk_room_type_id PRIMARY KEY (room_type_id),
    CONSTRAINT fk_room_type_dormitory_id FOREIGN KEY (room_in_dormitory_id) REFERENCES dormitory(dormitory_id)
);

CREATE TABLE IF NOT EXISTS university_sync.cafeteria(
	cafeteria_id int AUTO_INCREMENT,
    cafeteria_name varchar(50),
    cafeteria_description varchar(255),
    min_price float(20),
    max_price float(20),
    link_to_cafeteria_picture varchar(50),
    cafeteria_in_campus_id int,
    CONSTRAINT pk_cafeteria_id PRIMARY KEY (cafeteria_id),
    CONSTRAINT fk_cafeteria_campus_id FOREIGN KEY (cafeteria_in_campus_id) REFERENCES campus(campus_id)
);

CREATE TABLE IF NOT EXISTS university_sync.menu_item(
	menu_item_id int AUTO_INCREMENT,
    menu_item_name varchar(50),
    price float(20),
    link_to_menu_item_picture varchar(50),
    menu_item_in_cafeteria_id int,
    CONSTRAINT pk_menu_item_id PRIMARY KEY (menu_item_id),
    CONSTRAINT fk_menu_item_cafeteria_id FOREIGN KEY (menu_item_in_cafeteria_id) REFERENCES cafeteria(cafeteria_id)
);


CREATE TABLE IF NOT EXISTS university_sync.student(
	student_id int AUTO_INCREMENT,
    full_name varchar(50),
    email varchar(100),
    pass varchar(50),
    biography varchar(255),
    link_to_profile_picture varchar(100),
    student_major_id int DEFAULT NULL,
    student_room_type_id int DEFAULT NULL,
    CONSTRAINT pk_student PRIMARY KEY (student_id),
    CONSTRAINT fk_student_major_id FOREIGN KEY (student_major_id) REFERENCES major(major_id),
    CONSTRAINT fk_student_room_type_id FOREIGN KEY (student_room_type_id) REFERENCES room_type(room_type_id)
);

CREATE TABLE IF NOT EXISTS university_sync.friend_request(
    sender_id int,
    receiver_id int,
    CONSTRAINT pk_friend_request PRIMARY KEY (sender_id, receiver_id),
	CONSTRAINT fk_sender_id FOREIGN KEY (sender_id) REFERENCES student(student_id),
	CONSTRAINT fk_reciever_id FOREIGN KEY (receiver_id) REFERENCES student(student_id),
    CONSTRAINT chk_sender_receiver CHECK (sender_id <> receiver_id)
);

-- each pair is stored once, first id being less than second id
CREATE TABLE IF NOT EXISTS university_sync.student_friendship(
    first_student_id int,
    second_student_id int,
    CONSTRAINT pk_student_friendship PRIMARY KEY (first_student_id, second_student_id),
    CONSTRAINT fk_first_student_id FOREIGN KEY (first_student_id) REFERENCES student(student_id),
    CONSTRAINT fk_second_student_id FOREIGN KEY (second_student_id) REFERENCES student(student_id),
    CONSTRAINT chk_first_second_student CHECK (first_student_id < second_student_id)
);



CREATE TABLE IF NOT EXISTS university_sync.forum_post( -- extends post
	forum_post_id int AUTO_INCREMENT,
    owner_student_id int,
    creation_date datetime,
    last_edit_date datetime,
    heading varchar(50),
    main_text varchar(255),
    like_count int DEFAULT 0,
    CONSTRAINT pk_forum_post_id PRIMARY KEY (forum_post_id),
    CONSTRAINT fk_forum_post_owner_student_id FOREIGN KEY (owner_student_id) REFERENCES student(student_id)
);

CREATE TABLE IF NOT EXISTS university_sync.tag_forum_post(
	tag varchar(50),
    tagged_forum_post_id int,
    CONSTRAINT pk_tag PRIMARY KEY (tag, tagged_forum_post_id),
    CONSTRAINT fk_tag_tagged_forum_post_id FOREIGN KEY (tagged_forum_post_id) REFERENCES forum_post(forum_post_id)
);

CREATE TABLE IF NOT EXISTS university_sync.like_forum_post(
	liked_by_student_id int,
    liked_forum_post_id int,
    CONSTRAINT pk_like_forum_post PRIMARY KEY (liked_by_student_id, liked_forum_post_id),
    CONSTRAINT fk_liked_by_student_id FOREIGN KEY (liked_by_student_id) REFERENCES student(student_id),
    CONSTRAINT fk_liked_forum_post_id FOREIGN KEY (liked_forum_post_id) REFERENCES forum_post(forum_post_id)
);

CREATE TABLE IF NOT EXISTS university_sync.reply( -- extends student_content_item
	reply_id int AUTO_INCREMENT,
    owner_student_id int,
    creation_date datetime,
    last_edit_date datetime,
    main_text varchar(255),
    replies_to_forum_post_id int,
    CONSTRAINT pk_reply_id PRIMARY KEY (reply_id),
    CONSTRAINT fk_reply_owner_student_id FOREIGN KEY (owner_student_id) REFERENCES student(student_id),
    CONSTRAINT fk_replies_to_forum_post_id FOREIGN KEY (replies_to_forum_post_id) REFERENCES forum_post(forum_post_id)
);


        
CREATE TABLE IF NOT EXISTS university_sync.dorm_transfer_post( -- extends post
	dorm_transfer_post_id int AUTO_INCREMENT,
    owner_student_id int,
    creation_date datetime,
    last_edit_date datetime,
    heading varchar(50),
    main_text varchar(255),
    posted_room_id int,
    CONSTRAINT pk_dorm_transfer_post_id PRIMARY KEY (dorm_transfer_post_id),
    CONSTRAINT fk_dorm_transfer_post_owner_student_id FOREIGN KEY (owner_student_id) REFERENCES student(student_id),
	CONSTRAINT fk_dorm_transfer_post_posted_room_id FOREIGN KEY (posted_room_id) REFERENCES room_type(room_type_id)
);

CREATE TABLE IF NOT EXISTS university_sync.event_post( -- extends post
	event_post_id int AUTO_INCREMENT,
    owner_student_id int,
    creation_date datetime,
    last_edit_date datetime,
    heading varchar(50),
    main_text varchar(255),
    location varchar(50),
    event_date datetime,
    CONSTRAINT pk_event_post_id PRIMARY KEY (event_post_id),
    CONSTRAINT fk_event_post_owner_student_id FOREIGN KEY (owner_student_id) REFERENCES student(student_id)
);

CREATE TABLE IF NOT EXISTS university_sync.follow_event_post(
	followed_by_student_id int,
    followed_event_post_id int,
    CONSTRAINT pk_followed_event_post PRIMARY KEY (followed_by_student_id, followed_event_post_id),
    CONSTRAINT fk_followed_by_student_id FOREIGN KEY (followed_by_student_id) REFERENCES student(student_id),
    CONSTRAINT fk_followed_event_post_id FOREIGN KEY (followed_event_post_id) REFERENCES event_post(event_post_id)
);

CREATE TABLE IF NOT EXISTS university_sync.message( -- extends student_content_item
	message_id int AUTO_INCREMENT,
    owner_student_id int,
    receiver_student_id int,
    creation_date datetime,
    last_edit_date datetime,
    main_text varchar(255),
    CONSTRAINT pk_message_id PRIMARY KEY (message_id),
    CONSTRAINT fk_message_owner_student_id FOREIGN KEY (owner_student_id) REFERENCES student(student_id),
    CONSTRAINT fk_message_receiver_student_id FOREIGN KEY (receiver_student_id) REFERENCES student(student_id)
);


CREATE TABLE IF NOT EXISTS university_sync.review(
	review_id int AUTO_INCREMENT,
    owner_student_id int,
    creation_date datetime,
    last_edit_date datetime,
    main_text varchar(255),
    rating_given int,
    review_to_dormitory_id int,
    review_to_cafeteria_id int,
    CONSTRAINT pk_review_id PRIMARY KEY (review_id),
    CONSTRAINT fk_review_owner_student_id FOREIGN KEY (owner_student_id) REFERENCES student(student_id),
    CONSTRAINT fk_review_to_dormitory_id FOREIGN KEY (review_to_dormitory_id) REFERENCES dormitory(dormitory_id),
    CONSTRAINT fk_review_to_cafeteria_id FOREIGN KEY (review_to_cafeteria_id) REFERENCES cafeteria(cafeteria_id),
    CONSTRAINT chk_review_to_single_location CHECK (review_to_cafeteria_id IS NULL XOR review_to_dormitory_id IS NULL),
    CONSTRAINT chk_rating_range CHECK (rating_given BETWEEN 0 AND 5)
);

