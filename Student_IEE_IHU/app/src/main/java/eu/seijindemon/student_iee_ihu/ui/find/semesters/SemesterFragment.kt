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
                toolbar.title = "1st Semester"

                courseList.add(Course("Μαθηματικά Ι", "course/1101"))
                courseList.add(Course("Δομημένος Προγραμματισμός", "course/1102"))
                courseList.add(Course("Εισαγωγή στην Επιστήμη των Υπολογιστών", "course/1103"))
                courseList.add(Course("Ηλεκτρονική Φυσική", "course/1104"))
                courseList.add(Course("Κυκλώματα Συνεχούς Ρεύματος", "course/1105"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "2" -> {
                toolbar.title = "2nd Semester"

                courseList.add(Course("Μαθηματικά ΙΙ", "course/1201"))
                courseList.add(Course("Μετρήσεις και Κυκλώματα Εναλλασσόμενου Ρεύματος", "course/1202"))
                courseList.add(Course("Τεχνική Συγγραφή, Παρουσίαση και Ορολογία Ξένης Γλώσσας", "course/1203"))
                courseList.add(Course("Σχεδίαση Ψηφιακών Συστημάτων", "course/1204"))
                courseList.add(Course("Αντικειμενοστρεφής Προγραμματισμός", "course/1205"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "3" -> {
                toolbar.title = "3rd Semester"

                courseList.add(Course("Θεωρία Πιθανοτήτων και Στατιστική", "course/1301"))
                courseList.add(Course("Μαθηματικά ΙΙI", "course/1302"))
                courseList.add(Course("Επεξεργασία Σήματος", "course/1303"))
                courseList.add(Course("Γλώσσες και Τεχνολογίες Ιστού", "course/1405"))
                courseList.add(Course("Δομές Δεδομένων και Ανάλυση Αλγορίθμων", "course/1305"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "4" -> {
                toolbar.title = "4th Semester"

                courseList.add(Course("Οργάνωση και Αρχιτεκτονική Υπολογιστικών Συστημάτων\t", "course/1304"))
                courseList.add(Course("Συστήματα Διαχείρισης Βάσεων Δεδομένων", "course/1401"))
                courseList.add(Course("Τηλεπικοινωνιακά Συστήματα", "course/1402"))
                courseList.add(Course("Εισαγωγή στα Λειτουργικά Συστήματα", "course/1403"))
                courseList.add(Course("Ηλεκτρονικά Κυκλώματα", "course/1404"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "5" -> {
                toolbar.title = "5th Semester"

                courseList.add(Course("Ασύρματες Επικοινωνίες", "course/1501"))
                courseList.add(Course("Μικροελεγκτές", "course/1502"))
                courseList.add(Course("Σχεδίαση Λειτουργικών Συστημάτων", "course/1503"))
                courseList.add(Course("Ηλεκτρονικές Διατάξεις", "course/1504"))
                courseList.add(Course("Αλληλεπίδραση Ανθρώπου-Μηχανής", "course/1505"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "6" -> {
                toolbar.title = "6th Semester"

                courseList.add(Course("Τεχνητή Νοημοσύνη", "course/1601"))
                courseList.add(Course("Ενσωματωμένα Συστήματα", "course/1602"))
                courseList.add(Course("Σύνθεση Ηλεκτρονικών Κυκλωμάτων", "course/1611"))
                courseList.add(Course("Κβαντική Υπολογιστική", "course/1612"))
                courseList.add(Course("Μεθοδολογίες Σχεδιασμού Μικροηλεκτρονικών Κυκλωμάτων **", "course/1613"))
                courseList.add(Course("Μικροκυματική Τεχνολογία και Τηλεπισκόπηση", "course/1671"))
                courseList.add(Course("Οπτοηλεκτρονική και Οπτικές Επικοινωνίες", "course/1672"))
                courseList.add(Course("Συστήματα Μέσων Μαζικής Επικοινωνίας", "course/1673"))
                courseList.add(Course("Αριθμητικές Μέθοδοι", "course/1641"))
                courseList.add(Course("Προηγμένα Θέματα Αλληλεπίδρασης (Προγραμματισμός Κινητών Συσκευών)", "course/1642"))
                courseList.add(Course("Διοίκηση Έργων", "course/1643"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "7" -> {
                toolbar.title = "7th Semester"

                courseList.add(Course("Δίκτυα Υπολογιστών", "course/1701"))
                courseList.add(Course("Ηλεκτρονικά Ισχύος", "course/1702"))
                courseList.add(Course("Συστήματα Αυτομάτου Ελέγχου", "course/1711"))
                courseList.add(Course("Αισθητήρια και Επεξεργασία Μετρήσεων", "course/1712"))
                courseList.add(Course("Προγραμματιζόμενοι Λογικοί Ελεγκτές", "course/1713"))
                courseList.add(Course("Σχεδίαση Επαναπροσδιοριζόμενων Ψηφιακών Συστημάτων (FPGA) **", "course/1714"))
                courseList.add(Course("Τεχνολογίες Ήχου και Εικόνας", "course/1771"))
                courseList.add(Course("Εισαγωγή στην Αναλυτική των Δεδομένων", "course/1741"))
                courseList.add(Course("Μηχανική Λογισμικού", "course/1742"))
                courseList.add(Course("Τεχνολογία Βάσεων Δεδομένων", "course/1743"))
                courseList.add(Course("Προηγμένες Αρχιτεκτονικές Υπολογιστών και Προγραμματισμός Παράλληλων Συστημάτων", "course/1744"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "8" -> {
                toolbar.title = "8th Semester"

                courseList.add(Course("Ασφάλεια Πληροφοριακών Συστημάτων", "course/1801"))
                courseList.add(Course("Αρχές και Μέθοδοι Μηχανικής Μάθησης", "course/1802"))
                courseList.add(Course("Διαδίκτυο των Πραγμάτων", "course/1803"))
                courseList.add(Course("Εφαρμογές Συστημάτων Αυτομάτου Ελέγχου", "course/1811"))
                courseList.add(Course("Μετατροπείς Ισχύος", "course/1812"))
                courseList.add(Course("Μικροηλεκτρονική *", "course/1837"))
                courseList.add(Course("Εφαρμογές Συστημάτων Ισχύος και ΑΠΕ *", "course/1838"))
                courseList.add(Course("Ηλεκτροκίνηση και Ευφυή Δίκτυα *", "course/1839"))
                courseList.add(Course("Ασύρματα Δίκτυα", "course/1871"))
                courseList.add(Course("Ειδικά Θέματα Δικτύων (CCNA) 1", "course/1872"))
                courseList.add(Course("Προηγμένα Θέματα Δικτύων", "course/1873"))
                courseList.add(Course("Συστήματα Κινητών Επικοινωνιών", "course/1874"))
                courseList.add(Course("Ελεύθερη Επιλογή Β", "course/1898"))
                courseList.add(Course("Ραδιοτηλεοπτική Παραγωγή *", "course/1899"))
                courseList.add(Course("Οργάνωση Δεδομένων και Εξόρυξη Πληροφορίας", "course/1841"))
                courseList.add(Course("Διαδικτυακές Υπηρεσίες Προστιθέμενης Αξίας", "course/1842"))
                courseList.add(Course("Ανάπτυξη Ολοκληρωμένων Πληροφοριακών Συστημάτων", "course/1848"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "9" -> {
                toolbar.title = "9th Semester"

                courseList.add(Course("Πρακτική Άσκηση", "course/1970"))
                courseList.add(Course("Εφαρμογές Ενσωματωμένων Συστημάτων", "course/1911"))
                courseList.add(Course("Ρομποτική", "course/1912"))
                courseList.add(Course("ΑΠΕ και Ευφυή Ηλεκτρικά Δίκτυα", "course/1913"))
                courseList.add(Course("Απτικές Διεπαφές", "course/1914"))
                courseList.add(Course("Βιοϊατρική Τεχνολογία", "course/1915"))
                courseList.add(Course("Συστήματα Μετρήσεων Υποβοηθούμενων από Η/Υ **", "course/1916"))
                courseList.add(Course("Ασφάλεια Δικτύων και Επικοινωνιών **", "course/1971"))
                courseList.add(Course("Δικτύωση Καθορισμένη από Λογισμικό", "course/1972"))
                courseList.add(Course("Ειδικά Θέματα Δικτύων (CCNA) 2", "course/1973"))
                courseList.add(Course("Δορυφορικές Επικοινωνίες", "course/1974"))
                courseList.add(Course("Τεχνολογία Πολυμέσων", "course/1975"))
                courseList.add(Course("Ελεύθερη Επιλογή Α", "course/1998"))
                courseList.add(Course("Ανάπτυξη Διαδικτυακών Συστημάτων και Εφαρμογών", "course/1941"))
                courseList.add(Course("Επιχειρησιακή Έρευνα", "course/1942"))
                courseList.add(Course("Ανάκτηση Πληροφοριών – Μηχανές Αναζήτησης", "course/1943"))
                courseList.add(Course("Διαχείριση Συστήματος και Υπηρεσιών DBMS", "course/1944"))
                courseList.add(Course("Ευφυή Συστήματα", "course/1945"))
                courseList.add(Course("Προηγμένα Θέματα Τεχνητής Νοημοσύνης **", "course/1946"))
                courseList.add(Course("Προηγμένη Μηχανική Μάθηση", "course/1947"))
                courseList.add(Course("Κατανεμημένα Συστήματα", "course/1949"))
                courseList.add(Course("Σημασιολογικός Ιστός", "course/1950"))
                courseList.add(Course("Γραφικά Υπολογιστών", "course/1969"))

                val adapter = CourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "10" -> {
                toolbar.title = "10th Semester"

                courseList.add(Course("Διπλωματική Εργασία", "course/1999"))

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