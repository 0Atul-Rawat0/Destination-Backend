DROP DATABASE if exists WANDERLUST;
CREATE DATABASE WANDERLUST;
USE  WANDERLUST;
 
-- CREATE USERS TABLE  
CREATE TABLE USERS( 
USER_ID INT AUTO_INCREMENT,
USER_NAME VARCHAR(20) NOT NULL,
EMAIL_ID VARCHAR(30) NOT NULL,
CONTACT_NUMBER VARCHAR(10) NOT NULL UNIQUE,
PASSWORD VARCHAR(70) NOT NULL,  
constraint WL_USERS_Pk primary key ( USER_ID )
);

-- CREATE BOOKINGS TABLE  
CREATE TABLE BOOKING(
BOOKING_ID INT AUTO_INCREMENT,
CHECK_IN date NOT NULL,
CHECK_OUT DATE NOT NULL,
NO_OF_PEOPLE INT NOT NULL,
TOTAL_COST float NOT NULL,
TIME_OF_BOOKING datetime default CURRENT_TIMESTAMP,
DESTINATION_ID varchar(5) NOT NULL,
USER_ID INT NOT NULL,
constraint WL_BOOKINGS_Pk primary key (BOOKING_ID)
);

-- CREATE DESTINATION TABLE 
CREATE TABLE DESTINATION (
DESTINATION_ID VARCHAR(5),
CONTINENT VARCHAR(15) NOT NULL,
DESTINATION_NAME VARCHAR(100) NOT NULL,
IMAGE_URL VARCHAR(30) NOT NULL,
NO_OF_NIGHTS INT NOT NULL,
FLIGHT_CHARGE FLOAT NOT NULL,
CHARGE_PER_PERSON FLOAT NOT NULL,
DISCOUNT FLOAT NOT NULL DEFAULT 0.0,
AVAILABILITY INT NOT NULL,
DETAILS_ID VARCHAR(5) NOT NULL,
constraint WL_DESTINATION_pk primary key (DESTINATION_ID)
);

-- CREATE DESTINATION TABLE 
CREATE TABLE DETAILS(
DETAILS_ID VARCHAR(5),
ABOUT VARCHAR(700) NOT NULL,
PACKAGE_INCLUSION VARCHAR(500) NOT NULL,
HIGHLIGHTS VARCHAR(500) NOT NULL,
PACE VARCHAR(300) NOT NULL,
ITINERARY_ID varchar(5) NOT NULL,
CONSTRAINT WL_DETAILS_PK primary key (DETAILS_ID)
);

CREATE TABLE ITINERARY(
ITINERARY_ID VARCHAR(5),
FIRST_DAY VARCHAR(300) NOT NULL,
REST_OF_DAYS VARCHAR(300) NOT NULL,
LAST_DAY VARCHAR(300) NOT NULL,
constraint WL_ITI_PK primary key (ITINERARY_ID)
);

ALTER TABLE BOOKING ADD CONSTRAINT WL_BOOOKING_USER_FK FOREIGN KEY ( USER_ID ) REFERENCES USERS ( USER_ID );
ALTER TABLE BOOKING ADD CONSTRAINT WL_BOOOKING_DESTINATION_FK FOREIGN KEY ( DESTINATION_ID ) REFERENCES DESTINATION ( DESTINATION_ID );
ALTER TABLE DESTINATION ADD CONSTRAINT WL_DESTINATION_DETAILS_FK FOREIGN KEY ( DETAILS_ID ) REFERENCES DETAILS( DETAILS_ID );
ALTER TABLE DETAILS ADD CONSTRAINT WL_DETAILS_ITINERARY_FK FOREIGN KEY ( ITINERARY_ID ) REFERENCES ITINERARY( ITINERARY_ID );
-- DAY WISE DETAILS
INSERT INTO ITINERARY VALUES ('I1001','Travel day: Board your overnight flight to Athens','Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1002','Travel day: Board your overnight flight to Paris.','Vienna,Eiffel Tower photo stop,The Grand Canal,St. Mark’s Square,Ljubljana’s Prešeren Square,Graz’s Old Town','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1003','Travel day: Board your overnight flight to Zurich.','Chapel Bridge,Lake Annecy boat ride,Chamonix,Mont Blanc,Lake Como,Bernina Express train ride,Liechtenstein','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1004','Travel day:  Board your overnight flight to Budapest.','Hungarian Parliament Building,Szentendre,Schönbrunn Palace,Ringstrasse,Mikulov,Prague Castle,Wawel Cathedral','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1005','Travel day:  Board your overnight flight to London.','Trafalgar Square,Buckingham Palace,Eurostar train ride,Place de la Concorde,Eiffel Tower photo stop,The Colosseum','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1006','Cross the International Date Line in flight and lose a day.','Carlton Gardens,Alice Springs,Royal Flying Doctor Service,Uluru,Great Barrier Reef,Bondi Beach','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1007','Cross the International Date Line in flight and lose a day.','Melbourne,Uluru,Great Barrier Reef,Sydney Opera House,Glowworm Caves,Bob’s Peak,Milford Sound,Kiwi Wildlife Park','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1008','Travel day: Board your overnight flight to Beijing.','Shanghai,Forbidden City,Temple of Heaven,The Great Wall,Yangtze River cruise,Terra-Cotta Warriors,Yuyuan Garden','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1009','Travel day: Board your overnight flight to Beijing.','Shanghai, Forbidden City,Temple of Heaven,The Great Wall,Yangtze River cruise,Terra-Cotta Warriors,Yuyuan Garden','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1010','Travel day: Board your overnight flight to Singapore.','Southeast Asia,Thian Hock Keng Temple,Singapore Botanic Garden,Bako National Park,Semenggoh Wildlife Centre,Satang Island','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1011','Travel day: Board your overnight flight to Tokyo.','Kyoto,Meiji Shrine,Mount Fuji,Lake Ashi,Hakone Open-Air Museum,high-speed bullet train ride,Golden Pavilion','Departure:Transfer to the airport for your flight home.');
INSERT INTO ITINERARY VALUES ('I1012','Travel day: Board your overnight flight to Thailand.','Temple of the Golden Buddha,Damnoen Saduak Floating Market,Ayutthaya Ruins,Sukhothai Historical Park,Wat Rong Khun','Departure:Transfer to the airport for your flight home.');                   
INSERT INTO ITINERARY VALUES ('I1013','Cross the International Date Line in flight and lose a day.','Melbourne,Uluru,Great Barrier Reef,Sydney Opera House,Glowworm Caves,Bob’s Peak,Milford Sound,Kiwi Wildlife Park','Departure:Transfer to the airport for your flight home.'); 
INSERT INTO ITINERARY VALUES ('I1014','Board your overnight flight to Budapest.','Matthias Church,Danube River,Szentendre,Schönbrunn Palace,Ringstrasse','Departure:Transfer to the airport for your flight home.');  


-- INSERT CODE FOR DETAILS TABLE
INSERT INTO DETAILS VALUES('DL101','Watch the setting sun from the hilltops of Greece’s most famous islands.Experience ancient history and open-air museums in the capital of Athens. Then, the quintessential, beautiful Greek islands you’ve been dreaming of come to life on the isles of Mykonos and Santorini.',
'7 nights in handpicked hotels,7 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Greece,Athens,Mykonos,Santorini,Acropolis,Parthenon,Temple of Apollo,Ruins of Olympia,Ancient Theater of Epidaurus,Corinth Canal photo stop',
'On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails, with some hills and stairs.','I1001');
INSERT INTO DETAILS VALUES('DL102',
'Get swept away by the beauty of Europe’s most romantic cities.Journey through the dazzling imperial capitals of France, Italy, Slovenia, and Austria, soaking up each destination’s unique culture along the way. Sip coffee in charming cafes, spend your days exploring the grand boulevards or admiring sparkling canals, and watch each city’s skyline light up every evening.',
'10 nights in handpicked hotels,10 breakfasts,4 dinners with beer or wine,4 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Paris,Venice,Vienna,Eiffel Tower photo stop,The Grand Canal,St. Mark’s Square,Ljubljana’s Prešeren Square,Graz’s Old Town,Schönbrunn Palace','On this guided tour, you’ll walk for about 2.5 hours daily across mostly flat terrain, including paved roads and cobblestone streets, with some hills and stairs.','I1002');
INSERT INTO DETAILS VALUES('DL103','Experience rich culture against a backdrop of soaring Alpine peaks.Journey from Switzerland’s mountain-lined Lake Lucerne to France’s inspiring Lake Annecy. Take in the picturesque Chamonix Valley and stroll along Italy’s Lake Como. Then, cross Liechtenstein and hit the winter wonderland of Innsbruck before heading to Munich, the lively capital of Bavaria.',
'11 nights in handpicked hotels,11 breakfasts,5 dinners with beer or wine,8 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Alpine,Chapel Bridge,Lake Annecy boat ride,Chamonix, Mont Blanc,Lake Como,Bernina Express train ride,Liechtenstein,Nymphenburg Palace,The Glockenspiel',
'On this guided tour, you’ll walk for about 1 hour daily across moderately uneven terrain, including paved roads and cobblestone streets, with few hills or stairs.','I1003');
INSERT INTO DETAILS VALUES('DL104','Experience Eastern Europe in all its complexity. Imperial palaces, World War II sites, vibrant cafes—you can find it all in Eastern Europe. Journey along the Danube from the two-sided city of Budapest to Vienna, unrivaled for its beauty and majesty. Then, visit the castles and cathedrals of Prague and explore medieval Kraków before ending in modern Warsaw.',
'13 nights in handpicked hotels,13 breakfasts,5 dinners with beer or wine,6 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Matthias Church,Hungarian Parliament Building,Szentendre,Schönbrunn Palace,Ringstrasse,Mikulov,Prague Castle,Wawel Cathedral,Palace of Culture and Science',
'On this guided tour, you will walk for about 2.5 hours daily across mostly flat terrain, including paved roads and cobblestone streets, with few hills or stairs.','I1004');
INSERT INTO DETAILS VALUES('DL105','Delve deep into the history and culture of three inspiring, influential cities. Perhaps no cities have influenced the world more over the last 2,000 years than London, Paris, and Rome. On this guided tour, iconic sights like Big Ben, the Eiffel Tower, and the Colosseum are just the beginning of what you will see. Take it all in as you embark on this sweeping trip through England, France, and Italy.',
'9 nights in handpicked hotels,9 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'London,Paris,Rome,Trafalgar Square,Buckingham Palace,Eurostar train ride,Place de la Concorde,Eiffel Tower photo stop,The Colosseum,The Roman Forum',
'On this guided tour, you’ll walk for about 1 hour daily across mostly flat terrain, including paved roads and cobblestone streets, with few hills or stairs.','I1005');
INSERT INTO DETAILS VALUES('DL106','An island, a country, and a continent all in one.Experience the allure of the Land Down Under when you snorkel above the Great Barrier Reef, gaze at Uluru, and explore the Sydney Opera House. From multicultural Melbourne to the vast Outback, Australia is full of surprises for you to discover.',
'11 nights in handpicked hotels,11 breakfasts,1 lunch,2 dinners with beer or wine,6 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Australia,Sydney,Melbourne,Carlton Gardens,Alice Springs,Royal Flying Doctor Service,Uluru,Great Barrier Reef,Bondi Beach,Sydney Opera House',
'On this guided tour, you’ll walk for about 1 hour daily across mostly flat terrain, including paved roads and gravel paths, with few hills.','I1006');
INSERT INTO DETAILS VALUES('DL107','Australia and New Zealand—a world away. From Australia’s Great Barrier Reef and the rugged Outback to New Zealand’s sheep-dotted plains and cliff-lined fjords, the South Pacific features a lineup of dramatic landscapes. In-between outdoor adventures, you’ll discover sophisticated, multicultural cities and an irresistible, carpe diem spirit on this tour.',
'19 nights in handpicked hotels,19 breakfasts,1 lunch,6 dinners with beer or wine,13 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach, Flights from Melbourne to Alice Springs, Uluru Region to Great Barrier Reef Region, Great Barrier Reef Region to Sydney, Sydney to Fiordland National Park Region, Queenstown to Rotorua',
'Sydney,Australia,New Zealand,Melbourne,Uluru,Great Barrier Reef,Sydney Opera House,Glowworm Caves,Bob’s Peak,Milford Sound,Kiwi Wildlife Park,Kiwi home-hosted dinner, Auckland’s Sky Tower',
'On this guided tour, you’ll walk for about 1 hour daily across mostly flat terrain, including paved roads and gravel paths, with few hills.','I1007');
INSERT INTO DETAILS VALUES('DL108','Experience China and all of its contrasts. China’s sheer size is impossible to fathom until you walk across the expanse of Tiananmen Square or gaze at the seemingly endless Great Wall. Take a leisurely cruise down the Yangtze River and ride the world’s fastest train. Discover this magnificent country, from pristine landscapes and towering cityscapes to ancient sites and modern marvels.',
'13 nights in handpicked hotels,4 breakfasts,8 lunch,6 dinners with beer or wine,13 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'China,Beijing,Yangtze,Shanghai,Forbidden City,Temple of Heaven,Summer Palace,the Great Wall,Yangtze River cruise,Terra-Cotta Warriors,Jade Buddha Temple,Yuyuan Garden',
'On this guided tour, you’ll walk for about 2 hours daily across moderately uneven terrain, including paved roads and unpaved trails, with some hills and stairs.','I1008');
INSERT INTO DETAILS VALUES('DL109','Toast to the South American flavors of these three unique countries.The people of Uruguay, Argentina, and Chile each offer a unique take on the art of cooking and winemaking. Learn about the traditional side of wine production at a family-run bodega outside of Montevideo. Soak in the flavors—and the views—at a sophisticated wine estate in Mendoza. Stop for a sip in the acclaimed Aconcagua Valley wine region. Whether you’re visiting a small village or an expansive market, you’ll discover how locals celebrate their country’s tasty traditions on this culinary adventure.',
'8 nights in handpicked hotels,8 breakfasts,4 lunch,3 dinners with beer or wine,5 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Uruguay,Argentina,Chile,Mercado del Puerto,Canelones wine tastings,Mendoza wine tastings,Aconcagua Valley wine tasting,Santa Lucía Hill,Chilean cooking class',
'On this guided tour, you’ll walk for about 2 hours daily across mostly flat terrain, including paved roads and cobblestone streets with some hills.','I1009');
INSERT INTO DETAILS VALUES('DL110','Discover the mystique of Singapore and Malaysia.Travel from bustling city streets to pristine shorelines, taking in some of Southeast Asia’s most interesting cultures along the way. Stand in awe of Singapore’s world-class amenities and futuristic innovation before retreating into nature in Borneo, where dense jungles harbor impressive animal life. Round out your trip in cosmopolitan Kuala Lumpur to see how deep-rooted traditions intersect with the present-day.',
'9 nights in handpicked hotels,9 breakfasts,4 lunch,3 dinners with beer or wine,5 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Singapore,Malaysia,Southeast Asia,Thian Hock Keng Temple,Singapore Botanic Garden,Bako National Park,Semenggoh Wildlife Centre,Satang Island,Malacca River cruise,Petronas Towers',
'On this guided tour, you’ll walk for about 2.5 hours daily across moderately uneven terrain, including paved roads and unpaved paths, with some hills and stairs.','I1010');
INSERT INTO DETAILS VALUES('DL111','Discover how centuries-old traditions coexist with cutting-edge culture.The traditional Japanese tea ceremony is the embodiment of Japanese hospitality, representing harmony, respect, purity, and tranquility. You’ll experience this Zen aesthetic every day of your Japanese tour—and not only while drinking tea. Uncover ancient temples alongside futuristic cityscapes and witness how Japan’s art, architecture, and day-to-day culture continue to reflect its time-honored philosophies.',
'8 nights in handpicked hotels,8 breakfasts,4 lunch,3 dinners with beer or wine,5 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Japan,Tokyo,Kyoto,Meiji Shrine, Mount Fuji,Lake Ashi,Hakone Open-Air Museum,High-speed bullet train ride,Golden Pavilion,Fushimi Inari complex,Nara,Osaka Castle',
'On this guided tour, you’ll walk for about 2.5 hours daily across mostly flat terrain, including paved roads and gravel paths, with few hills. You may need to climb stairs when visiting temples.','I1011');
INSERT INTO DETAILS VALUES('DL112','Experience the warm welcome of the Land of Smiles.From bustling Bangkok, you’ll travel north to ancient ruined capitals, local villages, and mountaintop temples to investigate all sides of this vibrant country of contrasts. The spice and rice will nourish your body; the scenery and history will nourish your soul.',
'11 nights in handpicked hotels,12 breakfasts,4 lunches,4 dinners with beer or wine,8 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Grand Palace,Temple of the Golden Buddha,Damnoen Saduak Floating Market,Ayutthaya Ruins,Sukhothai Historical Park,Wat Rong Khun,Elephant Nature Park',
'On this guided tour, you’ll walk for about 1 hour daily across uneven terrain with some hills.','I1012');
INSERT INTO DETAILS VALUES('DL113','Spire-studded skylines and fascinating histories are just the beginning.Dotted with hilltop castles, ornate cathedrals, and imperial palaces, Vienna, Budapest, and Prague will captivate you. Spend a few days getting to know each of these dynamic cities­—you’ll discover their vivid World War II history, radical art movements, and unique local cultures.',
'9 nights in handpicked hotels,10 breakfasts,3 dinners with beer or wine,3 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach',
'Hungarian Parliament Building,Matthias Church,Danube River,Szentendre,Schönbrunn Palace,Ringstrasse,Prague Castle,St. Vitus Cathedral',
'On this guided tour, you’ll walk for about 3 hours daily across mostly flat terrain with some cobblestone streets, hills, and stairs.','I1013');
INSERT INTO DETAILS VALUES('DL114','Australia and New Zealand—a world away.From Australia’s Great Barrier Reef and the rugged Outback to New Zealand’s sheep-dotted plains and cliff-lined fjords, the South Pacific features a lineup of dramatic landscapes. In-between outdoor adventures, you’ll discover sophisticated, multicultural cities and an irresistible, carpe diem spirit on this tour.',
'19 nights in handpicked hotels,20 breakfasts,1 lunch,6 dinners with beer or wine,13 guided sightseeing tours,Expert tour director & local guides,Private deluxe motor coach,Flights from Melbourne to Alice Springs, Uluru Region to Great Barrier Reef Region, Great Barrier Reef Region to Sydney, Sydney to Fiordland National Park Region, Queenstown to Rotorua',
'Melbourne,Uluru,Great Barrier Reef,Sydney Opera House,Glowworm Caves,Bob’s Peak,Milford Sound,Kiwi Wildlife Park,Kiwi home-hosted dinner,Auckland’s Sky Tower',
'On this guided tour, you’ll walk for about 1 hour daily across mostly flat terrain, including paved roads and gravel paths, with few hills.','I1014');



-- INSERT CODE TO USERS TABLE
INSERT INTO DESTINATION VALUES ('D1001','Europe','A Week in Greece: Athens, Mykonos & Santorini','/assets/geece.jpg',7,500.0,2499.0,0.0,30,'DL101');
INSERT INTO DESTINATION VALUES ('D1002','Europe','Romantic Europe: Paris, Venice & Vienna','/assets/romantic.jpg',10,500.0,2729.0,0.0,30,'DL102');
INSERT INTO DESTINATION VALUES ('D1003','Europe','Jewels of Alpine Europe','/assets/europe.jpg',11,500.0,2799.0,0.0,30,'DL103');
INSERT INTO DESTINATION VALUES ('D1004','Europe','Highlights of Eastern Europe','/assets/easteurope.jpg',13,500.0,2699.0,0.0,30,'DL104');
INSERT INTO DESTINATION VALUES ('D1005','Europe','London, Paris & Rome','/assets/rome.jpg',13,500.0,2699.0,0.0,30,'DL105');
INSERT INTO DESTINATION VALUES ('D1006','Australia','Grand Tour of Australia','/assets/aus1.jpg',14,500.0,4549.0,0.0,30,'DL106');
INSERT INTO DESTINATION VALUES ('D1007','Australia','Australia & New Zealand','/assets/aus2.jpg',22,500.0,6399.0,0.0,30,'DL107');
INSERT INTO DESTINATION VALUES ('D1008','Asia','China: Beijing, the Yangtze & Shanghai','/assets/china.jpg',13,500.0,2399.0,0.0,30,'DL108');
INSERT INTO DESTINATION VALUES ('D1009','South America','Food & Wine: Uruguay, Argentina & Chile','/assets/wine.jpg',8,500.0,3549.0,0.0,30,'DL109');
INSERT INTO DESTINATION VALUES ('D1010','Asia','Singapore & Malaysia: A Journey through Southeast Asia','/assets/singapore.jpg',9,500.0,2649.0,0.0,30,'DL110');
INSERT INTO DESTINATION VALUES ('D1011','Asia','Highlights of Japan: Tokyo to Kyoto','/assets/tokyo.jpg',8,500.0,3349.0,0.0,30,'DL111');
INSERT INTO DESTINATION VALUES ('D1012','Asia','Thailand : The Golden Kingdom','/assets/Thailand.jpg',11,400.0,2249.0,5.0,15,'DL112');
INSERT INTO DESTINATION VALUES ('D1013','Europe','Budapest, Vienna & Prague','/assets/prague.jpg',9,400.0,2049.0,5.0,15,'DL113');
INSERT INTO DESTINATION VALUES ('D1014','Australia','Australia & New Zealand','/assets/aus3.jpg',19,400.0,6399.0,5.0,15,'DL114');


-- INSERT CODE TO USERS TABLE
INSERT INTO USERS VALUES (101, 'SCOTT', 'scott@stark.com', '8884967823', '3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3');
-- Scott@123
INSERT INTO USERS VALUES (102, 'TONY', 'tony@stark.com', '8875632142', '1f7cbaa9168ffce48872d8fc4e5429dee55ed8f21d8d84bccd6aaa2a72ae1d79');
-- Tony@123
INSERT INTO USERS VALUES (103, 'STEVE', 'steve@gmail.com', '9880253413', '97661249431ccedba1711b78fb58eceb2c03054a07a7b684ad53048691b34435');
-- Steve@123
INSERT INTO USERS VALUES (104, 'BANNER', 'banner@Uv.com', '8882039476', '9a8d7e96acac7b73f1f9c994dd512df9068bb0549961e42333745c67a994e6f1');
-- Banner@123

-- INSERT CODE TO BOOKING TABLE

INSERT INTO BOOKING VALUES (1001,sysdate()- interval 12 day,sysdate()- interval 5  day,2,4356.0,current_time(),'D1001',101);
INSERT INTO BOOKING VALUES (1002,sysdate()- interval 17 day,sysdate()- interval 6  day,4,11196.0,current_time(),'D1002',101);
INSERT INTO BOOKING VALUES (1003,sysdate()- interval 17 day,sysdate()- interval 4  day,1,2799.0,current_time(),'D1003',102);
INSERT INTO BOOKING VALUES (1004,sysdate()- interval 17 day,sysdate()- interval 3  day,3,8097.0,current_time(),'D1004',103);
INSERT INTO BOOKING VALUES (1005,sysdate()- interval 24 day,sysdate()- interval 2  day,5,13495.0,current_time(),'D1005',103);
commit;


SELECT * FROM USERS;