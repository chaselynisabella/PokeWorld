import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the ControlPanel for PokeBattle. It allows the
 * user to pick which Pokemon it would like to add next.
 *
 * @author Sundeep, Ethan, Heather, cbaca3
 * @version 1.0
 */
public class ControlPanel extends JPanel {
    private JButton rapidash, poliwhirl, venusaur;

    private JButton ampharos;

    private JLabel current;

    private String pokemonSpecies;

    /**
    * This creates a ControlPanel object and creates the button to add pokemon
    */
    public ControlPanel() {
        setPreferredSize(new Dimension(150, PokeWorld.HEIGHT));

        rapidash = new JButton("Rapidash");
        rapidash.addActionListener(new ButtonListener("Rapidash"));
        add(rapidash);

        poliwhirl = new JButton("Poliwhirl");
        poliwhirl.addActionListener(new ButtonListener("Poliwhirl"));
        add(poliwhirl);

        venusaur = new JButton("Venusaur");
        venusaur.addActionListener(new ButtonListener("Venusaur"));
        add(venusaur);

        ampharos = new JButton("Ampharos");
        ampharos.addActionListener(new ButtonListener("Ampharos"));
        add(ampharos);

        //default starting pokemon
        pokemonSpecies = "Rapidash";
        add(new JLabel("Current Pokemon"));
        current = new JLabel("Rapidash");
        add(current);

    }

    /**
     * Invoked by PokeWorld to determine which Pokemon
     * was chosen
     *
     * @return The currently selected Pokemon species
     */
    public String getPokemonSpecies() {
        return pokemonSpecies;
    }

    /**
     * Listener for clicks on the Pokemon switcher buttons
     */
    public class ButtonListener implements ActionListener {
        private String name;

        /**
        * Creates the button for the pokemon when clicked
        * @param className name of class and creates the button with that name
        */
        public ButtonListener(String className) {
            name = className;
        }

        /**
        * Recognizes that the button was clicked
        * @param e action of clicking
        */
        public void actionPerformed(ActionEvent e) {
            pokemonSpecies = name;
            current.setText(name);
        }
    }
}
