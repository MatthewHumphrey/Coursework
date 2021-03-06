import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * The KeyboardScreen class manages the WhereTo page in the XTrek.
 *
 * @author Gabriel Copat, 2018
 */
public class KeyboardScreen extends Screen {
    //Singleton initialiser of KeyboardScreen
    private static KeyboardScreen keyboardInstance;

    private char selected = 'A';
    public String output = "";
    JLabel screen = new JLabel();
    JLabel display = new JLabel(output);

    private KeyboardScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);

        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
        screen.setBounds(87, 224, 187, 232);
        display.setText("");
        display.setBounds(97, 232, 166, 18);
        display.setOpaque(true);
        add(display);
        add(screen);
    }

    static KeyboardScreen getInstance(){
        /*
         * Returns the single instance of KeyboardScreen
         *
         * @return the instance of KeyboardScreen
         */
        if(keyboardInstance == null){
            keyboardInstance = new KeyboardScreen(sm);
        }
        return keyboardInstance;
    }

    @Override
    void plus() {
        if (selected == ']' || selected == '%') {
        	//Do nothing, end of keyboard. ] = NEXT, % = DEL
        } else {
            selected++;
            if (selected == '\\') selected = ']'; //Switch char to work with the image naming and sequence.
            if (selected == ':') selected = '#'; //Switch char to work with the image naming and sequence.
            ImageIcon icon = new ImageIcon(getClass().getResource("images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }
    }

    @Override
    void minus() {
        if (selected == 'A' || selected == '1') {
        	//Do nothing, end of keyboard
        } else {
            if (selected == ']') selected = '\\'; //Switch char to work with the image naming and sequence.
            if (selected == '#') selected = ':'; //Switch char to work with the image naming and sequence.
            selected--;
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }
    }

    @Override
    void select() {
        if (selected == '[') {
            //Space Bar
            output = output + " ";
        } else if (selected == ']') {
            //Change Keyboard to numerals
            selected = 49; //ASCI for 1
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }else if (selected == '$') {
            //Change Keyboard to Alphabet
            selected = 'A';
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }else if (selected == '%'){
            //Make output remove last digit
            if (output.length() != 0) {
                output = output.substring(0, output.length() - 1);
            }
        }else if (selected == '#') {
            //Outputs 0
            output = output + '0';
            ;

        }else{
            //Digit pressed
            output = output + selected;

        }
        display.setText(output);
    }

    void showScreen() {
        selected = 'A';
        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
        display.setText(output);
    }


}
