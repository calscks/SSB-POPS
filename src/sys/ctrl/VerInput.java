package sys.ctrl;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author CK Seong
 */

public class VerInput {

    /**
     * Call this after adding KeyListener.
     * <p>
     *     How to: new VerInput().[any methods inside VerInput class](parameters);
     * </p>
     * <p>
     *     For example: new VerInput().keyTyped(e, 15);
     * </p>
     */
    //default constructor without anything :)
    public VerInput(){}

    //the very common method to accept only alphanumeric
    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();

        if (!(Character.isLetter(c) || Character.isDigit(c) || Character.isISOControl(c))){
            e.consume();
        }
    }

    //overloading of keyTyped to accept max length
    public void keyTyped(KeyEvent e, Integer maxLength) {
        char c = e.getKeyChar();
        JTextField textField = (JTextField) e.getSource();

        if (textField.getText().length() >= maxLength){
            e.consume();
        }
        if (!(Character.isLetter(c) || Character.isDigit(c) || Character.isISOControl(c))){
            e.consume();
        }
    }

}
