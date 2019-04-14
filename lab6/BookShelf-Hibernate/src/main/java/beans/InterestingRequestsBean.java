package beans;

import controllers.AuthorController;
import controllers.ReaderController;
import entities.Author;
import entities.Reader;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "interestingBean", eager = true)
@RequestScoped
public class InterestingRequestsBean {

    private List<Reader> readers;
    private List<SelectItem> availableAuthors;
    private long idAuthor;
    private Date startTime;
    private Date endTime;

    @PostConstruct
    public void init() {
        readers = ReaderController.readAllReaders();
        availableAuthors = new ArrayList<>();
        for(Author author: AuthorController.readAllAuthors()){
            availableAuthors.add(new SelectItem(author.getIdAuthor(), author.getLastName()+" " + author.getFirstName()));
        }
    }

    public String allReadersWhoBorrowedAuthorsBookAtSpecificTime(){
        System.out.println(ReaderController.getAllReadersWhoBorrowedAuthorsBookAtSpecificTime(1,
                new Date(1999, Calendar.AUGUST,1),new Date(2222, Calendar.AUGUST,1)));
        return "success";
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public List<SelectItem> getAvailableAuthors() {
        return availableAuthors;
    }

    public void setAvailableAuthors(List<SelectItem> availableAuthors) {
        this.availableAuthors = availableAuthors;
    }

    public long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
