public class Main {
    public static void main(String[] args) {
        Subject[] myWorkload = {
            new Subject("Mathematics EE Draft", 4),
            new Subject("Physics Problem Set", 2),
            new Subject("Computer Science Revision", 3),
            new Subject("Chemistry IA Analysis", 2)
        };

        Day[] myWeek = {
            new Day("Monday", 2),
            new Day("Tuesday", 3),
            new Day("Wednesday", 2),
            new Day("Thursday", 2),
            new Day("Friday", 1)
        };

        Scheduler scheduler = new Scheduler();
        scheduler.generateTimetable(myWorkload, myWeek);
    }
}