-- Insert data into campus table
INSERT INTO university_sync.campus (campus_name) VALUES
('Main Campus'),
('East Campus');

-- Insert data into location table
INSERT INTO university_sync.location (location_type) VALUES
('Dormitory'),
('Dormitory'),
('Dormitory'),
('Dormitory'),
('Dormitory'),
('Dormitory'),
('Dormitory'),
('Cafeterias'),
('Cafeterias'),
('Cafeterias'),
('Cafeterias'),
('Cafeterias');

-- Insert data into major table
INSERT INTO university_sync.major (major_code, major_full_name) VALUES
('CS', 'Computer Science'),
('EE', 'Electrical Engineering'),
('ME', 'Mechanical Engineering'),
('BA', 'Business Administration'),
('LAW', 'Law'),
('IE', 'Industrial Engineering'),
('MBG', 'Molecular Biology and Genetics');

INSERT INTO university_sync.dormitory (dormitory_location_id, dorm_name, dorm_description, link_to_dormitory_picture, dormitory_in_campus_id) VALUES
(1, '60', 'Double (Bunk Bed)', 'dorm60.jpg', 1),
(2, '64', 'Single, Suite', 'dorm64.jpg', 1),
(3, '70', 'Single Superior', 'dorm70.jpg', 1),
(4, '72', 'Triple', 'dorm72.jpg', 1),
(5, '76', 'Single', 'dorm76.jpg', 1),
(6, '77', 'This dorm has Double, Triple, Quadruple', 'dorm77.jpg', 1),
(7, '82', 'Double (Bathroom)', 'dorm82.jpg', 1);

-- Insert data into room_type table based on provided dormitory data
INSERT INTO university_sync.room_type (room_type_description, capacity, is_bunk, has_private_bathroom, room_in_dormitory_id) VALUES
('Double (Bunk Bed)', 2, true, false, 1),  -- Dormitory 60
('Single, Suite', 1, false, true, 2),      -- Dormitory 64
('Single Superior', 1, false, true, 3),    -- Dormitory 70
('Triple', 3, false, false, 4),            -- Dormitory 72
('Single', 1, false, false, 5),            -- Dormitory 76
('Double', 2, false, false, 6),            -- Dormitory 77
('Triple', 3, false, false, 6),            -- Dormitory 77
('Quadruple', 4, false, false, 6),         -- Dormitory 77
('Double (Bathroom)', 2, false, true, 7);  -- Dormitory 82

-- Creating cafeterias
INSERT INTO university_sync.cafeteria (cafeteria_location_id, cafeteria_name, cafeteria_description, min_price, max_price, link_to_cafeteria_picture, cafeteria_in_campus_id) VALUES
(8, 'Marmara Cafeteria', 'Cafeteria with various food options, cheap', 60.00, 60.00, 'main_cafeteria.jpg', 1),
(9, 'Fameo', 'One of the only food options in the east campus', 200.00, 300.00, 'east_cafeteria.jpg', 2),
(10, 'Cati Cafe', 'Cafe with many food options, food is bought by the kg', 120.00, 200.00, 'whatever.jpg', 1),
(11, 'Coffee Break', 'A very reasonably priced cafeteria with a central location', 90.00, 190.00, 'ok.jpg', 1),
(12, 'Mozart Cafe', 'Has a very wide selection of desserts with cheap prices', 60.00, 110.00, 'mozart.jpg', 2); 


-- Insert data into menu_item table
INSERT INTO university_sync.menu_item (menu_item_name, price, menu_item_in_cafeteria_id) VALUES
('Changes Daily, 4 Plates', 60.00, 8),
('Pizza', 8.50, 9),
('Salad', 60.00, 9),
('Pasta', 7.00,  9),
('Cake', 4.50, 9),
('Muffin', 2.50, 10),
('Burger', 10.00, 12),
('Donut', 1.75,  9),
('Fries', 3.00, 12),
('Wrap', 6.00,  11),
('Lasagna', 14.00,  9),
('Salmon', 18.50, 12),
('Falafel', 7.25, 10),
('Waffle', 4.75, 10),
('Pudding', 3.50, 12);

-- Insert Students
INSERT INTO university_sync.student (full_name, email, pass, biography, link_to_profile_picture, student_major, student_room_type_id) VALUES
('Ahmet Yılmaz', 'ahmet.yilmaz@ug.bilkent.edu.tr', 'password123', 'Has a black belt in karate', 'ahmet.jpg', 'CS', 1),
('Ayşe Demir', 'ayse.demir@ug.bilkent.edu.tr', 'securepass', 'Volunteers at an animal shelter', 'ayse.jpg', 'EE', 2),
('Mehmet Kaya', 'mehmet.kaya@ug.bilkent.edu.tr', 'mypassword', 'Loves to travel and has visited 10 countries', 'mehmet.jpg', 'CS', 4),
('Fatma Çelik', 'fatma.celik@ug.bilkent.edu.tr', 'pass1234', 'Enjoys painting in her free time', 'fatma.jpg', 'MBG', NULL),
('Canan Öz', 'canan.oz@ug.bilkent.edu.tr', 'strongpass', 'Plays the violin in the university orchestra', 'canan.jpg', 'EE', NULL),
('Emre Şahin', 'emre.sahin@ug.bilkent.edu.tr', 'safe123', 'Is a marathon runner', 'emre.jpg', 'IE', 2),
('Zeynep Kılıç', 'zeynep.kilic@ug.bilkent.edu.tr', 'zeynep123', 'Has written a book on psychology', 'zeynep.jpg', 'CS', 1),
('Burak Arslan', 'burak.arslan@ug.bilkent.edu.tr', 'burakpass', 'Enjoys skydiving', 'burak.jpg', 'EE', 3),
('Selin Polat', 'selin.polat@ug.bilkent.edu.tr', 'selin456', 'Has a photography blog', 'selin.jpg', 'LAW', NULL),
('Deniz Uslu', 'deniz.uslu@ug.bilkent.edu.tr', 'deniz789', 'Won a national chess championship', 'deniz.jpg', 'IE', 2),
('Masa Sandalye', 'masa.sandalye@ug.bilkent.edu.tr', 'masa', 'Won a national chess championship', 'masa.jpg', 'CS', 2);

-- Insert data into friend_request table
INSERT INTO university_sync.friend_request (sender_id, receiver_id) VALUES
(1, 3),
(2, 5),
(3, 7),
(4, 1),
(5, 6),
(6, 2),
(7, 4),
(8, 9),
(9, 10),
(10, 8);

-- Insert data into student_friendship table
INSERT INTO university_sync.student_friendship (first_student_id, second_student_id) VALUES
(2, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(8, 10),
(1, 9),
(2, 10),
(1, 4);

-- Insert data into forum_post table
INSERT INTO university_sync.forum_post (owner_student_id, creation_date, last_edit_date, heading, main_text, like_count) VALUES
(3, '2024-05-03 12:00:00', '2024-05-03 12:00:00', 'Events at Bilkent', 'Check out the upcoming events at Bilkent!', 8),
(4, '2024-05-04 13:00:00', '2024-05-04 13:00:00', 'Sports at Bilkent', 'Interested in sports? Join our club!', 20),
(5, '2024-05-05 14:00:00', '2024-05-05 14:00:00', 'Coding Workshop', 'Join us for a coding workshop next week!', 12),
(6, '2024-05-06 15:00:00', '2024-05-06 15:00:00', 'Book Exchange', 'Looking for a book? Lets exchange!', 18),
(7, '2024-05-07 16:00:00', '2024-05-07 16:00:00', 'Volunteering Opportunities', 'Opportunities to volunteer in the community.', 25),
(8, '2024-05-08 17:00:00', '2024-05-08 17:00:00', 'Student Council Elections', 'Nominate yourself for the student council!', 30),
(9, '2024-05-09 18:00:00', '2024-05-09 18:00:00', 'Music Night', 'Join us for a music night this Friday!', 22),
(10, '2024-05-10 19:00:00', '2024-05-10 19:00:00', 'Language Exchange', 'Practice languages with fellow students.', 17);

-- Insert data into tag_forum_post table
INSERT INTO university_sync.tag_forum_post (tag, tagged_forum_post_id) VALUES
('Introduction', 1),
('Study', 2);

-- Insert data into like_forum_post table
INSERT INTO university_sync.like_forum_post (liked_by_student_id, liked_forum_post_id) VALUES
(3, 4),
(4, 3),
(5, 5),
(6, 6),
(7, 2),
(8, 1),
(9, 3),
(10, 5);

-- Insert data into reply table
INSERT INTO university_sync.reply (owner_student_id, creation_date, last_edit_date, main_text, replies_to_forum_post_id) VALUES
(3, '2024-05-03 14:00:00', '2024-05-03 14:00:00', 'Looking forward to the events!', 1),
(4, '2024-05-04 15:00:00', '2024-05-04 15:00:00', 'Count me in for the sports club!', 1),
(5, '2024-05-05 16:00:00', '2024-05-05 16:00:00', 'Ill definitely attend the coding workshop.', 2),
(6, '2024-05-06 17:00:00', '2024-05-06 17:00:00', 'Let me know which book you need!', 3),
(6, '2024-05-06 17:00:00', '2024-05-06 17:00:00', 'OK, see you at yemekhane.', 4),
(7, '2024-05-07 18:00:00', '2024-05-07 18:00:00', 'Im interested in volunteering.', 7),
(8, '2024-05-08 19:00:00', '2024-05-08 19:00:00', 'Excited for the student council elections!', 2),
(9, '2024-05-09 20:00:00', '2024-05-09 20:00:00', 'Looking forward to the music night!', 3),
(10, '2024-05-10 21:00:00', '2024-05-10 21:00:00', 'I can help with language exchange!', 5),
(8, '2024-05-08 19:00:00', '2024-05-08 19:00:00', 'Excited for the student council elections!', 1);

-- Insert data into dorm_transfer_post table
INSERT INTO university_sync.dorm_transfer_post (owner_student_id, creation_date, last_edit_date, heading, main_text, posted_room_id) VALUES
(3, '2024-05-03 09:00:00', '2024-05-03 09:00:00', 'Hey Im looking to Transfer', 'I want to transfer to a double room in Dormitory 82.', 4),
(1, '2024-05-03 09:00:00', '2024-05-03 09:00:00', 'Please Transfer', 'I want to transfer to a single room in Dormitory 70.', 1);

-- Insert data into event_post table
INSERT INTO university_sync.event_post (owner_student_id, creation_date, last_edit_date, heading, main_text, location, event_date) VALUES
(2, '2024-05-05 15:00:00', '2024-05-05 15:00:00', 'Music Festival', 'Experience a day full of music and fun!', 'Amphitheater', '2024-06-15 14:00:00'),
(3, '2024-05-06 16:00:00', '2024-05-06 16:00:00', 'Charity Run', 'Join us for a charity run for a good cause!', 'Sports Complex', '2024-06-10 08:00:00'),
(4, '2024-05-07 17:00:00', '2024-05-07 17:00:00', 'Art Exhibition', 'Discover the talents of our students at the art exhibition.', 'Fine Arts Gallery', '2024-06-20 12:00:00'),
(5, '2024-05-08 18:00:00', '2024-05-08 18:00:00', 'Movie Night', 'Watch classic movies under the stars!', 'Open Air Theater', '2024-06-05 19:00:00'),
(6, '2024-05-09 19:00:00', '2024-05-09 19:00:00', 'Food Festival', 'Indulge in delicious cuisines from around the world.', 'Student Center', '2024-06-25 11:00:00');

-- Insert data into follow_event_post table
INSERT INTO university_sync.follow_event_post (followed_by_student_id, followed_event_post_id) VALUES
(2, 3),
(3, 1);

-- Insert data into message table
INSERT INTO university_sync.message (owner_student_id, receiver_student_id, creation_date, last_edit_date, main_text) VALUES
(3, 4, '2024-05-06 10:00:00', '2024-05-06 10:00:00', 'Hey there, are you coming to the charity run?'),
(4, 3, '2024-05-06 11:00:00', '2024-05-06 11:00:00', 'Yes, Ill be there to support the cause.'),
(4, 3, '2024-05-07 12:00:00', '2024-05-07 12:00:00', 'Hey, do you want to watch a movie this weekend?'),
(3, 4, '2024-05-07 13:00:00', '2024-05-07 13:00:00', 'Sure! Which movie are you planning to watch?'),
(8, 9, '2024-05-08 14:00:00', '2024-05-08 14:00:00', 'Hi, I heard youre interested in volunteering. Can you tell me more about it?'),
(9, 8, '2024-05-08 15:00:00', '2024-05-08 15:00:00', 'Of course! Lets meet up and discuss.'),
(10, 1, '2024-05-09 16:00:00', '2024-05-09 16:00:00', 'Hey John, how was the music night?'),
(1, 10, '2024-05-09 17:00:00', '2024-05-09 17:00:00', 'It was fantastic! You should have come.');

-- Insert data into review table
INSERT INTO university_sync.review (owner_student_id, creation_date, last_edit_date, main_text, rating_given, review_to_location_id) VALUES
(3, '2024-05-08 17:00:00', '2024-05-08 17:00:00', 'Friendly staff and good atmosphere.', 4, 10),
(4, '2024-05-09 18:00:00', '2024-05-09 18:00:00', 'Amazing service and delicious food!', 5, 11),
(1, '2024-05-06 15:00:00', '2024-05-06 15:00:00', 'Great food options!', 5, 8),
(2, '2024-05-07 16:00:00', '2024-05-07 16:00:00', 'Nice place but a bit crowded.', 4, 9),
(9, '2024-05-12 21:00:00', '2024-05-12 21:00:00', 'Friendly community and quiet environment.', 5, 3),
(2, '2024-05-13 22:00:00', '2024-05-13 22:00:00', 'Excellent facilities and prompt maintenance service.', 4, 4),
(5, '2024-05-14 23:00:00', '2024-05-14 23:00:00', 'Great security measures and comfortable living spaces.', 4, 5),
(6, '2024-05-15 00:00:00', '2024-05-15 00:00:00', 'Good internet connectivity and spacious study areas.', 5, 6);
