INSERT INTO `courses` (`title`, `semester`, `teachers`, `link`) VALUES
('Μαθηματικά Ι','1o','Αντωνίου Ευστάθιος, Τζέκης Παναγιώτης','https://www.iee.ihu.gr/course/1101/'),
('Δομημένος Προγραμματισμός','1o','Γουλιάνας Κωνσταντίνος, Ουγιάρογλου Στέφανος, Ασδρέ Κατερίνα','https://www.iee.ihu.gr/course/1102/'),
('Εισαγωγή στην Επιστήμη των Υπολογιστών', '1ο', 'Ηλιούδης Χρήστος', 'https://www.iee.ihu.gr/course/1103/');

INSERT INTO `teachers` (`name`, `email`, `personal_site`, `category`, `link`) VALUES
('Αδαμίδης Παναγιώτης', 'adamidis@it.teithe.gr', 'https://people.iee.ihu.gr/~adamidis/', 'Μέλη Δ.Ε.Π.', 'https://www.iee.ihu.gr/staff/adamidis-panagiotis/'),
('Βίτσας Βασίλης', 'vitsas@it.teithe.gr', 'https://www.it.teithe.gr/~vitsas', 'Μέλη Δ.Ε.Π.', 'https://www.iee.ihu.gr/staff/vitsas-vasilis/');

INSERT INTO `communities` (`title`, `category`, `link`) VALUES
('Τμήμα Μηχανικών Πληροφορικής Και Ηλεκτρονικών Συστημάτων ΔΙΠΑΕ θεσ/νίκης', 'facebook_pages', 'https://www.facebook.com/MixanikonPliroforikisKaiIlektronikonSystimaton/'),
('Ενημέρωση Φοιτητών ΔΙΠΑΕ Σίνδου', 'facebook_pages', 'https://www.facebook.com/tei.thessalonikhs/'),
('ΔΙΠΑΕ Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων', 'discord_servers', 'https://discord.gg/9tUnEVMe'),
('ΤΜΗΜΑ ΠΛΗΡΟΦΟΡΙΚΗΣ Α.Τ.Ε.Ι.ΤΗ.', 'facebook_groups', 'https://www.facebook.com/groups/pliroforikarioi/'),
('ΔΙ.ΠΑ.Ε. - Τμήμα Μηχανικών Πληροφορικής, Υπολογιστών και Τηλεπικοινωνιών', 'facebook_groups', 'https://www.facebook.com/groups/pliroforikiserron/'),
('Περιβαλλοντικη Ομαδα ΔΙΠΑΕ Θεσσαλονικης', 'facebook_groups', 'https://www.facebook.com/groups/189405949034962'),
('ΔΙΠΑΕ(Σινδος)', 'facebook_groups', 'https://www.facebook.com/groups/1542744542667713');

INSERT INTO `maps` (`latitude`, `longitude`, `name`, `description`) VALUES
('40.85954', '22.80485', 'Salonika', 'This is a beautiful city!'),
('40.85754', '22.80785', 'Kavala', 'This is a beautiful place!');

INSERT INTO `offers` (`title`, `description`, `category`, `link`) VALUES
('Jetbrains', 'Μια μεγάλη συλλογή από προγράμματα για προγραμματιστές με ετήσια άδεια', 'softwares', 'https://www.jetbrains.com'),
('Kivuto', 'Μια μεγάλη συλλογή από προγράμματα διάφορων εταιριών με πρόσφορες.', 'softwares', 'https://e5.onthehub.com/WebStore/ProductsByMajorVersionList.aspx?ws=be0fe910-869b-e011-969d-0030487d8897'),
('Microsoft', 'Μια μεγάλη συλλογή από προγράμματα της Microsoft.', 'softwares', 'https://imselab-atei-thessaloniki.weebly.com/ms-azure-gr.html'),
('Github', 'Μια μεγάλη συλλογή από συνδρομές και δώρα σε διάφορα προγράμματα και υπηρεσίες.', 'softwares', 'https://education.github.com');


INSERT INTO `official_services` (`title`, `description`, `category`, `link`) VALUES
('Tμήμα Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων', 'Η ιστοσελίδα του τμήματος.', 'department', 'https://www.iee.ihu.gr'),
('Moodle', 'Ο διαδικτυακός χώρος φιλοξενήσεις μαθημάτων.', 'department', 'https://moodle.teithe.gr/course/index.php?categoryid=125'),
('Moodle Exams', 'Ο διαδικτυακός χώρος για εξετάσεις μαθημάτων.', 'department', 'https://exams-iee.the.ihu.gr'),
('Ηλεκτρονικό Σύστημα Διπλωματικών', 'Ανάθεση και προβολή διπλωματικών/πτυχιακών.', 'department', 'https://thesis.iee.ihu.gr'),
('Δηλώσεις Μαθημάτων', 'Δηλώσεις και προβολή μαθημάτων εξαμήνου.', 'department', 'http://aris.noc.teithe.gr/el/diloseis'),
('Youtube', 'Το Youtube του τμήματος.', 'department', 'https://www.youtube.com/channel/UCq-5LWrceZMl4b5ulXF4RAg'),
('Apps', 'Κύριος λόγος οι ανακοινώσεις του τμήματος.', 'department', 'https://apps.iee.ihu.gr'),
('Feeding', 'Σύστημα Αιτήσεων Δικαιούχων Δωρεάν Σίτισης', 'university', 'http://feeding.teithe.gr'),
('Pithia', 'Κέντρο έλεγχου μαθημάτων και βαθμολόγιων φοιτητή.', 'department', 'http://pithia.teithe.gr/unistudent'),
('ECTS 160', 'Έλεγχος για τον αν φοιτητής έχει συμπληρώσει τουλάχιστον 160/210 ΠΜ*', 'department', 'https://www.iee.ihu.gr/ects160'),
('Διεύθυνση Τεχνικών Υπηρεσιών', 'Αιτήσεις έλεγχου/επιδιόρθωσης εγκαταστάσεων και μεγάλες Αίθουσες.', 'university', 'http://dty.teithe.gr'),
('Webmail', 'Το Email του τμήματος.', 'department', 'https://webmail.teithe.gr'),
('Πρακτική Άσκηση', 'Η σελίδα για την πρακτική άσκηση των φοιτητών.', 'department', 'http://placements.it.teithe.gr'),
('Κέντρο Δικτύου Αιτήματα', 'Η σελίδα του κέντρου δεκτού για σχετικά αιτήματα με τους λογαριασμούς.', 'university', 'https://helpdesk.the.ihu.gr/index.php'),
('Κεντρική Βιβλιοθήκη Σχόλης', 'Μπορείς να αναζητήσεις και βιβλία για αν υπάρχουν.', 'university', 'https://www.lib.teithe.gr');

INSERT INTO `unofficial_services` (`title`, `description`, `creator`, `category`, `link`) VALUES
('Φτιάξε το πρόγραμμα του εξαμήνου σου', 'Μια προσπάθεια ενός φοιτητή για να διευκολύνει την δημιουργία του εξαμηνιαίου προγράμματος.', 'Νίκος Θεοφανούδης', 'department', 'https://it185181.herokuapp.com');

INSERT INTO `useful_websites` (`title`, `description`, `category`, `link`) VALUES
('Φοιτητικά Νέα', 'Ενημέρωση για όλα τα φοιτητικά νέα.', 'news', 'https://www.foititikanea.gr'),
('Eduroam', 'Δωρεάν Ίντερνετ στο Campus.', 'internet', 'https://www.eduroam.gr'),
('Photomath', 'Μια mobile εφαρμογή για να λύνεις ασκήσεις με φωτογραφίες.', 'maths', 'https://photomath.com'),
('Symbolab', 'Μια ιστοσελίδα για να γράφεις και να λύνεις διάφορες ασκήσεις.', 'maths', 'https://www.symbolab.com');


