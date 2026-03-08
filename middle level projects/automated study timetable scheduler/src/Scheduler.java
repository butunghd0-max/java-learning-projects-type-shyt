import java.util.Arrays;
import java.util.Comparator;

public class Scheduler {

    public void generateTimetable(Subject[] subjects, Day[] week) {
        Subject[] prioritizedSubjects = Arrays.copyOf(subjects, subjects.length);
        Arrays.sort(prioritizedSubjects, Comparator.comparingInt(Subject::getHoursNeeded).reversed());

        System.out.println("\n--- Weekly Study Timetable ---");

        for (Day day : week) {
            System.out.println(day.getName() + " (" + day.getAvailableHours() + " hours available):");

            for (Subject subject : prioritizedSubjects) {
                if (day.getAvailableHours() == 0) {
                    break;
                }

                if (subject.getHoursNeeded() > 0) {
                    int hoursToStudyToday = Math.min(subject.getHoursNeeded(), day.getAvailableHours());
                    System.out.println("  -> Study " + subject.getName() + " for " + hoursToStudyToday + " hour(s)");
                    subject.reduceHoursNeeded(hoursToStudyToday);
                    day.reduceAvailableHours(hoursToStudyToday);
                }
            }

            if (day.getAvailableHours() > 0) {
                System.out.println("  -> " + day.getAvailableHours() + " hour(s) of free time remaining.");
            }

            System.out.println("------------------------------");
        }

        printUnscheduledWork(prioritizedSubjects);
    }

    private void printUnscheduledWork(Subject[] subjects) {
        int remainingHours = 0;

        for (Subject subject : subjects) {
            remainingHours += subject.getHoursNeeded();
        }

        if (remainingHours == 0) {
            System.out.println("All study hours scheduled successfully.");
            return;
        }

        System.out.println("Unscheduled work remains:");
        for (Subject subject : subjects) {
            if (subject.getHoursNeeded() > 0) {
                System.out.println("  - " + subject.getName() + ": " + subject.getHoursNeeded() + " hour(s)");
            }
        }
    }
}