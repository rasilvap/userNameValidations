package username.validation.view;

import username.validation.control.LoginControl;
import username.validation.exceptions.UserNameException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import static username.validation.util.Constants.*;

/**
 * This class is in charge of paint the user Interface
 * @author Rodolfo Silva
 */
public class LoginView extends JDialog {

    private JTextField tfUsername;
    private JLabel lbUsername;
    private JButton btnLogin;
    private boolean succeeded;

    /**
     * Class's consteuctor initialize and paint the GUI
     * @param parent the parent frame
     */
    public LoginView(Frame parent) {
        super(parent, PANEL_LOGIN_TITLE, true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        lbUsername = new JLabel(PANEL_LABEL_USERNAME);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.setBorder(new LineBorder(Color.GREEN));
        btnLogin = new JButton(BUTTON_LOGIN_LABEL);
        LoginControl loginControl = new LoginControl();
        btnLogin.addActionListener(e -> {
            String message;
            int informationMessage=0;
            try{
                loginControl.validateUserName(getUsername());
                if (loginControl.validateDuplicatedUser(getUsername())) {
                    message = ERROR_MESSAGE_USERNAME_EXIST;
                    message+=" " +loginControl.printSuggestionUsers(getUsername(),LENGTH_RANDOM_DUPLICATED_USER);
                    tfUsername.setText("");
                    succeeded = false;
                    informationMessage = JOptionPane.ERROR_MESSAGE;
                } else {
                    if (loginControl.validateRestrictedWords(getUsername())) {
                        message = ERROR_MESSAGE_USERNAME_RESTRICTED_WORD;
                        message+=" " +loginControl.printSuggestionUsers("",LENGTH_RANDOM_RESTRICTED_UER);
                        tfUsername.setText("");
                        succeeded = false;
                        informationMessage = JOptionPane.ERROR_MESSAGE;
                    }else{
                        message = SUCCES_MESSAGE;
                        succeeded = true;
                        informationMessage = JOptionPane.INFORMATION_MESSAGE;
                    }
                }
            }catch(UserNameException ex){
                message = ex.getMessage();
            }
            showMessageDialog(message,informationMessage);
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    /**
     * Returns the username typed by the user
     * @return the username value
     */
    public String getUsername() {
        return tfUsername.getText().trim();
    }

    /**
     * This method returns the succeded variable related
     * with the operation's result
     * @return the succeed value
     */
    public boolean isSucceeded() {
        return succeeded;
    }

    /**
     * This method paints a show dialog with the operation's result
     * @param message to show to the user in the GUI
     * @param informationMessage to show to the type of panel
     */
    private void showMessageDialog(String message,int informationMessage) {
        JOptionPane.showMessageDialog(LoginView.this,
                message,
                PANEL_LOGIN_TITLE,
                informationMessage);
    }


}
