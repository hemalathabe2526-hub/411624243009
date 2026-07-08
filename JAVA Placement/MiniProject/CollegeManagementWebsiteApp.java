import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.stream.Collectors;

public class CollegeManagementWebsiteApp {
    private final Database database;
    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private DefaultTableModel studentTableModel;
    private DefaultTableModel facultyTableModel;
    private DefaultTableModel courseTableModel;
    private DefaultTableModel attendanceTableModel;
    private DefaultTableModel rbacTableModel;
    private JLabel dashboardStudentCountLabel;
    private JLabel dashboardFacultyCountLabel;
    private JLabel dashboardCourseCountLabel;
    private JLabel dashboardTopGpaLabel;
    private JLabel dashboardNotificationCountLabel;
    private JLabel analyticsTopStudentsLabel;
    private JLabel analyticsDepartmentTotalsLabel;
    private JLabel analyticsCourseCoverageLabel;
    private JLabel analyticsPlacementStudentsLabel;
    private JLabel analyticsAlertsLabel;
    private JLabel analyticsLearningPathsLabel;
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField studentYearField;
    private JTextField studentDeptField;
    private JTextField gpaField;
    private JTextField studentCourseCodeField;
    private String selectedStudentId;
    private RoleBasedAccessControl rbac;
    private QRAttendanceSystem attendanceSystem;
    private DynamicReportGenerator reportGenerator;

    // Session Management
    private RoleBasedAccessControl.User currentUser;
    private JPanel navigationPanel;
    private JLabel userInfoLabel;
    public CollegeManagementWebsiteApp() {
        database = new Database();
        rbac = new RoleBasedAccessControl();
        attendanceSystem = new QRAttendanceSystem();
        reportGenerator = new DynamicReportGenerator();
        frame = new JFrame("College Management Website Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 760);
        frame.setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        seedData();

        // First show login panel
        JPanel loginPanel = buildLoginPanel();
        cardPanel.add(loginPanel, "login");
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        cardLayout.show(cardPanel, "login");
    }
    private void initializeMainApplication() {
        JPanel dashboard = buildDashboard();
        JPanel studentPanel = buildStudentPanel();
        JPanel facultyPanel = buildFacultyPanel();
        JPanel coursePanel = buildCoursePanel();
        JPanel analyticsPanel = buildAnalyticsPanel();
        JPanel campusPanel = buildCampusPanel();
        JPanel attendancePanel = buildAttendancePanel();
        JPanel rbacPanel = buildRBACPanel();
        JPanel reportPanel = buildReportGeneratorPanel();
        cardPanel.add(dashboard, "dashboard");
        cardPanel.add(studentPanel, "students");
        cardPanel.add(facultyPanel, "faculty");
        cardPanel.add(coursePanel, "courses");
        cardPanel.add(analyticsPanel, "analytics");
        cardPanel.add(campusPanel, "campus");
        cardPanel.add(attendancePanel, "attendance");
        cardPanel.add(rbacPanel, "rbac");
        cardPanel.add(reportPanel, "reports");
        frame.remove(cardPanel);
        frame.setLayout(new BorderLayout());
        frame.add(buildNavigationBar(), BorderLayout.WEST);
        frame.add(cardPanel, BorderLayout.CENTER);
        refreshDashboard();
        refreshAnalytics();
        cardLayout.show(cardPanel, "dashboard");
    }
    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 248, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel loginCard = new JPanel();
        loginCard.setBackground(Color.WHITE);
        loginCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 224, 224)),
            new EmptyBorder(40, 40, 40, 40)
        ));
        loginCard.setLayout(new GridLayout(7, 1, 15, 15));
        loginCard.setPreferredSize(new Dimension(400, 450));
        JLabel titleLabel = new JLabel("College Management System");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 47, 60));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel subtitleLabel = new JLabel("Secure Login Portal");
        subtitleLabel.setFont(new Font("Inter", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(117, 117, 117));
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(new Font("Inter", Font.BOLD, 12));
        JTextField userIdField = new JTextField();
        userIdField.setFont(new Font("Inter", Font.PLAIN, 12));
        userIdField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        userIdField.setPreferredSize(new Dimension(300, 35));
        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setFont(new Font("Inter", Font.BOLD, 12));
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{
            "SUPER_ADMIN", "PRINCIPAL", "HOD", "FACULTY", "STUDENT", "ACCOUNTANT"
        });
        roleCombo.setFont(new Font("Inter", Font.PLAIN, 12));
        roleCombo.setPreferredSize(new Dimension(300, 35));
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(30, 136, 229));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Inter", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            String userId = userIdField.getText().trim();
            String selectedRole = (String) roleCombo.getSelectedItem();
            if (userId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter User ID", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            RoleBasedAccessControl.User user = rbac.getUserById(userId);
            if (user == null) {
                JOptionPane.showMessageDialog(frame, "User not found. Please check the User ID.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!user.role.name().equals(selectedRole)) {
                JOptionPane.showMessageDialog(frame, "Role mismatch. Please select the correct role.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentUser = user;
            attendanceSystem.setCurrentTeacherId(userId);
            userIdField.setText("");
            initializeMainApplication();
            JOptionPane.showMessageDialog(frame, "Welcome " + user.name + "!\nRole: " + user.role, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton demoButton = new JButton("🔑 Use Demo User (Faculty)");
        demoButton.setBackground(new Color(76, 175, 80));
        demoButton.setForeground(Color.WHITE);
        demoButton.setFont(new Font("Inter", Font.PLAIN, 12));
        demoButton.setFocusPainted(false);
        demoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        demoButton.addActionListener(e -> {
            userIdField.setText("F001");
            roleCombo.setSelectedItem("FACULTY");
        });
        loginCard.add(titleLabel);
        loginCard.add(subtitleLabel);
        loginCard.add(userIdLabel);
        loginCard.add(userIdField);
        loginCard.add(roleLabel);
        loginCard.add(roleCombo);
        loginCard.add(loginButton);
        loginCard.add(demoButton);
        JTextArea helpText = new JTextArea(6, 50);
        helpText.setText("Demo Users:\n\n" +
                "• U001 / SUPER_ADMIN (Dr. Sharma)\n" +
                "• U002 / PRINCIPAL (Dr. Patel)\n" +
                "• U003 / HOD (HOD Rajesh)\n" +
                "• F001 / FACULTY (Professor Aditi)\n" +
                "• S1001 / STUDENT (Ananya)\n" +
                "• ACC001 / ACCOUNTANT (Accountant Amit)");
        helpText.setFont(new Font("Inter", Font.PLAIN, 11));
        helpText.setEditable(false);
        helpText.setBackground(new Color(240, 248, 255));
        helpText.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginCard, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(helpText, gbc);
        return panel;
    }
    private JPanel buildNavigationBar() {
        navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(38, 50, 56));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setPreferredSize(new Dimension(240, 0));
        JLabel title = new JLabel("Campus Portal");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Inter", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(24, 20, 12, 20));
        navigationPanel.add(title);
        // User info section
        userInfoLabel = new JLabel();
        userInfoLabel.setForeground(new Color(200, 255, 200));
        userInfoLabel.setFont(new Font("Inter", Font.BOLD, 11));
        userInfoLabel.setBorder(new EmptyBorder(12, 20, 12, 20));
        updateUserInfo();
        navigationPanel.add(userInfoLabel);
        navigationPanel.add(Box.createVerticalStrut(10));

        // Navigation buttons based on role
        addRoleBasedNavigation();
        navigationPanel.add(Box.createVerticalGlue());
        navigationPanel.add(createFooterLabel("Real-time updates", "Stay connected with campus life"));
        JButton logoutButton = new JButton("LOGOUT");
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        logoutButton.setBackground(new Color(244, 67, 54));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("Inter", Font.BOLD, 14));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        logoutButton.addActionListener(e -> handleLogout());
        navigationPanel.add(Box.createVerticalStrut(10));
        navigationPanel.add(logoutButton);
        return navigationPanel;
    }
    private void updateUserInfo() {
        if (currentUser != null) {
            userInfoLabel.setText("<html>👤 " + currentUser.name + "<br/>Role: " + currentUser.role + "</html>");
        }
    }
    private void addRoleBasedNavigation() {
        if (currentUser == null) return;
        navigationPanel.add(createNavButton("Dashboard", "dashboard"));
        switch (currentUser.role) {
            case SUPER_ADMIN:
                navigationPanel.add(createNavButton("Students", "students"));
                navigationPanel.add(createNavButton("Faculty", "faculty"));
                navigationPanel.add(createNavButton("Courses", "courses"));
                navigationPanel.add(createNavButton("Analytics", "analytics"));
                navigationPanel.add(createNavButton("Campus Tour", "campus"));
                navigationPanel.add(createNavButton("Attendance QR", "attendance"));
                navigationPanel.add(createNavButton("RBAC & Roles", "rbac"));
                navigationPanel.add(createNavButton("Reports", "reports"));
                break;
            case PRINCIPAL:
                navigationPanel.add(createNavButton("Students", "students"));
                navigationPanel.add(createNavButton("Faculty", "faculty"));
                navigationPanel.add(createNavButton("Analytics", "analytics"));
                navigationPanel.add(createNavButton("Reports", "reports"));
                break;
            case HOD:
                navigationPanel.add(createNavButton("Faculty", "faculty"));
                navigationPanel.add(createNavButton("Courses", "courses"));
                navigationPanel.add(createNavButton("Attendance QR", "attendance"));
                navigationPanel.add(createNavButton("Analytics", "analytics"));
                break;
            case FACULTY:
                navigationPanel.add(createNavButton("Attendance QR", "attendance"));
                navigationPanel.add(createNavButton("Reports", "reports"));
                break;
            case STUDENT:
                navigationPanel.add(createNavButton("My Marks", "reports"));
                navigationPanel.add(createNavButton("Courses", "courses"));
                break;
            case ACCOUNTANT:
                navigationPanel.add(createNavButton("Reports", "reports"));
                navigationPanel.add(createNavButton("Analytics", "analytics"));
                break;
        }
    }
    private void handleLogout() {
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            currentUser = null;
            attendanceSystem.setCurrentTeacherId(null);

            // Clear cardPanel and show login again
            cardPanel.removeAll();
            JPanel loginPanel = buildLoginPanel();
            cardPanel.add(loginPanel, "login");
            frame.remove(navigationPanel);
            frame.add(cardPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            cardLayout.show(cardPanel, "login");
            JOptionPane.showMessageDialog(frame, "You have been logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private JButton createNavButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        button.setBackground(new Color(55, 71, 79));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Inter", Font.PLAIN, 16));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.addActionListener(e -> cardLayout.show(cardPanel, cardName));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }
    private JPanel createFooterLabel(String title, String subtitle) {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(new EmptyBorder(24, 20, 24, 20));
        footer.setOpaque(false);
        JLabel main = new JLabel(title);
        main.setForeground(Color.LIGHT_GRAY);
        main.setFont(new Font("Inter", Font.BOLD, 14));
        footer.add(main);
        JLabel detail = new JLabel(subtitle);
        detail.setForeground(new Color(176, 190, 197));
        detail.setFont(new Font("Inter", Font.PLAIN, 12));
        footer.add(detail);
        return footer;
    }
    private JPanel buildDashboard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel heading = new JLabel("Campus Dashboard");
        heading.setFont(new Font("Inter", Font.BOLD, 28));
        heading.setForeground(new Color(33, 47, 60));
        panel.add(heading, BorderLayout.NORTH);
        JPanel cardGrid = new JPanel(new GridLayout(2, 3, 18, 18));
        cardGrid.setOpaque(false);
        dashboardStudentCountLabel = createDashboardValueLabel(String.valueOf(database.listStudents().size()), new Color(255, 87, 34));
        dashboardFacultyCountLabel = createDashboardValueLabel(String.valueOf(database.listFaculty().size()), new Color(3, 169, 244));
        dashboardCourseCountLabel = createDashboardValueLabel(String.valueOf(database.listCourses().size()), new Color(124, 77, 255));
        dashboardTopGpaLabel = createDashboardValueLabel("N/A", new Color(255, 193, 7));
        dashboardNotificationCountLabel = createDashboardValueLabel(String.valueOf(database.notifications.size()), new Color(233, 30, 99));
        cardGrid.add(createStatCard("Students", dashboardStudentCountLabel, "Manage enrollment & profiles", new Color(255, 87, 34)));
        cardGrid.add(createStatCard("Faculty", dashboardFacultyCountLabel, "Available instructors", new Color(3, 169, 244)));
        cardGrid.add(createStatCard("Courses", dashboardCourseCountLabel, "Learning paths", new Color(124, 77, 255)));
        cardGrid.add(createStatCard("Active Tours", createDashboardValueLabel("1", new Color(76, 175, 80)), "Virtual campus experience", new Color(76, 175, 80)));
        cardGrid.add(createStatCard("Top GPA", dashboardTopGpaLabel, "Current topper", new Color(255, 193, 7)));
        cardGrid.add(createStatCard("Notifications", dashboardNotificationCountLabel, "Latest campus alerts", new Color(233, 30, 99)));
        panel.add(cardGrid, BorderLayout.CENTER);
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 18, 18));
        actionPanel.setOpaque(false);
        actionPanel.setBorder(new EmptyBorder(24, 0, 0, 0));
        actionPanel.add(createHeroCard("Smart Course Advisor", "Receive personalized course recommendations based on student profile and department."));
        actionPanel.add(createHeroCard("Admissions Funnel", "Visualize admissions and retention across departments quickly."));
        actionPanel.add(createHeroCard("Campus Alerts", "See live notifications and urgent announcements in one place."));
        panel.add(actionPanel, BorderLayout.SOUTH);
        return panel;
    }
    private JPanel createStatCard(String title, JLabel valueLabel, String subtitle, Color accent) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)), new EmptyBorder(18, 18, 18, 18)));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 14));
        titleLabel.setForeground(new Color(117, 117, 117));
        card.add(titleLabel, BorderLayout.NORTH);
        valueLabel.setFont(new Font("Inter", Font.BOLD, 32));
        valueLabel.setForeground(accent);
        valueLabel.setBorder(new EmptyBorder(12, 0, 0, 0));
        card.add(valueLabel, BorderLayout.CENTER);
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Inter", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(158, 158, 158));
        subtitleLabel.setBorder(new EmptyBorder(14, 0, 0, 0));
        card.add(subtitleLabel, BorderLayout.SOUTH);
        return card;
    }
    private JLabel createDashboardValueLabel(String text, Color accent) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Inter", Font.BOLD, 32));
        label.setForeground(accent);
        label.setBorder(new EmptyBorder(12, 0, 0, 0));
        return label;
    }
    private JPanel createHeroCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(255, 255, 255));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224,224,224)), new EmptyBorder(18, 18, 18, 18)));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 16));
        titleLabel.setForeground(new Color(33,47,60));
        JLabel descLabel = new JLabel("<html><body style='width:240px'>" + description + "</body></html>");
        descLabel.setFont(new Font("Inter", Font.PLAIN, 13));
        descLabel.setForeground(new Color(117,117,117));
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);
        return card;
    }
    private String generateStudentId() {
        int maxNumber = database.listStudents().stream()
                .map(s -> s.id)
                .filter(id -> id.matches("S\\d+"))
                .mapToInt(id -> Integer.parseInt(id.substring(1)))
                .max()
                .orElse(1000);
        return "S" + (maxNumber + 1);
    }
    private void clearStudentForm() {
        selectedStudentId = null;
        studentIdField.setText(generateStudentId());
        studentNameField.setText("");
        studentYearField.setText("");
        studentDeptField.setText("");
        gpaField.setText("");
        studentCourseCodeField.setText("");
    }
    private JPanel buildStudentPanel() {
        JPanel panel = createContentPanel("Student Management");
        JPanel left = new JPanel(new BorderLayout(12, 12));
        left.setOpaque(false);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        studentTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Dept", "Year", "GPA", "Courses"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(studentTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedStudentId = (String) studentTableModel.getValueAt(row, 0);
                    Student selected = database.getStudent(selectedStudentId);
                    if (selected != null) {
                        studentIdField.setText(selected.id);
                        studentNameField.setText(selected.name);
                        studentDeptField.setText(selected.department);
                        studentYearField.setText(String.valueOf(selected.year));
                        gpaField.setText(String.format("%.2f", selected.gpa));
                    }
                }
            }
        });
        fillStudentTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(760, 420));
        left.add(scrollPane, BorderLayout.CENTER);
        JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));
        form.setOpaque(false);
        studentIdField = new JTextField();
        studentNameField = new JTextField();
        studentYearField = new JTextField();
        studentDeptField = new JTextField();
        gpaField = new JTextField();
        studentCourseCodeField = new JTextField();
        studentIdField.setEditable(false);
        studentIdField.setBackground(new Color(238, 238, 238));
        form.add(new JLabel("Student ID:"));
        form.add(studentIdField);
        form.add(new JLabel("Name:"));
        form.add(studentNameField);
        form.add(new JLabel("Year:"));
        form.add(studentYearField);
        form.add(new JLabel("Department:"));
        form.add(studentDeptField);
        form.add(new JLabel("GPA:"));
        form.add(gpaField);
        form.add(new JLabel("Course Code (enroll/drop):"));
        form.add(studentCourseCodeField);
        JButton addButton = createActionButton("Create Profile", e -> {
            String id = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            String dept = studentDeptField.getText().trim();
            String yearText = studentYearField.getText().trim();
            String gpaText = gpaField.getText().trim();
            if (name.isEmpty() || dept.isEmpty() || yearText.isEmpty() || gpaText.isEmpty()) {
                showError("Please fill all student fields before creating a profile.");
                return;
            }
            if (database.studentExists(id)) {
                showError("A student with this ID already exists.");
                return;
            }
            int year;
            double gpa;
            try {
                year = Integer.parseInt(yearText);
            } catch (NumberFormatException ex) {
                showError("Year must be a whole number.");
                return;
            }
            try {
                gpa = Double.parseDouble(gpaText);
            } catch (NumberFormatException ex) {
                showError("GPA must be a valid decimal number.");
                return;
            }
            if (!isValidStudentYear(dept, year)) {
                showError(getYearRangeMessage(dept));
                return;
            }
            if (gpa < 0.0 || gpa > 10.0) {
                showError("GPA must be between 0.0 and 10.0.");
                return;
            }
            Student newStudent = new Student(id, name, year, dept);
            newStudent.setGpa(gpa);
            database.addStudent(newStudent);
            addNotification("New student profile created: " + name);
            showMessage("Student profile created successfully.");
            clearStudentForm();
            refreshAllViews();
        });
        JButton updateButton = createActionButton("Update Profile", e -> {
            if (selectedStudentId == null) {
                showError("Please select a student from the table to update.");
                return;
            }
            String name = studentNameField.getText().trim();
            String dept = studentDeptField.getText().trim();
            String yearText = studentYearField.getText().trim();
            String gpaText = gpaField.getText().trim();
            if (name.isEmpty() || dept.isEmpty() || yearText.isEmpty() || gpaText.isEmpty()) {
                showError("Please fill all student fields before updating the profile.");
                return;
            }
            int year;
            double gpa;
            try {
                year = Integer.parseInt(yearText);
            } catch (NumberFormatException ex) {
                showError("Year must be a whole number.");
                return;
            }
            try {
                gpa = Double.parseDouble(gpaText);
            } catch (NumberFormatException ex) {
                showError("GPA must be a valid decimal number.");
                return;
            }
            if (!isValidStudentYear(dept, year)) {
                showError(getYearRangeMessage(dept));
                return;
            }
            if (gpa < 0.0 || gpa > 10.0) {
                showError("GPA must be between 0.0 and 10.0.");
                return;
            }
            database.updateStudent(selectedStudentId, name, year, dept, gpa);
            addNotification("Student profile updated: " + selectedStudentId);
            showMessage("Student profile updated successfully.");
            refreshAllViews();
        });
        JButton newButton = createActionButton("New Student", e -> clearStudentForm());
        JButton enrollButton = createActionButton("Enroll Course", e -> {
            String studentId = studentIdField.getText().trim();
            String courseCode = studentCourseCodeField.getText().trim();
            if (studentId.isEmpty() || courseCode.isEmpty()) {
                showError("Please enter both student ID and course code to enroll.");
                return;
            }
            if (!database.studentExists(studentId)) {
                showError("Student ID not found.");
                return;
            }
            if (!database.courseExists(courseCode)) {
                showError("Course code not found.");
                return;
            }
            Student student = database.getStudent(studentId);
            if (student.courses.contains(courseCode)) {
                showError("This student is already enrolled in the selected course.");
                return;
            }
            database.enrollStudent(studentId, courseCode);
            addNotification("Student " + studentId + " enrolled in course: " + courseCode);
            showMessage("Enrollment completed.");
            refreshAllViews();
        });
        JButton dropButton = createActionButton("Drop Course", e -> {
            String studentId = studentIdField.getText().trim();
            String courseCode = studentCourseCodeField.getText().trim();
            if (studentId.isEmpty() || courseCode.isEmpty()) {
                showError("Please enter both student ID and course code to drop.");
                return;
            }
            if (!database.studentExists(studentId)) {
                showError("Student ID not found.");
                return;
            }
            Student student = database.getStudent(studentId);
            if (!student.courses.contains(courseCode)) {
                showError("Student is not enrolled in the selected course.");
                return;
            }
            database.dropStudentFromCourse(studentId, courseCode);
            addNotification("Student " + studentId + " dropped course: " + courseCode);
            showMessage("Course dropped successfully.");
            refreshAllViews();
        });
        JButton quickSearchButton = createActionButton("Quick Search", e -> {
            String searchText = studentNameField.getText().trim().toLowerCase();
            List<Student> results = database.listStudents().stream()
                    .filter(s -> s.id.toLowerCase().contains(searchText) || s.name.toLowerCase().contains(searchText) || s.department.toLowerCase().contains(searchText))
                    .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.id.substring(1))))
                    .collect(Collectors.toList());
            showSearchResults(results);
        });
        right.add(form);
        right.add(Box.createVerticalStrut(18));
        right.add(addButton);
        right.add(Box.createVerticalStrut(10));
        right.add(updateButton);
        right.add(Box.createVerticalStrut(10));
        right.add(newButton);
        right.add(Box.createVerticalStrut(10));
        right.add(enrollButton);
        right.add(Box.createVerticalStrut(10));
        right.add(dropButton);
        right.add(Box.createVerticalStrut(10));
        right.add(quickSearchButton);
        right.add(Box.createVerticalGlue());
        left.setPreferredSize(new Dimension(820, 0));
        panel.add(left, BorderLayout.CENTER);
        panel.add(right, BorderLayout.EAST);
        clearStudentForm();
        return panel;
    }
    private void fillStudentTable() {
        studentTableModel.setRowCount(0);
        database.listStudents().stream()
                .sorted(Comparator.comparing(s -> s.id))
                .forEach(s -> studentTableModel.addRow(new Object[]{s.id, s.name, s.department, s.year, String.format("%.2f", s.gpa), String.join(", ", s.courses)}));
    }
    private JPanel buildFacultyPanel() {
        JPanel panel = createContentPanel("Faculty Management");
        facultyTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Department", "Title", "Courses"}, 0);
        JTable table = new JTable(facultyTableModel);
        fillFacultyTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(820, 520));
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel control = new JPanel(new GridLayout(5, 2, 10, 10));
        control.setOpaque(false);
        JTextField facultyIdField = new JTextField();
        JTextField facultyNameField = new JTextField();
        JTextField facultyDeptField = new JTextField();
        JTextField facultyTitleField = new JTextField();
        JTextField facultyCourseField = new JTextField();
        control.add(new JLabel("Faculty ID:"));
        control.add(facultyIdField);
        control.add(new JLabel("Name:"));
        control.add(facultyNameField);
        control.add(new JLabel("Department:"));
        control.add(facultyDeptField);
        control.add(new JLabel("Title:"));
        control.add(facultyTitleField);
        control.add(new JLabel("Assign course code:"));
        control.add(facultyCourseField);
        JPanel actionPanel = new JPanel();
        actionPanel.setOpaque(false);
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(createActionButton("Add Faculty", e -> {
            String id = facultyIdField.getText().trim();
            String name = facultyNameField.getText().trim();
            String dept = facultyDeptField.getText().trim();
            String title = facultyTitleField.getText().trim();
            if (id.isEmpty() || name.isEmpty() || dept.isEmpty() || title.isEmpty()) {
                showError("Please fill all faculty fields before adding a faculty member.");
                return;
            }
            if (database.facultyExists(id)) {
                showError("A faculty member with this ID already exists.");
                return;
            }
            database.addFaculty(new Faculty(id, name, dept, title));
            addNotification("Faculty added: " + name);
            showMessage("Faculty member added successfully.");
            refreshAllViews();
        }));
        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(createActionButton("Assign Course", e -> {
            String facultyId = facultyIdField.getText().trim();
            String courseCode = facultyCourseField.getText().trim();
            if (facultyId.isEmpty() || courseCode.isEmpty()) {
                showError("Please enter both faculty ID and course code.");
                return;
            }
            if (!database.facultyExists(facultyId)) {
                showError("Faculty ID not found.");
                return;
            }
            if (!database.courseExists(courseCode)) {
                showError("Course code not found.");
                return;
            }
            Faculty facultyMember = database.getFaculty(facultyId);
            if (facultyMember.courses.contains(courseCode)) {
                showError("This faculty member is already assigned to this course.");
                return;
            }
            database.assignFacultyToCourse(facultyId, courseCode);
            addNotification("Faculty assigned to: " + courseCode);
            showMessage("Course assignment updated.");
            refreshAllViews();
        }));
        JPanel right = new JPanel(new BorderLayout(14, 14));
        right.setOpaque(false);
        right.add(control, BorderLayout.NORTH);
        right.add(actionPanel, BorderLayout.SOUTH);
        panel.add(right, BorderLayout.EAST);
        return panel;
    }
    private void fillFacultyTable() {
        facultyTableModel.setRowCount(0);
        for (Faculty f : database.listFaculty()) {
            facultyTableModel.addRow(new Object[]{f.id, f.name, f.department, f.title, String.join(", ", f.courses)});
        }
    }
    private JPanel buildCoursePanel() {
        JPanel panel = createContentPanel("Course & Curriculum");
        courseTableModel = new DefaultTableModel(new Object[]{"Code", "Name", "Dept", "Credits", "Avg Rating"}, 0);
        JTable table = new JTable(courseTableModel);
        fillCourseTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(820, 520));
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTextField courseCodeField = new JTextField();
        JTextField courseNameField = new JTextField();
        JTextField courseDeptField = new JTextField();
        JTextField courseCreditField = new JTextField();
        JTextField courseRatingField = new JTextField();
        JPanel form = new JPanel(new GridLayout(10, 1, 8, 8));
        form.setOpaque(false);
        form.add(new JLabel("Course Code:"));
        form.add(courseCodeField);
        form.add(new JLabel("Course Name:"));
        form.add(courseNameField);
        form.add(new JLabel("Department:"));
        form.add(courseDeptField);
        form.add(new JLabel("Credits:"));
        form.add(courseCreditField);
        form.add(new JLabel("Rate course (1-5):"));
        form.add(courseRatingField);
        right.add(form);
        right.add(Box.createVerticalStrut(12));
        right.add(createActionButton("Build Course", e -> {
            String code = courseCodeField.getText().trim();
            String name = courseNameField.getText().trim();
            String dept = courseDeptField.getText().trim();
            String creditsText = courseCreditField.getText().trim();
            if (code.isEmpty() || name.isEmpty() || dept.isEmpty() || creditsText.isEmpty()) {
                showError("Please fill all course fields before creating a course.");
                return;
            }
            if (database.courseExists(code)) {
                showError("A course with this code already exists.");
                return;
            }
            int credits;
            try {
                credits = Integer.parseInt(creditsText);
            } catch (NumberFormatException ex) {
                showError("Credits must be a whole number.");
                return;
            }
            if (credits <= 0) {
                showError("Credits must be greater than zero.");
                return;
            }
            database.addCourse(new Course(code, name, dept, credits));
            addNotification("New course created: " + name);
            showMessage("Course added successfully.");
            refreshAllViews();
        }));
        right.add(Box.createVerticalStrut(10));
        right.add(createActionButton("Rate Course", e -> {
            String code = courseCodeField.getText().trim();
            String ratingText = courseRatingField.getText().trim();
            if (code.isEmpty() || ratingText.isEmpty()) {
                showError("Please enter a course code and rating.");
                return;
            }
            if (!database.courseExists(code)) {
                showError("Course code not found.");
                return;
            }
            int rating;
            try {
                rating = Integer.parseInt(ratingText);
            } catch (NumberFormatException ex) {
                showError("Rating must be a whole number between 1 and 5.");
                return;
            }
            if (rating < 1 || rating > 5) {
                showError("Rating must be between 1 and 5.");
                return;
            }
            database.rateCourse(code, rating);
            addNotification("Course rated: " + code);
            showMessage("Course rating updated.");
            refreshAllViews();
        }));
        right.add(Box.createVerticalStrut(10));
        right.add(createActionButton("Suggest Path", e -> {
            if (courseDeptField.getText().trim().isEmpty()) {
                showError("Please enter a department for course suggestions.");
                return;
            }
            showCourseSuggestions(courseDeptField.getText().trim());
        }));
        right.add(Box.createVerticalGlue());
        panel.add(right, BorderLayout.EAST);
        return panel;
    }
    private void fillCourseTable() {
        courseTableModel.setRowCount(0);
        for (Course c : database.listCourses()) {
            courseTableModel.addRow(new Object[]{c.code, c.name, c.department, c.credits, String.format("%.2f", c.averageRating)});
        }
    }
    private JPanel buildAnalyticsPanel() {
        JPanel panel = createContentPanel("Campus Analytics");
        panel.setLayout(new GridLayout(1, 2, 18, 18));
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setOpaque(false);
        analyticsTopStudentsLabel = createMetricTextLabel(database.topStudents(5).stream().map(s -> s.name + " (" + String.format("%.2f", s.gpa) + ")").collect(Collectors.joining("<br>")));
        analyticsDepartmentTotalsLabel = createMetricTextLabel(database.studentsPerDepartment().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining("<br>")));
        analyticsCourseCoverageLabel = createMetricTextLabel(database.coursesPerDepartment().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining("<br>")));
        analyticsPlacementStudentsLabel = createMetricTextLabel(getPlacementStudentText(9.3));
        analyticsAlertsLabel = createMetricTextLabel(String.join("<br>", database.notifications));
        analyticsLearningPathsLabel = createMetricTextLabel(database.listCourses().stream().map(c -> c.code + " - " + c.name).limit(8).collect(Collectors.joining("<br>")));
        left.add(createMetricCard("Top Students", analyticsTopStudentsLabel));
        left.add(Box.createVerticalStrut(16));
        left.add(createMetricCard("Placement Students", analyticsPlacementStudentsLabel));
        left.add(Box.createVerticalStrut(16));
        left.add(createMetricCard("Department Totals", analyticsDepartmentTotalsLabel));
        left.add(Box.createVerticalStrut(16));
        left.add(createMetricCard("Course Coverage", analyticsCourseCoverageLabel));
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setOpaque(false);
        right.add(createMetricCard("Latest Alerts", analyticsAlertsLabel));
        right.add(Box.createVerticalStrut(16));
        right.add(createMetricCard("Learning Paths", analyticsLearningPathsLabel));
        panel.add(left);
        panel.add(right);
        return panel;
    }
    private JPanel createMetricCard(String title, JLabel contentLabel) {
        JPanel card = new JPanel();
        card.setBackground(new Color(255, 255, 255));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)), new EmptyBorder(18, 18, 18, 18)));
        card.setLayout(new BorderLayout(0, 12));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 16));
        titleLabel.setForeground(new Color(33, 47, 60));
        card.add(titleLabel, BorderLayout.NORTH);
        contentLabel.setFont(new Font("Inter", Font.PLAIN, 13));
        contentLabel.setForeground(new Color(95, 99, 104));
        card.add(contentLabel, BorderLayout.CENTER);
        return card;
    }
    private JLabel createMetricTextLabel(String content) {
        String htmlContent = "<html><body style='width:320px;font-family:Inter,sans-serif;'>" + content + "</body></html>";
        JLabel label = new JLabel(htmlContent);
        label.setFont(new Font("Inter", Font.PLAIN, 13));
        label.setForeground(new Color(95, 99, 104));
        return label;
    }
    private String getPlacementStudentText(double minGpa) {
        List<Student> placementStudents = database.listStudents().stream()
                .filter(s -> s.gpa > minGpa)
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.id.substring(1))))
                .collect(Collectors.toList());
        if (placementStudents.isEmpty()) {
            return "No placement candidates with GPA above " + minGpa + ".";
        }
        return placementStudents.stream()
                .map(s -> "&#8226; " + s.id + " - " + s.name + " (" + String.format("%.2f", s.gpa) + ")")
                .collect(Collectors.joining("<br/>"));
    }
    private JPanel buildAttendancePanel() {
        JPanel panel = createContentPanel("QR-Based Attendance System - Teacher's Session");
        JPanel left = new JPanel(new BorderLayout(12, 12));
        left.setOpaque(false);
        attendanceTableModel = new DefaultTableModel(new Object[]{"Student ID", "Name", "Teacher Name", "Teacher Code", "Date", "Time", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(attendanceTableModel);
        fillAttendanceTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(760, 420));
        left.add(scrollPane, BorderLayout.CENTER);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Teacher Information Input
        JLabel teacherInfoLabel = new JLabel("📋 Teacher Information");
        teacherInfoLabel.setFont(new Font("Inter", Font.BOLD, 14));
        teacherInfoLabel.setForeground(new Color(30, 136, 229));
        right.add(teacherInfoLabel);
        right.add(Box.createVerticalStrut(10));
        JPanel teacherForm = new JPanel(new GridLayout(2, 2, 10, 10));
        teacherForm.setOpaque(false);
        JTextField teacherNameField = new JTextField();
        JTextField teacherCodeField = new JTextField();
        teacherForm.add(new JLabel("👩‍🏫 Teacher Name:"));
        teacherForm.add(teacherNameField);
        teacherForm.add(new JLabel("🔑 Staff Code:"));
        teacherForm.add(teacherCodeField);
        right.add(teacherForm);
        right.add(Box.createVerticalStrut(15));
        // Student Information Input
        JLabel studentInfoLabel = new JLabel("📚 Student Attendance");
        studentInfoLabel.setFont(new Font("Inter", Font.BOLD, 14));
        studentInfoLabel.setForeground(new Color(30, 136, 229));
        right.add(studentInfoLabel);
        right.add(Box.createVerticalStrut(10));
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setOpaque(false);
        JTextField studentIdField = new JTextField();
        JTextField studentNameField = new JTextField();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Present", "Absent", "Late"});
        form.add(new JLabel("Student ID:"));
        form.add(studentIdField);
        form.add(new JLabel("Student Name:"));
        form.add(studentNameField);
        form.add(new JLabel("Status:"));
        form.add(statusBox);
        right.add(form);
        right.add(Box.createVerticalStrut(15));
        right.add(createActionButton("Mark Attendance", e -> {
            String teacherName = teacherNameField.getText().trim();
            String teacherCode = teacherCodeField.getText().trim();
            String studentId = studentIdField.getText().trim();
            String studentName = studentNameField.getText().trim();
            String status = (String) statusBox.getSelectedItem();
            
            if (teacherName.isEmpty() || teacherCode.isEmpty()) {
                showError("Please enter Teacher Name and Staff Code first.");
                return;
            }
            if (studentId.isEmpty() || studentName.isEmpty()) {
                showError("Please enter Student ID and Name.");
                return;
            }
            
            attendanceSystem.markAttendanceWithTeacher(studentId, studentName, status, teacherCode, teacherName, teacherCode);
            addNotification("Attendance marked by " + teacherName + " (Code: " + teacherCode + ") for " + studentName + ": " + status);
            showMessage("✓ Attendance recorded successfully\n\n" +
                       "Teacher: " + teacherName + "\n" +
                       "Code: " + teacherCode + "\n" +
                       "Student: " + studentName + "\n" +
                       "Status: " + status);
            fillAttendanceTable();
            studentIdField.setText("");
            studentNameField.setText("");
        }));
        right.add(Box.createVerticalStrut(10));
        right.add(createActionButton("📲 Generate QR Code", e -> {
            String teacherName = teacherNameField.getText().trim();
            String teacherCode = teacherCodeField.getText().trim();
            
            if (teacherName.isEmpty() || teacherCode.isEmpty()) {
                showError("Please enter Teacher Name and Staff Code first.");
                return;
            }
            
            String qrData = attendanceSystem.generateTeacherQRCodeWithInfo(teacherName, teacherCode);
            showMessage("🎫 QR Code Generated Successfully!\n\n" +
                       "Teacher: " + teacherName + "\n" +
                       "Code: " + teacherCode + "\n\n" +
                       "QR Data:\n" + qrData + 
                       "\n\nShare this QR with students to mark attendance.");
        }));
        right.add(Box.createVerticalStrut(10));
        right.add(createActionButton("Scan Student QR", e -> {
            String teacherName = teacherNameField.getText().trim();
            String teacherCode = teacherCodeField.getText().trim();
            
            if (teacherName.isEmpty() || teacherCode.isEmpty()) {
                showError("Please enter Teacher Name and Staff Code first.");
                return;
            }
            
            String qrCode = JOptionPane.showInputDialog(frame, "Scan Student QR Code:", "");
            if (qrCode != null && !qrCode.trim().isEmpty()) {
                attendanceSystem.processStudentQRCode(qrCode, teacherCode);
                showMessage("✓ QR Code processed! Student attendance marked as Present.");
                fillAttendanceTable();
            }
        }));
        right.add(Box.createVerticalGlue());
        JPanel container = new JPanel(new BorderLayout(12, 12));
        container.setOpaque(false);
        container.add(left, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);
        panel.add(container, BorderLayout.CENTER);
        return panel;
    }
    private void fillAttendanceTable() {
        attendanceTableModel.setRowCount(0);
        for (QRAttendanceSystem.AttendanceRecord record : attendanceSystem.getAttendanceRecords()) {
            attendanceTableModel.addRow(new Object[]{record.studentId, record.studentName, record.teacherName, record.teacherCode, record.date, record.time, record.status});
        }
    }
    private JPanel buildRBACPanel() {
        JPanel panel = createContentPanel("Role-Based Access Control (RBAC)");
        JPanel left = new JPanel(new BorderLayout(12, 12));
        left.setOpaque(false);
        rbacTableModel = new DefaultTableModel(new Object[]{"User ID", "Name", "Role", "Department", "Permissions"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(rbacTableModel);
        fillRBACTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(760, 420));
        left.add(scrollPane, BorderLayout.CENTER);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setOpaque(false);
        JTextField userIdField = new JTextField();
        JTextField userNameField = new JTextField();
        JComboBox<RoleBasedAccessControl.UserRole> roleBox = new JComboBox<>(RoleBasedAccessControl.UserRole.values());
        JTextField deptField = new JTextField();
        form.add(new JLabel("User ID:"));
        form.add(userIdField);
        form.add(new JLabel("User Name:"));
        form.add(userNameField);
        form.add(new JLabel("Role:"));
        form.add(roleBox);
        form.add(new JLabel("Department:"));
        form.add(deptField);
        right.add(form);
        right.add(Box.createVerticalStrut(18));
        right.add(createActionButton("Create User", e -> {
            String id = userIdField.getText().trim();
            String name = userNameField.getText().trim();
            String dept = deptField.getText().trim();
            RoleBasedAccessControl.UserRole role = (RoleBasedAccessControl.UserRole) roleBox.getSelectedItem();
            if (id.isEmpty() || name.isEmpty() || dept.isEmpty()) {
                showError("Please fill all fields.");
                return;
            }
            rbac.createUser(id, name, role, dept);
            addNotification("User created: " + name + " (" + role + ")");
            showMessage("User created successfully.");
            fillRBACTable();
            userIdField.setText("");
            userNameField.setText("");
            deptField.setText("");
        }));
        right.add(Box.createVerticalStrut(10));
        right.add(createActionButton("View Permissions", e -> {
            String id = userIdField.getText().trim();
            if (id.isEmpty()) {
                showError("Please enter user ID.");
                return;
            }
            String perms = rbac.getUserPermissions(id);
            showMessage("Permissions for " + id + ":\n" + perms);
        }));
        right.add(Box.createVerticalStrut(10));
        right.add(createActionButton("Check Access", e -> {
            String id = userIdField.getText().trim();
            String action = JOptionPane.showInputDialog(frame, "Enter action to check (view_marks/generate_reports/manage_fees):", "");
            if (action != null && !action.trim().isEmpty()) {
                boolean hasAccess = rbac.hasAccess(id, action);
                showMessage("Access " + (hasAccess ? "GRANTED" : "DENIED") + " for action: " + action);
            }
        }));
        right.add(Box.createVerticalGlue());
        JPanel container = new JPanel(new BorderLayout(12, 12));
        container.setOpaque(false);
        container.add(left, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);
        panel.add(container, BorderLayout.CENTER);
        return panel;
    }
    private void fillRBACTable() {
        rbacTableModel.setRowCount(0);
        for (RoleBasedAccessControl.User user : rbac.getAllUsers()) {
            String perms = String.join(", ", user.role.getPermissions());
            rbacTableModel.addRow(new Object[]{user.id, user.name, user.role, user.department, perms});
        }
    }
    private JPanel buildReportGeneratorPanel() {
        JPanel panel = createContentPanel("Dynamic Report Generator");
        JPanel reportOptions = new JPanel(new GridLayout(3, 1, 18, 18));
        reportOptions.setOpaque(false);
        reportOptions.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel marksheetPanel = new JPanel(new BorderLayout(12, 12));
        marksheetPanel.setBackground(new Color(255, 255, 255));
        marksheetPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)), new EmptyBorder(18, 18, 18, 18)));
        JLabel marksheetTitle = new JLabel("PDF Marksheets");
        marksheetTitle.setFont(new Font("Inter", Font.BOLD, 16));
        marksheetPanel.add(marksheetTitle, BorderLayout.NORTH);
        JLabel marksheetDesc = new JLabel("Generate detailed academic performance reports in PDF format for students.");
        marksheetDesc.setFont(new Font("Inter", Font.PLAIN, 12));
        marksheetPanel.add(marksheetDesc, BorderLayout.CENTER);
        JButton marksheetBtn = createActionButton("Generate Marksheet", e -> {
            String studentId = JOptionPane.showInputDialog(frame, "Enter Student ID:", "");
            if (studentId != null && !studentId.trim().isEmpty()) {
                String report = reportGenerator.generateMarksheet(studentId.trim());
                showMessage("Marksheet Generated:\n" + report);
            }
        });
        marksheetPanel.add(marksheetBtn, BorderLayout.SOUTH);
        JPanel attendanceReportPanel = new JPanel(new BorderLayout(12, 12));
        attendanceReportPanel.setBackground(new Color(255, 255, 255));
        attendanceReportPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)), new EmptyBorder(18, 18, 18, 18)));
        JLabel attendanceTitle = new JLabel("Attendance Reports");
        attendanceTitle.setFont(new Font("Inter", Font.BOLD, 16));
        attendanceReportPanel.add(attendanceTitle, BorderLayout.NORTH);
        JLabel attendanceDesc = new JLabel("Generate attendance summaries by date, student, or department.");
        attendanceDesc.setFont(new Font("Inter", Font.PLAIN, 12));
        attendanceReportPanel.add(attendanceDesc, BorderLayout.CENTER);
        JButton attendanceBtn = createActionButton("Generate Attendance Report", e -> {
            String month = JOptionPane.showInputDialog(frame, "Enter month (MM):", "");
            if (month != null && !month.trim().isEmpty()) {
                String report = reportGenerator.generateAttendanceReport(month.trim());
                showMessage("Attendance Report Generated:\n" + report);
            }
        });
        attendanceReportPanel.add(attendanceBtn, BorderLayout.SOUTH);
        JPanel feePanel = new JPanel(new BorderLayout(12, 12));
        feePanel.setBackground(new Color(255, 255, 255));
        feePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)), new EmptyBorder(18, 18, 18, 18)));
        JLabel feeTitle = new JLabel("Fee Receipts");
        feeTitle.setFont(new Font("Inter", Font.BOLD, 16));
        feePanel.add(feeTitle, BorderLayout.NORTH);
        JLabel feeDesc = new JLabel("Generate detailed fee receipts and payment reports for students.");
        feeDesc.setFont(new Font("Inter", Font.PLAIN, 12));
        feePanel.add(feeDesc, BorderLayout.CENTER);
        JButton feeBtn = createActionButton("Generate Fee Receipt", e -> {
            String studentId = JOptionPane.showInputDialog(frame, "Enter Student ID:", "");
            if (studentId != null && !studentId.trim().isEmpty()) {
                String receipt = reportGenerator.generateFeeReceipt(studentId.trim());
                showMessage("Fee Receipt Generated:\n" + receipt);
            }
        });
        feePanel.add(feeBtn, BorderLayout.SOUTH);
        reportOptions.add(marksheetPanel);
        reportOptions.add(attendanceReportPanel);
        reportOptions.add(feePanel);
        panel.add(reportOptions, BorderLayout.CENTER);
        return panel;
    }
    private JPanel buildCampusPanel() {
        JPanel panel = createContentPanel("Virtual Campus Experience");
        JPanel tour = new JPanel(new GridLayout(1, 2, 28, 28));
        tour.setOpaque(false);
        JPanel left = new JPanel();
        left.setOpaque(true);
        left.setBackground(new Color(255,255,255));
        left.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Campus Tour Overview");
        title.setFont(new Font("Inter", Font.BOLD, 20));
        title.setForeground(new Color(33,47,60));
        left.add(title);
        left.add(Box.createVerticalStrut(18));
        left.add(new JLabel("Explore the website-inspired campus modules with interactive details and animations."));
        left.add(Box.createVerticalStrut(24));
        left.add(createProgressBarCard("Library Upgrades", 90));
        left.add(Box.createVerticalStrut(18));
        left.add(createProgressBarCard("Lab Capacity", 75));
        left.add(Box.createVerticalStrut(18));
        left.add(createProgressBarCard("Student Satisfaction", 84));
        JPanel right = new JPanel(new GridLayout(4, 1, 16, 16));
        right.setOpaque(false);
        right.add(createCampusCard("Main Portal", "Fast login, announcements, and student dashboard."));
        right.add(createCampusCard("Learning Blocks", "Track course progress, assignments and smart recommendations."));
        right.add(createCampusCard("Support Hub", "Counseling, feedback, and help center integrated."));
        right.add(createCampusCard("Event Showcase", "Live campus events and club updates with dynamic feeds."));
        tour.add(left);
        tour.add(right);
        panel.add(tour, BorderLayout.CENTER);
        return panel;
    }
    private JPanel createCampusCard(String title, String description) {
        JPanel card = new JPanel();
        card.setBackground(new Color(246, 249, 252));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(214, 230, 240)), new EmptyBorder(16, 16, 16, 16)));
        card.setLayout(new BorderLayout(0, 8));
        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Inter", Font.BOLD, 16));
        JLabel desc = new JLabel("<html>" + description + "</html>");
        desc.setFont(new Font("Inter", Font.PLAIN, 13));
        desc.setForeground(new Color(95, 99, 104));
        card.add(heading, BorderLayout.NORTH);
        card.add(desc, BorderLayout.CENTER);
        return card;
    }
    private JPanel createProgressBarCard(String title, int value) {
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BorderLayout());
        JLabel label = new JLabel(title);
        label.setFont(new Font("Inter", Font.BOLD, 14));
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(3, 155, 229));
        card.add(label, BorderLayout.NORTH);
        card.add(progressBar, BorderLayout.CENTER);
        Timer timer = new Timer(12, new ActionListener() {
            int current = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (current >= value) {
                    ((Timer) e.getSource()).stop();
                } else {
                    current++;
                    progressBar.setValue(current);
                }
            }
        });
        timer.start();
        return card;
    }
    private JButton createActionButton(String title, ActionListener listener) {
        JButton button = new JButton(title);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 136, 229));
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.addActionListener(listener);
        return button;
    }
    private JPanel createContentPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));
        panel.setBorder(new EmptyBorder(18, 22, 18, 22));
        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Inter", Font.BOLD, 24));
        heading.setForeground(new Color(33, 47, 60));
        panel.add(heading, BorderLayout.NORTH);
        return panel;
    }
    private void showSearchResults(List<Student> results) {
        String message = results.stream()
                .map(s -> s.id + " - " + s.name + " (" + s.department + ")")
                .collect(Collectors.joining("\n"));
        if (message.isEmpty()) {
            message = "No matching students found.";
        }
        JOptionPane.showMessageDialog(frame, message, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }
    private boolean isPostgraduateProgram(String department) {
        String normalized = department == null ? "" : department.trim().toLowerCase();
        return normalized.equals("M.E") || normalized.equals("M.TECH") || normalized.equals("MBA") || normalized.equals("M.COM") || normalized.equals("MCA");
    }
    private boolean isValidStudentYear(String department, int year) {
        if (isPostgraduateProgram(department)) {
            return year >= 1 && year <= 2;
        }
        return year >= 1 && year <= 4;
    }
    private String getYearRangeMessage(String department) {
        return isPostgraduateProgram(department)
                ? "For ME/M.tech/MBA/Mcom/MCA students, year must be between 1 and 2."
                : "Year must be between 1 and 4.";
    }
    private void refreshDashboard() {
        if (dashboardStudentCountLabel != null) {
            dashboardStudentCountLabel.setText(String.valueOf(database.listStudents().size()));
        }
        if (dashboardFacultyCountLabel != null) {
            dashboardFacultyCountLabel.setText(String.valueOf(database.listFaculty().size()));
        }
        if (dashboardCourseCountLabel != null) {
            dashboardCourseCountLabel.setText(String.valueOf(database.listCourses().size()));
        }
        if (dashboardNotificationCountLabel != null) {
            dashboardNotificationCountLabel.setText(String.valueOf(database.notifications.size()));
        }
        if (dashboardTopGpaLabel != null) {
            List<Student> top = database.topStudents(1);
            dashboardTopGpaLabel.setText(top.isEmpty() ? "N/A" : String.format("%.2f", top.get(0).gpa));
        }
    }
    private void refreshAnalytics() {
        if (analyticsTopStudentsLabel != null) {
            analyticsTopStudentsLabel.setText("<html>" + database.topStudents(5).stream().map(s -> s.name + " (" + String.format("%.2f", s.gpa) + ")").collect(Collectors.joining("<br>")) + "</html>");
        }
        if (analyticsDepartmentTotalsLabel != null) {
            analyticsDepartmentTotalsLabel.setText("<html>" + database.studentsPerDepartment().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining("<br>")) + "</html>");
        }
        if (analyticsCourseCoverageLabel != null) {
            analyticsCourseCoverageLabel.setText("<html>" + database.coursesPerDepartment().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining("<br>")) + "</html>");
        }
        if (analyticsPlacementStudentsLabel != null) {
            analyticsPlacementStudentsLabel.setText("<html><body style='width:320px;font-family:Inter,sans-serif;'>" + getPlacementStudentText(9.3) + "</body></html>");
        }
        if (analyticsAlertsLabel != null) {
            analyticsAlertsLabel.setText("<html>" + String.join("<br>", database.notifications) + "</html>");
        }
        if (analyticsLearningPathsLabel != null) {
            analyticsLearningPathsLabel.setText("<html>" + database.listCourses().stream().map(c -> c.code + " - " + c.name).limit(8).collect(Collectors.joining("<br>")) + "</html>");
        }
    }
    private void refreshAllViews() {
        fillStudentTable();
        fillFacultyTable();
        fillCourseTable();
        refreshDashboard();
        refreshAnalytics();
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    private void showCourseSuggestions(String department) {
        List<Course> matches = database.listCourses().stream()
                .filter(c -> c.department.equalsIgnoreCase(department))
                .sorted(Comparator.comparingDouble(c -> -c.averageRating))
                .collect(Collectors.toList());
        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No course recommendations found for " + department, "Course Suggestions", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String suggestionText = matches.stream()
                .map(c -> c.code + " - " + c.name + " (Rating: " + String.format("%.2f", c.averageRating) + ")")
                .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(frame, suggestionText, "Recommended Courses", JOptionPane.INFORMATION_MESSAGE);
    }
    private void addNotification(String message) {
        database.notifications.add(0, message);
        if (database.notifications.size() > 7) database.notifications.remove(database.notifications.size() - 1);
        if (studentTableModel != null && facultyTableModel != null && courseTableModel != null) {
            refreshAllViews();
        }
    }
    private void seedData() {
        database.addCourse(new Course("CS101", "Full-stack Web Foundations", "CS", 4));
        database.addCourse(new Course("CS202", "Interactive Systems Design", "CS", 4));
        database.addCourse(new Course("EE105", "Embedded Systems", "EE", 3));
        database.addCourse(new Course("ME110", "Robotic Automation", "ME", 3));
        database.addCourse(new Course("HS120", "Digital Communications", "HS", 2));
        database.addFaculty(new Faculty("F001", "Professor Aditi", "CS", "Lead Instructor"));
        database.addFaculty(new Faculty("F002", "Dr. Rohan", "EE", "Associate Professor"));
        database.addFaculty(new Faculty("F003", "Ms. Priya", "ME", "Assistant Professor"));
        Student s1 = new Student("S1001", "Ananya", 2, "CS");
        s1.setGpa(9.2);
        Student s2 = new Student("S1002", "Aditya", 3, "EE");
        s2.setGpa(8.7);
        Student s3 = new Student("S1003", "Riya", 1, "ME");
        s3.setGpa(8.0);
        Student s4 = new Student("S1004", "Vikram", 4, "CS");
        s4.setGpa(9.5);
        Student s5 = new Student("S1005", "Nisha", 2, "AIDS");
        s5.setGpa(8.4);
        database.addStudent(s1);
        database.addStudent(s2);
        database.addStudent(s3);
        database.addStudent(s4);
        database.addStudent(s5);
        database.assignFacultyToCourse("F001", "CS101");
        database.assignFacultyToCourse("F002", "EE105");
        database.assignFacultyToCourse("F003", "ME110");
        database.rateCourse("CS101", 5);
        database.rateCourse("CS101", 4);
        database.rateCourse("CS202", 5);
        database.rateCourse("HS120", 4);
        // Initialize RBAC System
        rbac.createUser("U001", "Dr. Sharma", RoleBasedAccessControl.UserRole.SUPER_ADMIN, "Administration");
        rbac.createUser("U002", "Principal Dr. Patel", RoleBasedAccessControl.UserRole.PRINCIPAL, "Administration");
        rbac.createUser("U003", "HOD Rajesh", RoleBasedAccessControl.UserRole.HOD, "CS");
        rbac.createUser("F001", "Professor Aditi", RoleBasedAccessControl.UserRole.FACULTY, "CS");
        rbac.createUser("S1001", "Ananya", RoleBasedAccessControl.UserRole.STUDENT, "CS");
        rbac.createUser("ACC001", "Accountant Amit", RoleBasedAccessControl.UserRole.ACCOUNTANT, "Finance");
        // Initialize Attendance System
        attendanceSystem.markAttendance("S1001", "Ananya", "Present");
        attendanceSystem.markAttendance("S1002", "Aditya", "Present");
        attendanceSystem.markAttendance("S1003", "Riya", "Absent");
        attendanceSystem.markAttendance("S1004", "Vikram", "Late");
        attendanceSystem.markAttendance("S1005", "Nisha", "Present");
        addNotification("Campus platform live: check latest updates.");
        addNotification("New student orientation schedule released.");
        addNotification("Course registration deadline is Friday.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CollegeManagementWebsiteApp());
    }
    static class Student {
        final String id;
        String name;
        String department;
        int year;
        double gpa;
        final List<String> courses = new ArrayList<>();
        Student(String id, String name, int year, String department, double gpa) {
            this.id = id;
            this.name = name;
            this.year = year;
            this.department = department;
            this.gpa = gpa;
        }
        Student(String id, String name, int year, String department) {
            this(id, name, year, department, 0.0);
        }
        void setGpa(double gpa) {
            this.gpa = gpa;
        }
        void enroll(String courseCode) {
            if (!courses.contains(courseCode)) {
                courses.add(courseCode);
            }
        }
        void drop(String courseCode) {
            courses.remove(courseCode);
        }
    }
    static class Faculty {
        final String id;
        final String name;
        final String department;
        final String title;
        final List<String> courses = new ArrayList<>();
        Faculty(String id, String name, String department, String title) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.title = title;
        }
        void assign(String courseCode) {
            if (!courses.contains(courseCode)) {
                courses.add(courseCode);
            }
        }
    }
    static class Course {
        final String code;
        final String name;
        final String department;
        final int credits;
        double averageRating = 0.0;
        int ratingCount = 0;
        Course(String code, String name, String department, int credits) {
            this.code = code;
            this.name = name;
            this.department = department;
            this.credits = credits;
        }
        void addRating(int rating) {
            averageRating = ((averageRating * ratingCount) + rating) / (++ratingCount);
        }
    }
    static class Database {
        private final Map<String, Student> students = new HashMap<>();
        private final Map<String, Faculty> faculty = new HashMap<>();
        private final Map<String, Course> courses = new HashMap<>();
        final List<String> notifications = new ArrayList<>();
        void addStudent(Student student) {
            students.put(student.id, student);
        }
        void updateStudent(String studentId, String name, int year, String department, double gpa) {
            Student student = students.get(studentId);
            if (student != null) {
                student.name = name;
                student.year = year;
                student.department = department;
                student.gpa = gpa;
            }
        }
        void addFaculty(Faculty facultyMember) {
            faculty.put(facultyMember.id, facultyMember);
        }
        void addCourse(Course course) {
            courses.put(course.code, course);
        }
        List<Student> listStudents() {
            return new ArrayList<>(students.values());
        }
        List<Faculty> listFaculty() {
            return new ArrayList<>(faculty.values());
        }
        List<Course> listCourses() {
            return new ArrayList<>(courses.values());
        }
        void enrollStudent(String studentId, String courseCode) {
            Student student = students.get(studentId);
            if (student != null && courses.containsKey(courseCode)) {
                student.enroll(courseCode);
            }
        }
        void dropStudentFromCourse(String studentId, String courseCode) {
            Student student = students.get(studentId);
            if (student != null) {
                student.drop(courseCode);
            }
        }
        void assignFacultyToCourse(String facultyId, String courseCode) {
            Faculty facultyMember = faculty.get(facultyId);
            if (facultyMember != null && courses.containsKey(courseCode)) {
                facultyMember.assign(courseCode);
            }
        }
        Course getCourse(String courseCode) {
            return courses.get(courseCode);
        }
        Student getStudent(String studentId) {
            return students.get(studentId);
        }
        Faculty getFaculty(String facultyId) {
            return faculty.get(facultyId);
        }
        boolean studentExists(String studentId) {
            return students.containsKey(studentId);
        }
        boolean facultyExists(String facultyId) {
            return faculty.containsKey(facultyId);
        }
        boolean courseExists(String courseCode) {
            return courses.containsKey(courseCode);
        }
        void rateCourse(String courseCode, int rating) {
            Course course = courses.get(courseCode);
            if (course != null) {
                course.addRating(rating);
            }
        }
        List<Student> topStudents(int count) {
            return listStudents().stream()
                    .sorted(Comparator.comparingDouble(s -> -s.gpa))
                    .limit(count)
                    .collect(Collectors.toList());
        }
        Map<String, Long> studentsPerDepartment() {
            return listStudents().stream().collect(Collectors.groupingBy(s -> s.department, Collectors.counting()));
        }
        Map<String, Long> coursesPerDepartment() {
            return listCourses().stream().collect(Collectors.groupingBy(c -> c.department, Collectors.counting()));
        }
    }
    static class QRAttendanceSystem {
        private final List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        private int qrCounter = 1000;
        private String currentTeacherId;
        private String currentTeacherName;
        private String currentTeacherCode;
        
        public static class TeacherInfo {
            public String teacherName;
            public String teacherCode;
            public TeacherInfo(String teacherName, String teacherCode) {
                this.teacherName = teacherName;
                this.teacherCode = teacherCode;
            }
        }
        
        public static class AttendanceRecord {
            public String studentId;
            public String studentName;
            public String teacherId;
            public String teacherName;
            public String teacherCode;
            public String date;
            public String time;
            public String status;
            public AttendanceRecord(String studentId, String studentName, String teacherId, String teacherName, String teacherCode, String date, String time, String status) {
                this.studentId = studentId;
                this.studentName = studentName;
                this.teacherId = teacherId;
                this.teacherName = teacherName;
                this.teacherCode = teacherCode;
                this.date = date;
                this.time = time;
                this.status = status;
            }
        }
        public void setCurrentTeacherId(String teacherId) {
            this.currentTeacherId = teacherId;
        }
        
        public void setCurrentTeacherInfo(String teacherName, String teacherCode) {
            this.currentTeacherName = teacherName;
            this.currentTeacherCode = teacherCode;
        }
        
        public void markAttendance(String studentId, String studentName, String status) {
            markAttendanceWithTeacher(studentId, studentName, status, "UNASSIGNED", "", "");
        }
        
        public void markAttendanceWithTeacher(String studentId, String studentName, String status, String teacherId) {
            markAttendanceWithTeacher(studentId, studentName, status, teacherId, currentTeacherName, currentTeacherCode);
        }
        
        public void markAttendanceWithTeacher(String studentId, String studentName, String status, String teacherId, String teacherName, String teacherCode) {
            String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new java.text.SimpleDateFormat("HH:mm:ss").format(new Date());
            AttendanceRecord record = new AttendanceRecord(studentId, studentName, teacherId, teacherName, teacherCode, date, time, status);
            attendanceRecords.add(0, record);
            if (attendanceRecords.size() > 100) {
                attendanceRecords.remove(attendanceRecords.size() - 1);
            }
        }
        public String generateQRCode(String studentId) {
            String qrCode = "QR_" + studentId + "_" + (++qrCounter);
            return qrCode;
        }
        public String generateTeacherQRCode(String teacherId) {
            String timestamp = new java.text.SimpleDateFormat("HHmmss").format(new Date());
            String qrCode = "TEACH_" + teacherId + "_" + timestamp + "_" + (++qrCounter);
            return qrCode;
        }
        
        public String generateTeacherQRCodeWithInfo(String teacherName, String teacherCode) {
            String timestamp = new java.text.SimpleDateFormat("HHmmss").format(new Date());
            String date = new java.text.SimpleDateFormat("ddMMyy").format(new Date());
            String qrCode = "TEACH_" + teacherCode + "_" + teacherName.replaceAll(" ", "_") + "_" + date + "_" + timestamp;
            setCurrentTeacherInfo(teacherName, teacherCode);
            return qrCode;
        }
        public void processQRCode(String qrCode) {
            if (qrCode.startsWith("QR_")) {
                String[] parts = qrCode.split("_");
                if (parts.length >= 2) {
                    markAttendanceWithTeacher(parts[1], "Student_" + parts[1], "Present", currentTeacherId);
                }
            }
        }
        public void processStudentQRCode(String qrCode, String teacherId) {
            if (qrCode.startsWith("QR_STUDENT_")) {
                String[] parts = qrCode.split("_");
                if (parts.length >= 3) {
                    markAttendanceWithTeacher(parts[2], "Student_" + parts[2], "Present", teacherId);
                }
            }
        }
        public List<AttendanceRecord> getAttendanceRecords() {
            return new ArrayList<>(attendanceRecords);
        }
    }
    static class RoleBasedAccessControl {
        public enum UserRole {
            SUPER_ADMIN(new String[]{"view_marks", "generate_reports", "manage_fees", "manage_users", "manage_courses", "approve_requests"}),
            PRINCIPAL(new String[]{"view_marks", "generate_reports", "manage_fees", "approve_requests"}),
            HOD(new String[]{"view_marks", "generate_reports", "manage_courses", "approve_requests"}),
            FACULTY(new String[]{"view_marks", "grade_students", "manage_courses"}),
            STUDENT(new String[]{"view_marks", "view_fees", "view_attendance"}),
            ACCOUNTANT(new String[]{"manage_fees", "generate_reports", "view_payments"});
            private final String[] permissions;
            UserRole(String[] permissions) {
                this.permissions = permissions;
            }
            public String[] getPermissions() {
                return permissions;
            }
        }
        public static class User {
            public String id;
            public String name;
            public UserRole role;
            public String department;
            public User(String id, String name, UserRole role, String department) {
                this.id = id;
                this.name = name;
                this.role = role;
                this.department = department;
            }
        }
        private final Map<String, User> users = new HashMap<>();
        public void createUser(String id, String name, UserRole role, String department) {
            users.put(id, new User(id, name, role, department));
        }
        public String getUserPermissions(String userId) {
            User user = users.get(userId);
            if (user != null) {
                return String.join(", ", user.role.getPermissions());
            }
            return "User not found";
        }
        public boolean hasAccess(String userId, String action) {
            User user = users.get(userId);
            if (user != null) {
                for (String permission : user.role.getPermissions()) {
                    if (permission.equals(action)) {
                        return true;
                    }
                }
            }
            return false;
        }
        public List<User> getAllUsers() {
            return new ArrayList<>(users.values());
        }
        public User getUserById(String userId) {
            return users.get(userId);
        }
    }
    static class DynamicReportGenerator {
        public String generateMarksheet(String studentId) {
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            return "=== STUDENT MARKSHEET ===\n" +
                    "Student ID: " + studentId + "\n" +
                    "Generated: " + timestamp + "\n" +
                    "Status: PDF Report Ready\n" +
                    "Marks Summary:\n" +
                    "- Subject 1: 85/100\n" +
                    "- Subject 2: 92/100\n" +
                    "- Subject 3: 78/100\n" +
                    "Overall GPA: 8.5/10.0\n" +
                    "Report format: PDF (suitable for printing)";
        }
        public String generateAttendanceReport(String month) {
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            return "=== ATTENDANCE REPORT ===\n" +
                    "Month: " + month + "\n" +
                    "Generated: " + timestamp + "\n" +
                    "Total Days: 22\n" +
                    "Present: 20 days\n" +
                    "Absent: 1 day\n" +
                    "Late: 1 day\n" +
                    "Attendance Percentage: 90.9%\n" +
                    "Status: COMPLIANT";
        }
        public String generateFeeReceipt(String studentId) {
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String receiptNo = "REC-" + System.nanoTime() % 1000000;
            return "=== FEE RECEIPT ===\n" +
                    "Receipt No: " + receiptNo + "\n" +
                    "Student ID: " + studentId + "\n" +
                    "Date: " + timestamp + "\n" +
                    "Semester: Spring 2026\n" +
                    "Tuition Fee: ₹50,000\n" +
                    "Exam Fee: ₹5,000\n" +
                    "Library Fee: ₹2,000\n" +
                    "Sports Fee: ₹1,000\n" +
                    "Total Amount: ₹58,000\n" +
                    "Payment Status: PAID\n" +
                    "Thank you for your payment!";
        }
    }
}