package project.controller.command;

public class Action {
    private String url;
    private ActionType actionType;


    public Action(String url, ActionType actionType) {
        this.url = url;
        this.actionType = actionType;
    }

    public String getUrl() {
        return url;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public enum ActionType {
        FORWARD, REDIRECT
    }
}
