public class Rider extends User {
    // Properties
    private boolean isRequesting;
    //Methods
    public Rider(String name, String email, String contact_no) {
        super(name, email, contact_no,"R");
        this.isRequesting = false;
    }

    public boolean isRequesting() {
        return isRequesting;
    }

    public void setRequesting(boolean requesting) {
        isRequesting = requesting;
    }
}
