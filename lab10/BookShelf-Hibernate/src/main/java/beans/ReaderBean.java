package beans;

import controllers.ReaderController;
import entities.Reader;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "readerBean")
@RequestScoped
public class ReaderBean {

    private Reader readerEntity = new Reader();
    private List<Reader> readers = ReaderController.readAllReaders();

    public ReaderBean() {
    }

    public String addReader(){
        readerEntity.setBooks(new ArrayList<>());
        ReaderController.addReader(readerEntity);
        return "success";
    }

    public String deleteReader(long idReader){
        ReaderController.deleteReader(idReader);
        return "success";
    }

    public List<Reader> getReaders() {
        readers = ReaderController.readAllReaders();
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public Reader getReaderEntity() {
        return readerEntity;
    }

    public void setReaderEntity(Reader readerEntity) {
        this.readerEntity = readerEntity;
    }
}
