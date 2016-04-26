package sys.ctrl;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * @author CK Seong
 */

/*
Courtesy of Hovercraft Full Of Eels on Stack Overflow:
http://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers

Modified for my own needs.
Uses regular expression to match any number before decimal place, and limit only 2
d.p. in the verify method below.

To use:
PlainDocument doc = (PlainDocument) textfield.getDocument();
doc.setDocumentFilter(new PriceDoc());
Applies to price.
 */

public class PriceDoc extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        if (verify(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean verify(String text) {
        return text.matches("^[0-9]+[.]?[0-9]?[0-9]?$");
        /*
        Same as:
        if (text.matches("^[0-9]+[.]?[0-9]?[0-9]?$"))
            return true;
        else
            return false;

        due to the matches method is also returning a boolean, hence it can be simplified.
         */
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        //Lazy to know why but I have to -1 else I can't remove the first digit in the text field lmao
        sb.delete(offset, offset + length - 1);
        if (verify(sb.toString())) {
            super.remove(fb, offset, length);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws
            BadLocationException {
        Document doc = fb.getDocument();
        String sb = doc.getText(0, doc.getLength());
        sb += text;
        if (verify(sb)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
