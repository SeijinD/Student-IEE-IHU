package eu.seijindemon.student_iee_ihu.refactor.ui.find.semesters

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentSemesterBinding
import eu.seijindemon.student_iee_ihu.framework.model.SimpleCourse
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.refactor.ui.find.semesters.adapter.SimpleCourseAdapter
import kotlinx.android.synthetic.main.fragment_category_community.*

@AndroidEntryPoint
class SemesterFragment : BaseFragment<FragmentSemesterBinding>(), AdapterView.OnItemClickListener {

    override fun getViewBinding(): FragmentSemesterBinding {
        return FragmentSemesterBinding.inflate(layoutInflater)
    }

    private val list = ArrayList<SimpleCourse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }
    
    private fun setupViews() {
        with(binding) {
            list.clear()

            when(SemesterFragmentArgs.fromBundle(requireArguments()).semesterNumber) {
                "1" -> {
                    toolbar.root.title = getString(R.string.first_semester)

                    list.add(SimpleCourse("Μαθηματικά Ι", "course/1101"))
                    list.add(SimpleCourse("Δομημένος Προγραμματισμός", "course/1102"))
                    list.add(SimpleCourse("Εισαγωγή στην Επιστήμη των Υπολογιστών", "course/1103"))
                    list.add(SimpleCourse("Ηλεκτρονική Φυσική", "course/1104"))
                    list.add(SimpleCourse("Κυκλώματα Συνεχούς Ρεύματος", "course/1105"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "2" -> {
                    toolbar.root.title = getString(R.string.second_semester)

                    list.add(SimpleCourse("Μαθηματικά ΙΙ", "course/1201"))
                    list.add(
                        SimpleCourse(
                            "Μετρήσεις και Κυκλώματα Εναλλασσόμενου Ρεύματος",
                            "course/1202"
                        )
                    )
                    list.add(
                        SimpleCourse(
                            "Τεχνική Συγγραφή, Παρουσίαση και Ορολογία Ξένης Γλώσσας",
                            "course/1203"
                        )
                    )
                    list.add(SimpleCourse("Σχεδίαση Ψηφιακών Συστημάτων", "course/1204"))
                    list.add(SimpleCourse("Αντικειμενοστρεφής Προγραμματισμός", "course/1205"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "3" -> {
                    toolbar.root.title = getString(R.string.third_semester)

                    list.add(SimpleCourse("Θεωρία Πιθανοτήτων και Στατιστική", "course/1301"))
                    list.add(SimpleCourse("Μαθηματικά ΙΙΙ", "course/1302"))
                    list.add(SimpleCourse("Επεξεργασία Σήματος", "course/1303"))
                    list.add(SimpleCourse("Γλώσσες και Τεχνολογίες Ιστού", "course/1405"))
                    list.add(SimpleCourse("Δομές Δεδομένων και Ανάλυση Αλγορίθμων", "course/1305"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "4" -> {
                    toolbar.root.title = getString(R.string.fourth_semester)

                    list.add(
                        SimpleCourse(
                            "Οργάνωση και Αρχιτεκτονική Υπολογιστικών Συστημάτων",
                            "course/1304"
                        )
                    )
                    list.add(SimpleCourse("Συστήματα Διαχείρισης Βάσεων Δεδομένων", "course/1401"))
                    list.add(SimpleCourse("Τηλεπικοινωνιακά Συστήματα", "course/1402"))
                    list.add(SimpleCourse("Εισαγωγή στα Λειτουργικά Συστήματα", "course/1403"))
                    list.add(SimpleCourse("Ηλεκτρονικά Κυκλώματα", "course/1404"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "5" -> {
                    toolbar.root.title = getString(R.string.fifth_semester)

                    list.add(SimpleCourse("Ασύρματες Επικοινωνίες", "course/1501"))
                    list.add(SimpleCourse("Μικροελεγκτές", "course/1502"))
                    list.add(SimpleCourse("Σχεδίαση Λειτουργικών Συστημάτων", "course/1503"))
                    list.add(SimpleCourse("Ηλεκτρονικές Διατάξεις", "course/1504"))
                    list.add(SimpleCourse("Αλληλεπίδραση Ανθρώπου-Μηχανής", "course/1505"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "6" -> {
                    toolbar.root.title = getString(R.string.sixth_semester)

                    list.add(SimpleCourse("Τεχνητή Νοημοσύνη", "course/1601"))
                    list.add(SimpleCourse("Ενσωματωμένα Συστήματα", "course/1602"))
                    list.add(SimpleCourse("Σύνθεση Ηλεκτρονικών Κυκλωμάτων", "course/1611"))
                    list.add(SimpleCourse("Κβαντική Υπολογιστική", "course/1612"))
                    list.add(
                        SimpleCourse(
                            "Μεθοδολογίες Σχεδιασμού Μικροηλεκτρονικών Κυκλωμάτων **",
                            "course/1613"
                        )
                    )
                    list.add(
                        SimpleCourse(
                            "Μικροκυματική Τεχνολογία και Τηλεπισκόπηση",
                            "course/1671"
                        )
                    )
                    list.add(
                        SimpleCourse(
                            "Οπτοηλεκτρονική και Οπτικές Επικοινωνίες",
                            "course/1672"
                        )
                    )
                    list.add(SimpleCourse("Συστήματα Μέσων Μαζικής Επικοινωνίας", "course/1673"))
                    list.add(SimpleCourse("Αριθμητικές Μέθοδοι", "course/1641"))
                    list.add(
                        SimpleCourse(
                            "Προηγμένα Θέματα Αλληλεπίδρασης (Προγραμματισμός Κινητών Συσκευών)",
                            "course/1642"
                        )
                    )
                    list.add(SimpleCourse("Διοίκηση Έργων", "course/1643"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "7" -> {
                    toolbar.root.title = getString(R.string.seventh_semester)

                    list.add(SimpleCourse("Δίκτυα Υπολογιστών", "course/1701"))
                    list.add(SimpleCourse("Ηλεκτρονικά Ισχύος", "course/1702"))
                    list.add(SimpleCourse("Συστήματα Αυτομάτου Ελέγχου", "course/1711"))
                    list.add(SimpleCourse("Αισθητήρια και Επεξεργασία Μετρήσεων", "course/1712"))
                    list.add(SimpleCourse("Προγραμματιζόμενοι Λογικοί Ελεγκτές", "course/1713"))
                    list.add(
                        SimpleCourse(
                            "Σχεδίαση Επαναπροσδιοριζόμενων Ψηφιακών Συστημάτων (FPGA) **",
                            "course/1714"
                        )
                    )
                    list.add(SimpleCourse("Τεχνολογίες Ήχου και Εικόνας", "course/1771"))
                    list.add(SimpleCourse("Εισαγωγή στην Αναλυτική των Δεδομένων", "course/1741"))
                    list.add(SimpleCourse("Μηχανική Λογισμικού", "course/1742"))
                    list.add(SimpleCourse("Τεχνολογία Βάσεων Δεδομένων", "course/1743"))
                    list.add(
                        SimpleCourse(
                            "Προηγμένες Αρχιτεκτονικές Υπολογιστών και Προγραμματισμός Παράλληλων Συστημάτων",
                            "course/1744"
                        )
                    )

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "8" -> {
                    toolbar.root.title = getString(R.string.eighth_semester)

                    list.add(SimpleCourse("Ασφάλεια Πληροφοριακών Συστημάτων", "course/1801"))
                    list.add(SimpleCourse("Αρχές και Μέθοδοι Μηχανικής Μάθησης", "course/1802"))
                    list.add(SimpleCourse("Διαδίκτυο των Πραγμάτων", "course/1803"))
                    list.add(SimpleCourse("Εφαρμογές Συστημάτων Αυτομάτου Ελέγχου", "course/1811"))
                    list.add(SimpleCourse("Μετατροπείς Ισχύος", "course/1812"))
                    list.add(SimpleCourse("Μικροηλεκτρονική *", "course/1837"))
                    list.add(SimpleCourse("Εφαρμογές Συστημάτων Ισχύος και ΑΠΕ *", "course/1838"))
                    list.add(SimpleCourse("Ηλεκτροκίνηση και Ευφυή Δίκτυα *", "course/1839"))
                    list.add(SimpleCourse("Ασύρματα Δίκτυα", "course/1871"))
                    list.add(SimpleCourse("Ειδικά Θέματα Δικτύων (CCNA) 1", "course/1872"))
                    list.add(SimpleCourse("Προηγμένα Θέματα Δικτύων", "course/1873"))
                    list.add(SimpleCourse("Συστήματα Κινητών Επικοινωνιών", "course/1874"))
                    list.add(SimpleCourse("Ελεύθερη Επιλογή Β", "course/1898"))
                    list.add(SimpleCourse("Ραδιοτηλεοπτική Παραγωγή *", "course/1899"))
                    list.add(
                        SimpleCourse(
                            "Οργάνωση Δεδομένων και Εξόρυξη Πληροφορίας",
                            "course/1841"
                        )
                    )
                    list.add(
                        SimpleCourse(
                            "Διαδικτυακές Υπηρεσίες Προστιθέμενης Αξίας",
                            "course/1842"
                        )
                    )
                    list.add(
                        SimpleCourse(
                            "Ανάπτυξη Ολοκληρωμένων Πληροφοριακών Συστημάτων",
                            "course/1848"
                        )
                    )

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "9" -> {
                    toolbar.root.title = getString(R.string.ninth_semester)

                    list.add(SimpleCourse("Πρακτική Άσκηση", "course/1970"))
                    list.add(SimpleCourse("Εφαρμογές Ενσωματωμένων Συστημάτων", "course/1911"))
                    list.add(SimpleCourse("Ρομποτική", "course/1912"))
                    list.add(SimpleCourse("ΑΠΕ και Ευφυή Ηλεκτρικά Δίκτυα", "course/1913"))
                    list.add(SimpleCourse("Απτικές Διεπαφές", "course/1914"))
                    list.add(SimpleCourse("Βιοϊατρική Τεχνολογία", "course/1915"))
                    list.add(
                        SimpleCourse(
                            "Συστήματα Μετρήσεων Υποβοηθούμενων από Η/Υ **",
                            "course/1916"
                        )
                    )
                    list.add(SimpleCourse("Ασφάλεια Δικτύων και Επικοινωνιών **", "course/1971"))
                    list.add(SimpleCourse("Δικτύωση Καθορισμένη από Λογισμικό", "course/1972"))
                    list.add(SimpleCourse("Ειδικά Θέματα Δικτύων (CCNA) 2", "course/1973"))
                    list.add(SimpleCourse("Δορυφορικές Επικοινωνίες", "course/1974"))
                    list.add(SimpleCourse("Τεχνολογία Πολυμέσων", "course/1975"))
                    list.add(SimpleCourse("Ελεύθερη Επιλογή Α", "course/1998"))
                    list.add(
                        SimpleCourse(
                            "Ανάπτυξη Διαδικτυακών Συστημάτων και Εφαρμογών",
                            "course/1941"
                        )
                    )
                    list.add(SimpleCourse("Επιχειρησιακή Έρευνα", "course/1942"))
                    list.add(
                        SimpleCourse(
                            "Ανάκτηση Πληροφοριών – Μηχανές Αναζήτησης",
                            "course/1943"
                        )
                    )
                    list.add(
                        SimpleCourse(
                            "Διαχείριση Συστήματος και Υπηρεσιών DBMS",
                            "course/1944"
                        )
                    )
                    list.add(SimpleCourse("Ευφυή Συστήματα", "course/1945"))
                    list.add(SimpleCourse("Προηγμένα Θέματα Τεχνητής Νοημοσύνης **", "course/1946"))
                    list.add(SimpleCourse("Προηγμένη Μηχανική Μάθηση", "course/1947"))
                    list.add(SimpleCourse("Κατανεμημένα Συστήματα", "course/1949"))
                    list.add(SimpleCourse("Σημασιολογικός Ιστός", "course/1950"))
                    list.add(SimpleCourse("Γραφικά Υπολογιστών", "course/1969"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
                "10" -> {
                    toolbar.root.title = getString(R.string.tenth_semester)

                    list.add(SimpleCourse("Διπλωματική Εργασία", "course/1999"))

                    val adapter = SimpleCourseAdapter(requireContext(), list)
                    courseList.adapter = adapter
                    courseList.onItemClickListener = this@SemesterFragment
                }
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val course: SimpleCourse = list[position]
        findNavController().navigate(SemesterFragmentDirections.actionMenuSemesterToSimpleCourseFragment(course.link!!))
    }

}