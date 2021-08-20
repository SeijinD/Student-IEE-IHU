INSERT INTO `communities` (`title_gr`, `title_en`, `category`, `link`) VALUES
('Τμήμα Μηχανικών Πληροφορικής Και Ηλεκτρονικών Συστημάτων ΔΙΠΑΕ θεσ/νίκης', 'Department of Information and Electronic Engineering I.H.U. Thessalonikis', 'facebook_pages', 'https://www.facebook.com/MixanikonPliroforikisKaiIlektronikonSystimaton/'),
('Ενημέρωση Φοιτητών ΔΙΠΑΕ Σίνδου', 'Inform students I.H.U. Sindos', 'facebook_pages', 'https://www.facebook.com/tei.thessalonikhs/'),
('ΔΙΠΑΕ Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων', 'I.H.U. Information and Electronic Engineering', 'discord_servers', 'https://discord.gg/9tUnEVMe'),
('ΤΜΗΜΑ ΠΛΗΡΟΦΟΡΙΚΗΣ Α.Τ.Ε.Ι.ΤΗ.', 'A.Τ.Ε.Ι. of Thessaloniki, Department of Information Technology', 'facebook_groups', 'https://www.facebook.com/groups/pliroforikarioi/'),
('ΔΙ.ΠΑ.Ε. - Τμήμα Μηχανικών Πληροφορικής, Υπολογιστών και Τηλεπικοινωνιών', 'I.H.U. - Department of Information and Electronic Engineering', 'facebook_groups', 'https://www.facebook.com/groups/pliroforikiserron/'),
('Περιβαλλοντικη Ομαδα ΔΙΠΑΕ Θεσσαλονικης', 'Environmental group I.H.U Thessalonikis', 'facebook_groups', 'https://www.facebook.com/groups/189405949034962'),
('ΔΙΠΑΕ(Σινδος)', 'I.H.U. (Sindos)', 'facebook_groups', 'https://www.facebook.com/groups/1542744542667713');

INSERT INTO `maps` (`latitude`, `longitude`, `title_gr`, `title_en`, `description_gr`, `description_en`) VALUES
('40.65709541436208', '22.80369452293734', 'Διεθνές Πανεπιστήμιο Ελλάδος - Αλεξανδρεια Πανεπιστημιουπολη', 'International Hellenic University - Alexandria Campus', 'Το πανεπιστήμιο μας.', 'Our university'),
('40.656089735641', '22.804182812928136', 'Τμήμα ΜΠΗΣ - Κτίριο Π', 'Department ΜΠΗΣ - Building Π', 'Τμήμα Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων - Κτίριο Π', 'Department of Information and Electronic Engineering - Building Π'),
('40.655602367915854', '22.805878923368827', 'Τμήμα ΜΠΗΣ - Κτίριο H', 'Department ΜΠΗΣ - Building Η', 'Τμήμα Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων - Κτίριο Η', 'Department of Information and Electronic Engineering - Building Η'),
('40.655740736173236', '22.80576627059863', 'Γραμματεία τμήματος ΜΠΗΣ', 'Secretariat of the ΜΠΗΣ department ', 'Γραμματεία τμήματος Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων', 'Secretariat of the Department of Information and Electronic Engineering');

INSERT INTO `offers` (`title_gr`, `title_en`, `description_gr`, `description_en`, `category`, `link`) VALUES
('Jetbrains', 'Jetbrains', 'Μια μεγάλη συλλογή από προγράμματα για προγραμματιστές με ετήσια άδεια.', 'A large collection of programs for developers with an annual license.', 'softwares', 'https://www.jetbrains.com'),
('Kivuto', 'Kivuto', 'Μια μεγάλη συλλογή από προγράμματα διάφορων εταιριών με πρόσφορες.', 'A large collection of programs of various companies with offers.', 'softwares', 'https://e5.onthehub.com/WebStore/ProductsByMajorVersionList.aspx?ws=be0fe910-869b-e011-969d-0030487d8897'),
('Microsoft', 'Microsoft', 'Μια μεγάλη συλλογή από προγράμματα της Microsoft.', 'A large collection of Microsoft programs. ', 'softwares', 'https://imselab-atei-thessaloniki.weebly.com/ms-azure-gr.html'),
('Github', 'Github', 'Μια μεγάλη συλλογή από συνδρομές και δώρα σε διάφορα προγράμματα και υπηρεσίες.', 'A large collection of subscriptions and gifts in various programs and services. ', 'softwares', 'https://education.github.com');

INSERT INTO `official_services` (`title_gr`, `title_en`, `description_gr`, `description_en`, `category`, `link`) VALUES
('Tμήμα Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων', 'Department of Information and Electronic Engineering', 'Η ιστοσελίδα του τμήματος.', 'The department website.', 'department', 'https://www.iee.ihu.gr'),
('Moodle', 'Moodle', 'Ο διαδικτυακός χώρος φιλοξενήσεις μαθημάτων.', 'The web site hosting courses. ', 'department', 'https://moodle.teithe.gr/course/index.php?categoryid=125'),
('Moodle Exams', 'Moodle Exams', 'Ο διαδικτυακός χώρος για εξετάσεις μαθημάτων.', 'The web site for course exams. ', 'department', 'https://exams-iee.the.ihu.gr'),
('Ηλεκτρονικό Σύστημα Διπλωματικών', 'Electronic Diplomacy System ', 'Ανάθεση και προβολή διπλωματικών/πτυχιακών.', 'Assignment and view of diplomas/graduates.', 'department', 'https://thesis.iee.ihu.gr'),
('Δηλώσεις Μαθημάτων', 'Course Statements ', 'Δηλώσεις και προβολή μαθημάτων εξαμήνου.', 'Registration and view of semester courses', 'department', 'http://aris.noc.teithe.gr/el/diloseis'),
('Youtube', 'Youtube', 'Το Youtube του τμήματος.', 'YouTube of the department.', 'department', 'https://www.youtube.com/channel/UCq-5LWrceZMl4b5ulXF4RAg'),
('Apps', 'Apps', 'Κύριος λόγος οι ανακοινώσεις του τμήματος.', 'Main reason the announcements of the department.', 'department', 'https://apps.iee.ihu.gr'),
('Feeding', 'Feeding', 'Σύστημα Αιτήσεων Δικαιούχων Δωρεάν Σίτισης.', 'Free Feeding Application System', 'university', 'http://feeding.teithe.gr'),
('Pithia', 'Pithia', 'Κέντρο έλεγχου μαθημάτων και βαθμολόγιων φοιτητή.', 'Student control center for courses and grades.', 'department', 'http://pithia.teithe.gr/unistudent'),
('ECTS 160', 'ECTS 160', 'Έλεγχος για τον αν φοιτητής έχει συμπληρώσει τουλάχιστον 160/210 ΠΜ*', 'Check if student has completed at least 160/210 AM', 'department', 'https://www.iee.ihu.gr/ects160'),
('Διεύθυνση Τεχνικών Υπηρεσιών', 'Technical Services Department ', 'Αιτήσεις έλεγχου/επιδιόρθωσης εγκαταστάσεων και μεγάλες Αίθουσες.', 'Requests to check/repair facilities and large rooms.', 'university', 'http://dty.teithe.gr'),
('Webmail', 'Webmail', 'Το Email του τμήματος.', 'The Email of the department. ', 'department', 'https://webmail.teithe.gr'),
('Πρακτική Άσκηση', 'Internships', 'Η σελίδα για την πρακτική άσκηση των φοιτητών.', 'The student internship page.', 'department', 'http://placements.it.teithe.gr'),
('Κέντρο Δικτύου Αιτήματα', 'Network Center Requests', 'Η σελίδα του κέντρου δεκτού για σχετικά αιτήματα με τους λογαριασμούς.', 'The Admittance Center page for related requests with accounts.', 'university', 'https://helpdesk.the.ihu.gr/index.php'),
('Κεντρική Βιβλιοθήκη Πανεπιστήμιου', 'Central Library of University', 'Μπορείς να αναζητήσεις και βιβλία για αν υπάρχουν.', 'You can search for books if they exist.', 'university', 'https://www.lib.teithe.gr');

INSERT INTO `unofficial_services` (`title_gr`, `title_en`, `description_gr`, `description_en`, `creator_gr`, `creator_en`, `category`, `link`) VALUES
('Φτιάξε το πρόγραμμα του εξαμήνου σου', 'Make your semester schedule', 'Μια προσπάθεια ενός φοιτητή για να διευκολύνει την δημιουργία του εξαμηνιαίου προγράμματος.', 'An effort of a student to facilitate the creation of the semester program. ', 'Νίκος Θεοφανούδης', 'Nikos Theofanoudis ', 'department', 'https://it185181.herokuapp.com');

INSERT INTO `useful_websites` (`title_gr`, `title_en`, `description_gr`, `description_en`, `category`, `link`) VALUES
('Φοιτητικά Νέα', 'Student News', 'Ενημέρωση για όλα τα φοιτητικά νέα.', 'Information for all student news. ', 'news', 'https://www.foititikanea.gr'),
('Eduroam', 'Eduroam', 'Δωρεάν Ίντερνετ στο Campus.', 'Free Internet on Campus. ', 'internet', 'https://www.eduroam.gr'),
('Photomath', 'Photomath', 'Μια mobile εφαρμογή για να λύνεις ασκήσεις με φωτογραφίες.', 'A mobile application to solve exercises with photos.', 'maths', 'https://photomath.com'),
('Symbolab', 'Symbolab', 'Μια ιστοσελίδα για να γράφεις και να λύνεις διάφορες ασκήσεις.', 'A website to write and solve various exercises. ', 'maths', 'https://www.symbolab.com');



INSERT INTO `courses` (`title_gr`, `title_en`, `semester`, `teachers_gr`, `teachers_en`, `link`) VALUES
('Μαθηματικά Ι', 'Mathematics Ι','1o','Αντωνίου Ευστάθιος, Τζέκης Παναγιώτης', 'Antoniou Efstathios, Tzekis Panagiotis', 'course/1101'),
('Δομημένος Προγραμματισμός', 'Structured Programming','1o','Γουλιάνας Κωνσταντίνος, Ουγιάρογλου Στέφανος, Ασδρέ Κατερίνα', 'Goulianas Konstantinos, Ougiaroglou Stefanos, Asdre Katerina','course/1102'),
('Εισαγωγή στην Επιστήμη των Υπολογιστών', 'Introduction to computer science', '1ο', 'Ηλιούδης Χρήστος', 'Ilioudis Christos', 'course/1103'),
('', '', '1o', '', '', ''),
('', '', '1o', '', '', ''),
('', '', '2o', '', '', ''),
('', '', '2o', '', '', ''),
('', '', '2o', '', '', ''),
('', '', '2o', '', '', ''),
('', '', '2o', '', '', ''),
('', '', '3o', '', '', ''),
('', '', '3o', '', '', ''),
('', '', '3o', '', '', ''),
('', '', '3o', '', '', ''),
('', '', '3o', '', '', ''),
('', '', '4o', '', '', ''),
('', '', '4o', '', '', ''),
('', '', '4o', '', '', ''),
('', '', '4o', '', '', ''),
('', '', '4o', '', '', ''),
('', '', '5o', '', '', ''),
('', '', '5o', '', '', ''),
('', '', '5o', '', '', ''),
('', '', '5o', '', '', ''),
('', '', '5o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '6o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '7o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '8o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '9o', '', '', ''),
('', '', '10o', '', '', '');


INSERT INTO `teachers` (`name_gr`, `name_en`, `email`, `personal_site`, `category_gr`, `category_en`, `link`) VALUES
('Αδαμίδης Παναγιώτης', 'Adamidis Panagiotis', 'adamidis@it.teithe.gr', 'https://people.iee.ihu.gr/~adamidis/', 'Μέλη Δ.Ε.Π.', 'Faculty Members', 'https://www.iee.ihu.gr/staff/adamidis-panagiotis/'),
('Βίτσας Βασίλης', 'Vitsas Vasilis', 'vitsas@it.teithe.gr', 'https://www.it.teithe.gr/~vitsas', 'Μέλη Δ.Ε.Π.', 'Faculty Members', 'https://www.iee.ihu.gr/staff/vitsas-vasilis/');

