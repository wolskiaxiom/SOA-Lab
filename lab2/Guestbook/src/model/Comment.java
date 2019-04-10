package model;

public class Comment {
    public String commentator;
    public String email;
    public String comment;

    public Comment(String commentator, String email, String comment) {
        this.commentator = commentator;
        this.email = email;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return commentator+ " ("+email+") " + "says " + comment;
    }
}
