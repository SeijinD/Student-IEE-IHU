package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.model.Course
import kotlinx.android.synthetic.main.fragment_semester.view.*

class SemesterFragment : Fragment(), AdapterView.OnItemClickListener {

    private val courseList = ArrayList<Course>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_semester, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        courseList.clear()

        when(SemesterFragmentArgs.fromBundle(requireArguments()).semesterNumber){
            "1" -> {
                toolbar.title = "First Semester"

                courseList.add(Course("Μαθηματικά Ι", "https://www.iee.ihu.gr/course/1101"))
                courseList.add(Course("Δομημένος Προγραμματισμός", "https://www.iee.ihu.gr/course/1102"))
                courseList.add(Course("Εισαγωγή στην Επιστήμη των Υπολογιστών", "https://www.iee.ihu.gr/course/1103"))
                courseList.add(Course("Ηλεκτρονική Φυσική", "https://www.iee.ihu.gr/course/1104"))
                courseList.add(Course("Κυκλώματα Συνεχούς Ρεύματος", "https://www.iee.ihu.gr/course/1105"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "2" -> {
                toolbar.title = "Second Semester"

                courseList.add(Course("Μαθηματικά ΙΙ", "https://www.iee.ihu.gr/course/1201"))
                courseList.add(Course("Μετρήσεις και Κυκλώματα Εναλλασσόμενου Ρεύματος", "https://www.iee.ihu.gr/course/1202"))
                courseList.add(Course("Τεχνική Συγγραφή, Παρουσίαση και Ορολογία Ξένης Γλώσσας", "https://www.iee.ihu.gr/course/1203"))
                courseList.add(Course("Σχεδίαση Ψηφιακών Συστημάτων", "https://www.iee.ihu.gr/course/1204"))
                courseList.add(Course("Αντικειμενοστρεφής Προγραμματισμός", "https://www.iee.ihu.gr/course/1205"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "3" -> {
                toolbar.title = "Third Semester"

                courseList.add(Course("Θεωρία Πιθανοτήτων και Στατιστική", "https://www.iee.ihu.gr/course/1301"))
                courseList.add(Course("Μαθηματικά ΙΙI", "https://www.iee.ihu.gr/course/1302"))
                courseList.add(Course("Επεξεργασία Σήματος", "https://www.iee.ihu.gr/course/1303"))
                courseList.add(Course("Γλώσσες και Τεχνολογίες Ιστού", "https://www.iee.ihu.gr/course/1405"))
                courseList.add(Course("Δομές Δεδομένων και Ανάλυση Αλγορίθμων", "https://www.iee.ihu.gr/course/1305"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "4" -> {
                toolbar.title = "Fourth Semester"

                courseList.add(Course("Οργάνωση και Αρχιτεκτονική Υπολογιστικών Συστημάτων\t", "https://www.iee.ihu.gr/course/1304"))
                courseList.add(Course("Συστήματα Διαχείρισης Βάσεων Δεδομένων", "https://www.iee.ihu.gr/course/1401"))
                courseList.add(Course("Τηλεπικοινωνιακά Συστήματα", "https://www.iee.ihu.gr/course/1402"))
                courseList.add(Course("Εισαγωγή στα Λειτουργικά Συστήματα", "https://www.iee.ihu.gr/course/1403"))
                courseList.add(Course("Ηλεκτρονικά Κυκλώματα", "https://www.iee.ihu.gr/course/1404"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "5" -> {
                toolbar.title = "Fifth Semester"

                courseList.add(Course("Ασύρματες Επικοινωνίες", "https://www.iee.ihu.gr/course/1501"))
                courseList.add(Course("Μικροελεγκτές", "https://www.iee.ihu.gr/course/1502"))
                courseList.add(Course("Σχεδίαση Λειτουργικών Συστημάτων", "https://www.iee.ihu.gr/course/1503"))
                courseList.add(Course("Ηλεκτρονικές Διατάξεις", "https://www.iee.ihu.gr/course/1504"))
                courseList.add(Course("Αλληλεπίδραση Ανθρώπου-Μηχανής", "https://www.iee.ihu.gr/course/1505"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "6" -> {
                toolbar.title = "Sixth Semester"

                courseList.add(Course("Τεχνητή Νοημοσύνη", "https://www.iee.ihu.gr/course/1601"))
                courseList.add(Course("Ενσωματωμένα Συστήματα", "https://www.iee.ihu.gr/course/1602"))
                courseList.add(Course("Σύνθεση Ηλεκτρονικών Κυκλωμάτων", "https://www.iee.ihu.gr/course/1611"))
                courseList.add(Course("Κβαντική Υπολογιστική", "https://www.iee.ihu.gr/course/1612"))
                courseList.add(Course("Μεθοδολογίες Σχεδιασμού Μικροηλεκτρονικών Κυκλωμάτων **", "https://www.iee.ihu.gr/course/1613"))
                courseList.add(Course("Μικροκυματική Τεχνολογία και Τηλεπισκόπηση", "https://www.iee.ihu.gr/course/1671"))
                courseList.add(Course("Οπτοηλεκτρονική και Οπτικές Επικοινωνίες", "https://www.iee.ihu.gr/course/1672"))
                courseList.add(Course("Συστήματα Μέσων Μαζικής Επικοινωνίας", "https://www.iee.ihu.gr/course/1673"))
                courseList.add(Course("Αριθμητικές Μέθοδοι", "https://www.iee.ihu.gr/course/1641"))
                courseList.add(Course("Προηγμένα Θέματα Αλληλεπίδρασης (Προγραμματισμός Κινητών Συσκευών)", "https://www.iee.ihu.gr/course/1642"))
                courseList.add(Course("Διοίκηση Έργων", "https://www.iee.ihu.gr/course/1643"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "7" -> {
                toolbar.title = "Seventh Semester"

                courseList.add(Course("Δίκτυα Υπολογιστών", "https://www.iee.ihu.gr/course/1701"))
                courseList.add(Course("Ηλεκτρονικά Ισχύος", "https://www.iee.ihu.gr/course/1702"))
                courseList.add(Course("Συστήματα Αυτομάτου Ελέγχου", "https://www.iee.ihu.gr/course/1711"))
                courseList.add(Course("Αισθητήρια και Επεξεργασία Μετρήσεων", "https://www.iee.ihu.gr/course/1712"))
                courseList.add(Course("Προγραμματιζόμενοι Λογικοί Ελεγκτές", "https://www.iee.ihu.gr/course/1713"))
                courseList.add(Course("Σχεδίαση Επαναπροσδιοριζόμενων Ψηφιακών Συστημάτων (FPGA) **", "https://www.iee.ihu.gr/course/1714"))
                courseList.add(Course("Τεχνολογίες Ήχου και Εικόνας", "https://www.iee.ihu.gr/course/1771"))
                courseList.add(Course("Εισαγωγή στην Αναλυτική των Δεδομένων", "https://www.iee.ihu.gr/course/1741"))
                courseList.add(Course("Μηχανική Λογισμικού", "https://www.iee.ihu.gr/course/1742"))
                courseList.add(Course("Τεχνολογία Βάσεων Δεδομένων", "https://www.iee.ihu.gr/course/1743"))
                courseList.add(Course("Προηγμένες Αρχιτεκτονικές Υπολογιστών και Προγραμματισμός Παράλληλων Συστημάτων", "https://www.iee.ihu.gr/course/1744"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "8" -> {
                toolbar.title = "Eighth Semester"

                courseList.add(Course("Ασφάλεια Πληροφοριακών Συστημάτων", "https://www.iee.ihu.gr/course/1801"))
                courseList.add(Course("Αρχές και Μέθοδοι Μηχανικής Μάθησης", "https://www.iee.ihu.gr/course/1802"))
                courseList.add(Course("Διαδίκτυο των Πραγμάτων", "https://www.iee.ihu.gr/course/1803"))
                courseList.add(Course("Εφαρμογές Συστημάτων Αυτομάτου Ελέγχου", "https://www.iee.ihu.gr/course/1811"))
                courseList.add(Course("Μετατροπείς Ισχύος", "https://www.iee.ihu.gr/course/1812"))
                courseList.add(Course("Μικροηλεκτρονική *", "https://www.iee.ihu.gr/course/1837"))
                courseList.add(Course("Εφαρμογές Συστημάτων Ισχύος και ΑΠΕ *", "https://www.iee.ihu.gr/course/1838"))
                courseList.add(Course("Ηλεκτροκίνηση και Ευφυή Δίκτυα *", "https://www.iee.ihu.gr/course/1839"))
                courseList.add(Course("Ασύρματα Δίκτυα", "https://www.iee.ihu.gr/course/1871"))
                courseList.add(Course("Ειδικά Θέματα Δικτύων (CCNA) 1", "https://www.iee.ihu.gr/course/1872"))
                courseList.add(Course("Προηγμένα Θέματα Δικτύων", "https://www.iee.ihu.gr/course/1873"))
                courseList.add(Course("Συστήματα Κινητών Επικοινωνιών", "https://www.iee.ihu.gr/course/1874"))
                courseList.add(Course("Ελεύθερη Επιλογή Β", "https://www.iee.ihu.gr/course/1898"))
                courseList.add(Course("Ραδιοτηλεοπτική Παραγωγή *", "https://www.iee.ihu.gr/course/1899"))
                courseList.add(Course("Οργάνωση Δεδομένων και Εξόρυξη Πληροφορίας", "https://www.iee.ihu.gr/course/1841"))
                courseList.add(Course("Διαδικτυακές Υπηρεσίες Προστιθέμενης Αξίας", "https://www.iee.ihu.gr/course/1842"))
                courseList.add(Course("Ανάπτυξη Ολοκληρωμένων Πληροφοριακών Συστημάτων", "https://www.iee.ihu.gr/course/1848"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "9" -> {
                toolbar.title = "Ninth Semester"

                courseList.add(Course("Πρακτική Άσκηση", "https://www.iee.ihu.gr/course/1970"))
                courseList.add(Course("Εφαρμογές Ενσωματωμένων Συστημάτων", "https://www.iee.ihu.gr/course/1911"))
                courseList.add(Course("Ρομποτική", "https://www.iee.ihu.gr/course/1912"))
                courseList.add(Course("ΑΠΕ και Ευφυή Ηλεκτρικά Δίκτυα", "https://www.iee.ihu.gr/course/1913"))
                courseList.add(Course("Απτικές Διεπαφές", "https://www.iee.ihu.gr/course/1914"))
                courseList.add(Course("Βιοϊατρική Τεχνολογία", "https://www.iee.ihu.gr/course/1915"))
                courseList.add(Course("Συστήματα Μετρήσεων Υποβοηθούμενων από Η/Υ **", "https://www.iee.ihu.gr/course/1916"))
                courseList.add(Course("Ασφάλεια Δικτύων και Επικοινωνιών **", "https://www.iee.ihu.gr/course/1971"))
                courseList.add(Course("Δικτύωση Καθορισμένη από Λογισμικό", "https://www.iee.ihu.gr/course/1972"))
                courseList.add(Course("Ειδικά Θέματα Δικτύων (CCNA) 2", "https://www.iee.ihu.gr/course/1973"))
                courseList.add(Course("Δορυφορικές Επικοινωνίες", "https://www.iee.ihu.gr/course/1974"))
                courseList.add(Course("Τεχνολογία Πολυμέσων", "https://www.iee.ihu.gr/course/1975"))
                courseList.add(Course("Ελεύθερη Επιλογή Α", "https://www.iee.ihu.gr/course/1998"))
                courseList.add(Course("Ανάπτυξη Διαδικτυακών Συστημάτων και Εφαρμογών", "https://www.iee.ihu.gr/course/1941"))
                courseList.add(Course("Επιχειρησιακή Έρευνα", "https://www.iee.ihu.gr/course/1942"))
                courseList.add(Course("Ανάκτηση Πληροφοριών – Μηχανές Αναζήτησης", "https://www.iee.ihu.gr/course/1943"))
                courseList.add(Course("Διαχείριση Συστήματος και Υπηρεσιών DBMS", "https://www.iee.ihu.gr/course/1944"))
                courseList.add(Course("Ευφυή Συστήματα", "https://www.iee.ihu.gr/course/1945"))
                courseList.add(Course("Προηγμένα Θέματα Τεχνητής Νοημοσύνης **", "https://www.iee.ihu.gr/course/1946"))
                courseList.add(Course("Προηγμένη Μηχανική Μάθηση", "https://www.iee.ihu.gr/course/1947"))
                courseList.add(Course("Κατανεμημένα Συστήματα", "https://www.iee.ihu.gr/course/1949"))
                courseList.add(Course("Σημασιολογικός Ιστός", "https://www.iee.ihu.gr/course/1950"))
                courseList.add(Course("Γραφικά Υπολογιστών", "https://www.iee.ihu.gr/course/1969"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "10" -> {
                toolbar.title = "Tenth Semester"

                courseList.add(Course("Διπλωματική Εργασία", "https://www.iee.ihu.gr/course/1999"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
        }
        return view
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val course: Course = courseList[position]
        Navigation.findNavController(requireView()).navigate(SemesterFragmentDirections.actionMenuSemesterToCourseFragment(course.link!!))
    }


}