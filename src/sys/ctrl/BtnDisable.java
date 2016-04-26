package sys.ctrl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CK Seong
 */

/*
creating sth similar to button disable property binding in javafx, but code is totally different and longer
i hate swing so much

How to use:

1. get textfield(s)'s doc first. Document var = tf.getDocument();
2. get button's model. ButtonModel model = btn.getModel();
3. Create a new instance of this class. BtnDisable var = new BtnDisable(model);
4. Add textfield(s)'s doc to the list of documents below. BtnDisable.addDoc(tf's doc);

Courtesy to René Link on StackOverflow:
http://stackoverflow.com/questions/23856818/set-enable-button-if-text-field-is-fill
 */
public class BtnDisable implements DocumentListener{
    private ButtonModel model;
    private List<Document> documents = new ArrayList<>();

    public BtnDisable(ButtonModel model){
        this.model = model;
    }

    public void addDoc(Document doc){
        doc.addDocumentListener(this);
        this.documents.add(doc);
        docChanged();
    }

    //best part: if any one of the textfields are empty, button is disabled!
    private void docChanged(){
        boolean enable = true;
        for (Document doc : documents){
            if (doc.getLength() == 0){
                enable = false;
            }
        }
        model.setEnabled(enable);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        docChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        docChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        docChanged();
    }

}
