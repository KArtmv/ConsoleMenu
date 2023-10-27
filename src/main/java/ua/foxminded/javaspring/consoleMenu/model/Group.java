package ua.foxminded.javaspring.consoleMenu.model;

public class Group {

    private Long groupID;
    private String groupName;

    public Group(Long groupID) {
        this.groupID = groupID;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(Long groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public Long getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }
}
