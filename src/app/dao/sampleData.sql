-- Campus data
INSERT INTO university_sync.campus (campus_name) VALUES
('Main Campus'),
('North Campus'),
('South Campus');

-- Major data
INSERT INTO university_sync.major (major_code, major_full_name) VALUES
('CS', 'Computer Science'),
('ENG', 'Engineering'),
('BIO', 'Biology');

INSERT INTO university_sync.location (location_type) VALUES
('dormitory'),
('dormitory'),
('dormitory'),
('cafeteria'),
('cafeteria'),
('cafeteria');


-- Dormitory data
INSERT INTO university_sync.dormitory (dormitory_location_id, dorm_name, dorm_description, link_to_dormitory_picture, dormitory_in_campus_id) VALUES
(1, 'Dorm A', 'Cozy dormitory with single and double rooms.', 'dorm_a.jpg', 1),
(2, 'Dorm B', 'Modern dormitory with private bathrooms.', 'dorm_b.jpg', 2),
(3, 'Dorm C', 'Traditional dormitory with bunk beds.', 'dorm_c.jpg', 3);

-- Room type data
INSERT INTO university_sync.room_type (room_type_description, capacity, is_bunk, has_private_bathroom, room_in_dormitory_id) VALUES
('Single Room', 1, FALSE, TRUE, 1),
('Double Room', 2, FALSE, FALSE, 1),
('Bunk Bed Room', 2, TRUE, FALSE, 3);

-- Cafeteria data
INSERT INTO university_sync.cafeteria (cafeteria_location_id, cafeteria_name, cafeteria_description, min_price, max_price, link_to_cafeteria_picture, cafeteria_in_campus_id) VALUES
(4, 'Cafeteria 1', 'Offers a variety of cuisines.', 5.00, 15.00, 'cafeteria_1.jpg', 1),
(5, 'Cafeteria 2', 'Known for its healthy options.', 6.00, 18.00, 'cafeteria_2.jpg', 2),
(6, 'Cafeteria 3', 'Popular spot for students to socialize.', 4.00, 12.00, 'cafeteria_3.jpg', 3);

-- Menu item data
INSERT INTO university_sync.menu_item (menu_item_name, price, link_to_menu_item_picture, menu_item_in_cafeteria_id) VALUES
('Cheeseburger', 5.99, 'cheeseburger.jpg', 4),
('Salad Bowl', 7.49, 'salad_bowl.jpg', 5),
('Pizza Slice', 3.99, 'pizza_slice.jpg', 6);

-- Student data
INSERT INTO university_sync.student (full_name, email, pass, biography, link_to_profile_picture, student_major, student_room_type_id) VALUES
('Alice Johnson', 'alice@example.com', 'password123', 'Computer Science major, loves programming.', 'alice.jpg', 'computer science', 1),
('Bob Smith', 'bob@example.com', 'password456', 'Engineering major, enjoys building things.', 'bob.jpg', 'engineering', 2),
('Charlie Brown', 'charlie@example.com', 'password789', 'Biology major, interested in environmental science.', 'charlie.jpg', 'biology', 3),
('Emily White', 'emily@example.com', 'password123', 'Computer Science major, aspiring software engineer.', 'emily.jpg', 'computer science', 1),
('Michael Green', 'michael@example.com', 'password456', 'Engineering major, passionate about renewable energy.', 'michael.jpg', 'engineering', 2),
('Sophia Rodriguez', 'sophia@example.com', 'password789', 'Biology major, loves studying animal behavior.', 'sophia.jpg', 'biology', 3);

-- Friend request data
INSERT INTO university_sync.friend_request (sender_id, receiver_id) VALUES
(1, 2),
(1, 3),
(2,4),
(2, 3);

-- Student friendship data
INSERT INTO university_sync.student_friendship (first_student_id, second_student_id) VALUES
(1, 4),
(1, 3);

-- Forum post data
INSERT INTO university_sync.forum_post (owner_student_id, creation_date, last_edit_date, heading, main_text, like_count) VALUES
(1, '2024-05-01', '2024-05-01', 'Introduction', 'Hello everyone! I am new here.', 10),
(2, '2024-05-02', '2024-05-02', 'Project Ideas', 'Looking for project ideas in engineering.', 15),
(3, '2024-05-03', '2024-05-03', 'Study Group', 'Anyone interested in forming a study group for biology?', 8);

-- Tag forum post data
INSERT INTO university_sync.tag_forum_post (tag, tagged_forum_post_id) VALUES
('New', 1),
('Engineering', 2),
('Biology', 3);

-- Like forum post data
INSERT INTO university_sync.like_forum_post (liked_by_student_id, liked_forum_post_id) VALUES
(2, 1),
(3, 1),
(1, 2),
(2, 2),
(3, 3);

-- Reply data
INSERT INTO university_sync.reply (owner_student_id, creation_date, last_edit_date, main_text, replies_to_forum_post_id) VALUES
(2, '2024-05-01', '2024-05-01', 'Welcome, Alice!', 1),
(3, '2024-05-01', '2024-05-01', 'Nice to meet you, Alice.', 1),
(1, '2024-05-02', '2024-05-02', 'You can join our engineering club.', 2),
(3, '2024-05-03', '2024-05-03', 'I''m interested!', 3);

-- Dorm transfer post data
INSERT INTO university_sync.dorm_transfer_post (owner_student_id, creation_date, last_edit_date, heading, main_text, posted_room_id) VALUES
(1, '2024-05-01', '2024-05-01', 'Looking to transfer dorms', 'Considering transferring to a dorm closer to my classes.', 2),
(2, '2024-05-02', '2024-05-02', 'Need roommate for dorm transfer', 'Looking for a roommate to transfer to Dorm C.', 3),
(3, '2024-05-03', '2024-05-03', 'Available room in Dorm A', 'Have a vacant spot in my room in Dorm A.', 1);

-- Event post data
INSERT INTO university_sync.event_post (owner_student_id, creation_date, last_edit_date, heading, main_text, location, event_date) VALUES
(1, '2024-05-01', '2024-05-01', 'Club Meeting', 'Computer Science Club meeting tomorrow.', 'Room 101', '2024-05-02'),
(2, '2024-05-02', '2024-05-02', 'Engineering Expo', 'Excited for the engineering expo next week.', 'Auditorium', '2024-05-10'),
(3, '2024-05-03', '2024-05-03', 'Biology Field Trip', 'Field trip to study local ecosystems.', 'Nature Reserve', '2024-05-15');

-- Follow event post data
INSERT INTO university_sync.follow_event_post (followed_by_student_id, followed_event_post_id) VALUES
(1, 2),
(2, 3),
(3, 1);

-- Message data
INSERT INTO university_sync.message (owner_student_id, receiver_student_id, creation_date, last_edit_date, main_text) VALUES
(1, 2, '2024-05-01', '2024-05-01', 'Hey Bob, do you want to study together?'),
(2, 1, '2024-05-01', '2024-05-01', 'Sure, Alice. When and where?'),
(3, 1, '2024-05-01', '2024-05-01', 'Hi Alice, can I join your study group?');

INSERT INTO university_sync.message (owner_student_id, receiver_student_id, creation_date, last_edit_date, main_text) 
VALUES 
(1, 2, "2024-05-01", "2024-05-01", "Hey Bob, do you want to study together?"),
(2, 1, "2024-05-01", "2024-05-01", "Sure, Alice. When and where?"),
(3, 1, "2024-05-01", "2024-05-01", "Hi Alice, can I join your study group?"),
(1, 3, "2024-05-01", "2024-05-01", "Of course, Charlie. We meet at the library at 3 pm."),
(3, 2, "2024-05-02", "2024-05-02", "Bob, do you have any project ideas in mind?"),
(2, 3, "2024-05-02", "2024-05-02", "Yes, Charlie. Let's discuss it over lunch tomorrow."),
(2, 1, "2024-05-02", "2024-05-02", "Alice, have you seen the new engineering lab?"),
(1, 2, "2024-05-02", "2024-05-02", "No, Bob. Is it impressive?"),
(2, 1, "2024-05-02", "2024-05-02", "Absolutely! You should check it out sometime."),
(1, 3, "2024-05-03", "2024-05-03", "Charlie, are you going to the biology field trip next week?"),
(3, 1, "2024-05-03", "2024-05-03", "Yes, Alice. It sounds fascinating!"),
(3, 2, "2024-05-03", "2024-05-03", "Bob, can you share your notes from last lecture?"),
(2, 3, "2024-05-03", "2024-05-03", "Sure, Charlie. I'll send them over."),
(1, 2, "2024-05-04", "2024-05-04", "Bob, do you want to grab lunch together today?"),
(2, 1, "2024-05-04", "2024-05-04", "I'm swamped with assignments, Alice. Maybe another time."),
(3, 1, "2024-05-04", "2024-05-04", "Alice, can you help me with this coding problem?"),
(1, 3, "2024-05-04", "2024-05-04", "Sure thing, Charlie. I'll be glad to assist."),
(1, 2, "2024-05-05", "2024-05-05", "Bob, the study group is meeting tomorrow. Are you joining?"),
(2, 1, "2024-05-05", "2024-05-05", "I'll try to make it, Alice."),
(3, 1, "2024-05-05", "2024-05-05", "Alice, do you know where I can find research materials for my project?"),
(1, 3, "2024-05-05", "2024-05-05", "Check out the university library, Charlie. They have extensive resources."),
(1, 2, "2024-05-06", "2024-05-06", "Bob, I heard there's a coding competition next month. Are you participating?"),
(2, 1, "2024-05-06", "2024-05-06", "Yes, Alice. It's a great opportunity to test our skills."),
(3, 1, "2024-05-06", "2024-05-06", "Alice, can you proofread my essay for the biology class?"),
(1, 3, "2024-05-06", "2024-05-06", "Sure, Charlie. Send it over and I'll take a look."),
(1, 2, "2024-05-07", "2024-05-07", "Bob, have you decided on your project idea for the engineering expo?"),
(2, 1, "2024-05-07", "2024-05-07", "I'm still brainstorming, Alice. It's a tough decision."),
(3, 1, "2024-05-07", "2024-05-07", "Alice, what do you think about studying abroad next semester?"),
(1, 3, "2024-05-07", "2024-05-07", "It sounds like an amazing opportunity, Charlie. Let's discuss it further."),
(1, 2, "2024-05-08", "2024-05-08", "Bob, are you free this weekend? We could go hiking."),
(2, 1, "2024-05-08", "2024-05-08", "Sounds good, Alice. I could use a break from studying."),
(3, 1, "2024-05-08", "2024-05-08", "Alice, do you have any recommendations for summer internships?"),
(1, 3, "2024-05-08", "2024-05-08", "Yes, Charlie. Let me send you some options I found."),
(1, 2, "2024-05-09", "2024-05-09", "Bob, did you hear about the campus cleanup event next week?"),
(2, 1, "2024-05-09", "2024-05-09", "Yes, Alice. I signed up already. Are you going?"),
(3, 1, "2024-05-09", "2024-05-09", "Alice, can I borrow your biology textbook for a few days?"),
(1, 3, "2024-05-09", "2024-05-09", "Of course, Charlie. I'll bring it to you tomorrow."),
(1, 2, "2024-05-10", "2024-05-10", "Bob, are you attending the engineering expo today?"),
(2, 1, "2024-05-10", "2024-05-10", "Yes, Alice. I'm looking forward to it."),
(3, 1, "2024-05-10", "2024-05-10", "Alice, have you decided on your elective courses for next semester?"),
(1, 3, "2024-05-10", "2024-05-10", "Not yet, Charlie. I'm still considering my options."),
(1, 2, "2024-05-11", "2024-05-11", "Bob, are you interested in joining the basketball club with me?");


-- Review data
INSERT INTO university_sync.review (owner_student_id, creation_date, last_edit_date, main_text, rating_given, review_to_location_id) VALUES
(1, '2024-05-01', '2024-05-01', 'Great dormitory with friendly staff.', 4, 1),
(2, '2024-05-02', '2024-05-02', 'The food here is delicious!', 5, 4),
(3, '2024-05-03', '2024-05-03', 'Enjoyed the cafeteria atmosphere.', 4, 5);
