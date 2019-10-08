package org.itstep.controller.command;

import org.itstep.controller.command.role.admin.RatingCommand;
import org.itstep.controller.command.role.all.LoginCommand;
import org.itstep.controller.command.role.all.LogoutCommand;
import org.itstep.controller.command.role.all.RegistrationCommand;
import org.itstep.controller.command.role.user.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    /* Command holder map */
    private Map<String, Command> commands;

    /**
     * Constructs Command Holder
     */
    public CommandHolder() {
        initCommands();
    }

    /**
     * Initialize commands
     */
    private void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put("", new HomeCommand());
                put("logout", new LogoutCommand());
                put("login", new LoginCommand());
                put("registration", new RegistrationCommand());
                put("tests", new TestsCommand());
                put("getRandQuestions", new QuestionsCommand());
                put("getQuestion", new QuestionCommand());
                put("radioAnswered", new AnswerCommand());
                put("results", new ResultsCommand());
                put("resetAnswers", new ResetAnswerCommand());
                put("getRating", new RatingCommand());
            }
        };
    }

    /**
     * Get command from commands holder
     *
     * @param key command key
     * @return command object or default generated command
     */
    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> "/homeView.jsp");
    }
}
