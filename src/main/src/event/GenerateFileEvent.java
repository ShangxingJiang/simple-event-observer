package event;

public class GenerateFileEvent extends EventBase{

    private String fileName;

    private String content;

    public GenerateFileEvent() {
        this.id = "GenerateFileEvent";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
