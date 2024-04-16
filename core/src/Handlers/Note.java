package Handlers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Note {
    public Skin skin;
    public boolean isHighlighted;

    public Sound sound;

    public TextButton textbutton;

    public String noteName;

    public Note(String noteName, Skin skin, Sound sound, boolean isHighlighted){
        this.skin = skin;
        this.isHighlighted = isHighlighted;
        this.sound = sound;
        this.noteName = noteName;

        textbutton = new TextButton(noteName,skin);

    }

    public boolean setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
        return isHighlighted;

    }

    public void playSound(){
        sound.play(1.0f);
    }

}
