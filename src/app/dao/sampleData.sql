insert into university_sync.campus (campus_name)
values ('main campus'),
('east campus');

insert into university_sync.major (major_code, major_full_name)
values ('cs', 'comp science'),
('eee', 'electric electronic'),
('pols', 'political');

insert into university_sync.dormitory (dorm_name, dorm_description, link_to_dormitory_picture, dormitory_in_campus_id)
values ('91', 'a male student dorm in east campus that has 2, 3, 4 person rooms. prep students reside here.', 'picutres\91.png or whatever', '2'),
('62', 'a dorm in central campus that has idk capacity rooms. cheap dorm.', 'picutres\62.png or whatever', '1'),
('70', 'a ultra super luxury dorm in central campus. only gazillioners reside here.', 'picutres\70.png or whatever', '1');

insert into university_sync.room_type (room_type_description, capacity, is_bunk, has_private_bathroom, room_in_dormitory_id)
values ('regular 2 person room in east campus', 2, false, false, 1),
('4 person cheaper room in east campus', 4, true, false, 1),
('ultra delux super luxury room', 1, false, true, 3),
('bunk room in central campus', 2, true, false, 2);

insert into university_sync.cafeteria (cafeteria_name, cafeteria_description, min_price, max_price, link_to_cafeteria_picture, cafeteria_in_campus_id)
values ('bilka', 'cafeteria right next to dorm 76', 50, 250, 'pictures\bilka.png or whatever', 1),
('ee building mozart', 'cafe in ee building that sells sandwich, coffee, etc.', 60, 200, 'pictures\mozart.png or whatever', 1);