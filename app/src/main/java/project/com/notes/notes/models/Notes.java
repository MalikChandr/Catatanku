package project.com.notes.notes.models;

public class Notes {

    private int notesId;
    private String notesTitle;
    private String notesDescription;
    private String notesDateCreate;
    private String notesDateUpdate;

    public Notes(int notesId, String notesTitle, String notesDescription, String notesDateCreate, String notesDateUpdate) {
        this.notesId            = notesId;
        this.notesTitle         = notesTitle;
        this.notesDescription   = notesDescription;
        this.notesDateCreate    = notesDateCreate;
        this.notesDateUpdate    = notesDateUpdate;
    }

    public Notes(){}

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesDescription() {
        return notesDescription;
    }

    public void setNotesDescription(String notesDescription) {
        this.notesDescription = notesDescription;
    }

    public String getNotesDateCreate() {
        return notesDateCreate;
    }

    public void setNotesDateCreate(String notesDateCreate) {
        this.notesDateCreate = notesDateCreate;
    }

    public String getNotesDateUpdate() {
        return notesDateUpdate;
    }

    public void setNotesDateUpdate(String notesDateUpdate) {
        this.notesDateUpdate = notesDateUpdate;
    }
}
