package Handlers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Note {
    public Skin skin;
    public boolean isHighlighted;

    public Sound sound;

    public TextButton textbutton;

    public String noteName;


    public Note(String noteName, Skin skin, Sound sound, boolean isHighlighted) {
        this.skin = skin;
        this.isHighlighted = isHighlighted;
        this.sound = sound;
        this.noteName = noteName;

        textbutton = new TextButton(noteName, skin);


    }

    public void setHighlighted(boolean highlighted) {

        if (highlighted) {
            textbutton.setColor(1, 0, 1, 1);
        }
        if (!highlighted) {
            textbutton.setColor(1, 1, 1, 1);
        }


    }

    public void playSound() {
        sound.play(1.0f);
    }
}



