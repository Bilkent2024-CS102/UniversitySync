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

-- Review data
INSERT INTO university_sync.review (owner_student_id, creation_date, last_edit_date, main_text, rating_given, review_to_location_id) VALUES
(1, '2024-05-01', '2024-05-01', 'Great dormitory with friendly staff.', 4, 1),
(2, '2024-05-02', '2024-05-02', 'The food here is delicious!', 5, 4),
(3, '2024-05-03', '2024-05-03', 'Enjoyed the cafeteria atmosphere.', 4, 5);
