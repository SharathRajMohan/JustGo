public class Rider extends User {
    // Properties
    private boolean isRequesting;
    private Trip CurrentTrip;
    //Methods
    public Rider(String name, String email, String contact_no) {
        super(name, email, contact_no,"R");
        this.isRequesting = false;
        this.CurrentTrip = null;
    }

    public boolean isRequesting() {
        return isRequesting;
    }

    public void setRequesting(boolean requesting) {
        isRequesting = requesting;
    }

    public Trip getCurrentTrip() {
        return CurrentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        CurrentTrip = currentTrip;
    }
}
