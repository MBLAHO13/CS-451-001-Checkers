package ux.Screens;

import ux.Buttons.OptionButton;
import ux.Labels.NoteLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrNotify extends ScrFactory {
    protected OptionButton okBut = new OptionButton(STYLE.GREEN, STRINGS.DELETECONFIRMBUT);
    NoteLabel msg = null;

    public ScrNotify(String text) {
        //The button fills horizontal unlike everything else. This is intended or  I have to increase the original frame size.
        msg = new NoteLabel(text);
        this.constr.gridwidth = 2;
        this.add(msg);
        this.constr.gridwidth = 1;
        this.constr.gridy++;
        this.constr.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.okBut);
        this.constr.gridx++;

        //Add button functionality
        this.okBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Server call to delete the game

                //Then exits out of the delete confirmation page
                frame.dispose();
            }
        });

    }
}
