package eu.seijindemon.student_iee_ihu.ui.find.semesters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.refactor.framework.model.SimpleCourse
import kotlinx.android.synthetic.main.fragment_semester.view.*

@AndroidEntryPoint
class SemesterFragment : Fragment(), AdapterView.OnItemClickListener {

    private val courseList = ArrayList<SimpleCourse>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_semester, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        courseList.clear()

        when(SemesterFragmentArgs.fromBundle(requireArguments()).semesterNumber){
            "1" -> {
                toolbar.title = getString(R.string.first_semester)

                courseList.add(SimpleCourse("Μαθηματικά Ι", "course/1101"))
                courseList.add(SimpleCourse("Δομημένος Προγραμματισμός", "course/1102"))
                courseList.add(SimpleCourse("Εισαγωγή στην Επιστήμη των Υπολογιστών", "course/1103"))
                courseList.add(SimpleCourse("Ηλεκτρονική Φυσική", "course/1104"))
                courseList.add(SimpleCourse("Κυκλώματα Συνεχούς Ρεύματος", "course/1105"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "2" -> {
                toolbar.title = getString(R.string.second_semester)

                courseList.add(SimpleCourse("Μαθηματικά ΙΙ", "course/1201"))
                courseList.add(SimpleCourse("Μετρήσεις και Κυκλώματα Εναλλασσόμενου Ρεύματος", "course/1202"))
                courseList.add(SimpleCourse("Τεχνική Συγγραφή, Παρουσίαση και Ορολογία Ξένης Γλώσσας", "course/1203"))
                courseList.add(SimpleCourse("Σχεδίαση Ψηφιακών Συστημάτων", "course/1204"))
                courseList.add(SimpleCourse("Αντικειμενοστρεφής Προγραμματισμός", "course/1205"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "3" -> {
                toolbar.title = getString(R.string.third_semester)

                courseList.add(SimpleCourse("Θεωρία Πιθανοτήτων και Στατιστική", "course/1301"))
                courseList.add(SimpleCourse("Μαθηματικά ΙΙΙ", "course/1302"))
                courseList.add(SimpleCourse("Επεξεργασία Σήματος", "course/1303"))
                courseList.add(SimpleCourse("Γλώσσες και Τεχνολογίες Ιστού", "course/1405"))
                courseList.add(SimpleCourse("Δομές Δεδομένων και Ανάλυση Αλγορίθμων", "course/1305"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "4" -> {
                toolbar.title = getString(R.string.fourth_semester)

                courseList.add(SimpleCourse("Οργάνωση και Αρχιτεκτονική Υπολογιστικών Συστημάτων", "course/1304"))
                courseList.add(SimpleCourse("Συστήματα Διαχείρισης Βάσεων Δεδομένων", "course/1401"))
                courseList.add(SimpleCourse("Τηλεπικοινωνιακά Συστήματα", "course/1402"))
                courseList.add(SimpleCourse("Εισαγωγή στα Λειτουργικά Συστήματα", "course/1403"))
                courseList.add(SimpleCourse("Ηλεκτρονικά Κυκλώματα", "course/1404"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "5" -> {
                toolbar.title = getString(R.string.fifth_semester)

                courseList.add(SimpleCourse("Ασύρματες Επικοινωνίες", "course/1501"))
                courseList.add(SimpleCourse("Μικροελεγκτές", "course/1502"))
                courseList.add(SimpleCourse("Σχεδίαση Λειτουργικών Συστημάτων", "course/1503"))
                courseList.add(SimpleCourse("Ηλεκτρονικές Διατάξεις", "course/1504"))
                courseList.add(SimpleCourse("Αλληλεπίδραση Ανθρώπου-Μηχανής", "course/1505"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "6" -> {
                toolbar.title = getString(R.string.sixth_semester)

                courseList.add(SimpleCourse("Τεχνητή Νοημοσύνη", "course/1601"))
                courseList.add(SimpleCourse("Ενσωματωμένα Συστήματα", "course/1602"))
                courseList.add(SimpleCourse("Σύνθεση Ηλεκτρονικών Κυκλωμάτων", "course/1611"))
                courseList.add(SimpleCourse("Κβαντική Υπολογιστική", "course/1612"))
                courseList.add(SimpleCourse("Μεθοδολογίες Σχεδιασμού Μικροηλεκτρονικών Κυκλωμάτων **", "course/1613"))
                courseList.add(SimpleCourse("Μικροκυματική Τεχνολογία και Τηλεπισκόπηση", "course/1671"))
                courseList.add(SimpleCourse("Οπτοηλεκτρονική και Οπτικές Επικοινωνίες", "course/1672"))
                courseList.add(SimpleCourse("Συστήματα Μέσων Μαζικής Επικοινωνίας", "course/1673"))
                courseList.add(SimpleCourse("Αριθμητικές Μέθοδοι", "course/1641"))
                courseList.add(SimpleCourse("Προηγμένα Θέματα Αλληλεπίδρασης (Προγραμματισμός Κινητών Συσκευών)", "course/1642"))
                courseList.add(SimpleCourse("Διοίκηση Έργων", "course/1643"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "7" -> {
                toolbar.title = getString(R.string.seventh_semester)

                courseList.add(SimpleCourse("Δίκτυα Υπολογιστών", "course/1701"))
                courseList.add(SimpleCourse("Ηλεκτρονικά Ισχύος", "course/1702"))
                courseList.add(SimpleCourse("Συστήματα Αυτομάτου Ελέγχου", "course/1711"))
                courseList.add(SimpleCourse("Αισθητήρια και Επεξεργασία Μετρήσεων", "course/1712"))
                courseList.add(SimpleCourse("Προγραμματιζόμενοι Λογικοί Ελεγκτές", "course/1713"))
                courseList.add(SimpleCourse("Σχεδίαση Επαναπροσδιοριζόμενων Ψηφιακών Συστημάτων (FPGA) **", "course/1714"))
                courseList.add(SimpleCourse("Τεχνολογίες Ήχου και Εικόνας", "course/1771"))
                courseList.add(SimpleCourse("Εισαγωγή στην Αναλυτική των Δεδομένων", "course/1741"))
                courseList.add(SimpleCourse("Μηχανική Λογισμικού", "course/1742"))
                courseList.add(SimpleCourse("Τεχνολογία Βάσεων Δεδομένων", "course/1743"))
                courseList.add(SimpleCourse("Προηγμένες Αρχιτεκτονικές Υπολογιστών και Προγραμματισμός Παράλληλων Συστημάτων", "course/1744"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "8" -> {
                toolbar.title = getString(R.string.eighth_semester)

                courseList.add(SimpleCourse("Ασφάλεια Πληροφοριακών Συστημάτων", "course/1801"))
                courseList.add(SimpleCourse("Αρχές και Μέθοδοι Μηχανικής Μάθησης", "course/1802"))
                courseList.add(SimpleCourse("Διαδίκτυο των Πραγμάτων", "course/1803"))
                courseList.add(SimpleCourse("Εφαρμογές Συστημάτων Αυτομάτου Ελέγχου", "course/1811"))
                courseList.add(SimpleCourse("Μετατροπείς Ισχύος", "course/1812"))
                courseList.add(SimpleCourse("Μικροηλεκτρονική *", "course/1837"))
                courseList.add(SimpleCourse("Εφαρμογές Συστημάτων Ισχύος και ΑΠΕ *", "course/1838"))
                courseList.add(SimpleCourse("Ηλεκτροκίνηση και Ευφυή Δίκτυα *", "course/1839"))
                courseList.add(SimpleCourse("Ασύρματα Δίκτυα", "course/1871"))
                courseList.add(SimpleCourse("Ειδικά Θέματα Δικτύων (CCNA) 1", "course/1872"))
                courseList.add(SimpleCourse("Προηγμένα Θέματα Δικτύων", "course/1873"))
                courseList.add(SimpleCourse("Συστήματα Κινητών Επικοινωνιών", "course/1874"))
                courseList.add(SimpleCourse("Ελεύθερη Επιλογή Β", "course/1898"))
                courseList.add(SimpleCourse("Ραδιοτηλεοπτική Παραγωγή *", "course/1899"))
                courseList.add(SimpleCourse("Οργάνωση Δεδομένων και Εξόρυξη Πληροφορίας", "course/1841"))
                courseList.add(SimpleCourse("Διαδικτυακές Υπηρεσίες Προστιθέμενης Αξίας", "course/1842"))
                courseList.add(SimpleCourse("Ανάπτυξη Ολοκληρωμένων Πληροφοριακών Συστημάτων", "course/1848"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "9" -> {
                toolbar.title = getString(R.string.ninth_semester)

                courseList.add(SimpleCourse("Πρακτική Άσκηση", "course/1970"))
                courseList.add(SimpleCourse("Εφαρμογές Ενσωματωμένων Συστημάτων", "course/1911"))
                courseList.add(SimpleCourse("Ρομποτική", "course/1912"))
                courseList.add(SimpleCourse("ΑΠΕ και Ευφυή Ηλεκτρικά Δίκτυα", "course/1913"))
                courseList.add(SimpleCourse("Απτικές Διεπαφές", "course/1914"))
                courseList.add(SimpleCourse("Βιοϊατρική Τεχνολογία", "course/1915"))
                courseList.add(SimpleCourse("Συστήματα Μετρήσεων Υποβοηθούμενων από Η/Υ **", "course/1916"))
                courseList.add(SimpleCourse("Ασφάλεια Δικτύων και Επικοινωνιών **", "course/1971"))
                courseList.add(SimpleCourse("Δικτύωση Καθορισμένη από Λογισμικό", "course/1972"))
                courseList.add(SimpleCourse("Ειδικά Θέματα Δικτύων (CCNA) 2", "course/1973"))
                courseList.add(SimpleCourse("Δορυφορικές Επικοινωνίες", "course/1974"))
                courseList.add(SimpleCourse("Τεχνολογία Πολυμέσων", "course/1975"))
                courseList.add(SimpleCourse("Ελεύθερη Επιλογή Α", "course/1998"))
                courseList.add(SimpleCourse("Ανάπτυξη Διαδικτυακών Συστημάτων και Εφαρμογών", "course/1941"))
                courseList.add(SimpleCourse("Επιχειρησιακή Έρευνα", "course/1942"))
                courseList.add(SimpleCourse("Ανάκτηση Πληροφοριών – Μηχανές Αναζήτησης", "course/1943"))
                courseList.add(SimpleCourse("Διαχείριση Συστήματος και Υπηρεσιών DBMS", "course/1944"))
                courseList.add(SimpleCourse("Ευφυή Συστήματα", "course/1945"))
                courseList.add(SimpleCourse("Προηγμένα Θέματα Τεχνητής Νοημοσύνης **", "course/1946"))
                courseList.add(SimpleCourse("Προηγμένη Μηχανική Μάθηση", "course/1947"))
                courseList.add(SimpleCourse("Κατανεμημένα Συστήματα", "course/1949"))
                courseList.add(SimpleCourse("Σημασιολογικός Ιστός", "course/1950"))
                courseList.add(SimpleCourse("Γραφικά Υπολογιστών", "course/1969"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
            "10" -> {
                toolbar.title = getString(R.string.tenth_semester)

                courseList.add(SimpleCourse("Διπλωματική Εργασία", "course/1999"))

                val adapter = SimpleCourseAdapter(requireContext(), courseList)
                view.course_list.adapter = adapter
                view.course_list.onItemClickListener = this
            }
        }
        return view
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val course: SimpleCourse = courseList[position]
        Navigation.findNavController(requireView()).navigate(SemesterFragmentDirections.actionMenuSemesterToSimpleCourseFragment(course.link!!))
    }


}