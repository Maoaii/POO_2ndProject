import versionControlSystem.VersionControlSystem;
import versionControlSystem.VersionControlSystemClass;

import java.util.Scanner;

public class Main {
    // Commands
    private enum Command {
        HELP, EXIT, UNKNOWN
    }

    // Output messages
    private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
    private static final String EXIT_MESSAGE = "Bye!";
    private static final String HELP_MESSAGE = "register - adds a new userê\n" +
                                                "users - lists all registered usersê\n" +
                                                "create - creates a new projectê\n" +
                                                "projects - lists all projectsê\n" +
                                                "team - adds team members to a projectê\n" +
                                                "artefacts - adds artefacts to a projectê\n" +
                                                "project - shows detailed project informationê\n" +
                                                "revision - revises an artefactê\n" +
                                                "manages - lists developers of a managerê\n" +
                                                "keyword - filters projects by keywordê\n" +
                                                "confidentiality - filters projects by confidentiality levelê\n" +
                                                "workaholics - top 3 employees with more artefacts updatesê\n" +
                                                "common - employees with more projects in commonê\n" +
                                                "help - shows the available commandsê\n" +
                                                "exit - terminates the execution of the programê\n";


    public static void main(String[] args) {
        interpretCommands();
    }

    private static void interpretCommands() {
        Scanner in = new Scanner(System.in);
        VersionControlSystem groceryStore = new VersionControlSystemClass();

        Command command = readCommand(in);

        while (!command.equals(Command.EXIT)) {
            switch (command) {
                case HELP: interpretHelp(); break;
                default: System.out.println(UNKNOWN_COMMAND); break;
            }
            System.out.println();
            command = readCommand(in);
        }
        System.out.println(EXIT_MESSAGE);
        System.out.println();
        in.close();
    }

    private static Command readCommand(Scanner in) {
        String cmd = in.next().toUpperCase().trim();

        if (cmd.equals("CRIA"))
            cmd += in.next().toUpperCase().trim();
        try {
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    private static void interpretHelp() {
        System.out.println(HELP_MESSAGE);
    }
}
